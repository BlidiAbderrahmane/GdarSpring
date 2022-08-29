package com.esprit.services;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.esprit.entities.APIRest;
import com.esprit.entities.APIRestclass;
import com.esprit.entities.APIRestmethod;
import com.esprit.entities.APIRestproject;
import com.esprit.entities.APIRestressource;
import com.esprit.entities.Details;
import com.esprit.entities.Projet;
import com.esprit.entities.Projets;
import com.esprit.entities.SearchCritiria;
import com.esprit.entities.SearchDetailsCritiria;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProjectsServiceImpl implements IPorjectsService{
	
	@Override
	public List<Projet> getDocumentations() {
		List<Projet> documentation= new ArrayList<Projet>() ;
		ObjectMapper mapper= new ObjectMapper();
		Projets lp;
		try {
			lp = mapper.readValue(new File ("data/documentation.json"), Projets.class);
			documentation= lp.getProjets();
		} catch (JsonParseException e) {
			System.out.println(e.getMessage());
		} catch (JsonMappingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return documentation;
	}
	
	@Override
	public APIRest searchAPIProject(SearchCritiria f) {
		List<Projet> l1 = getDocumentations();
		if(f.isEmpty()) {
			System.out.println("les critères de recherche sont vides");
			return null;
		}
		else {
			APIRest rl = new APIRest();
			List<APIRestproject> pl = new ArrayList<APIRestproject>();
			if(f.getProjet()!="") {
				for(int i=0 ; i< l1.size(); i++) {
					if( l1.get(i).getNomProjet().toUpperCase().startsWith(f.getProjet().toUpperCase()) ) {
						APIRestproject rproject= new APIRestproject();
						rproject.setIdAPIRestproject(l1.get(i).getIdProject());
						rproject.setProjet(l1.get(i).getNomProjet());
						rproject.setVersion(l1.get(i).getVersion());
						pl.add(rproject);
					}
				}
				rl.setAPIRestprojects(pl);
			}
			else {
				for(int i=0 ; i< l1.size(); i++) {
					APIRestproject rproject= new APIRestproject();
					rproject.setIdAPIRestproject(l1.get(i).getIdProject());
					rproject.setProjet(l1.get(i).getNomProjet());
					rproject.setVersion(l1.get(i).getVersion());
					pl.add(rproject);
				}
				rl.setAPIRestprojects(pl);
			}
			return searchAPIClasses(rl, l1,f); 
		}
	}
	
	@Override
	public APIRest searchAPIClasses( APIRest rl, List<Projet> l1, SearchCritiria f) {
		if(f.getController()=="") {
			for(int i=0;i<rl.getAPIRestprojects().size();i++) {
				List<APIRestclass> rcs = new ArrayList<APIRestclass>() ;
				for(int j=0;j<l1.size();j++) {
					if(l1.get(j).getNomProjet().toUpperCase().startsWith( rl.getAPIRestprojects().get(i).getProjet().toUpperCase())) {
						for (int k=0;k<l1.get(j).getClasses().size();k++) {
							APIRestclass rc = new APIRestclass();
							rc.setIdAPIRestclass(l1.get(j).getClasses().get(k).getIdClass());
							rc.setController(l1.get(j).getClasses().get(k).getNomClass());
							rcs.add(rc);
						}
					}
				}
				rl.getAPIRestprojects().get(i).setAPIRestclasses(rcs);
			}
		}
		else {
			ArrayList<String> projToDel = new ArrayList<String>();
			for(int i=0;i<rl.getAPIRestprojects().size();i++) {
				List<APIRestclass> rcs = new ArrayList<APIRestclass>() ;
				boolean  Rlfounded =false;
				for(int j=0;j<l1.size();j++) {
					if(l1.get(j).getNomProjet().equals( rl.getAPIRestprojects().get(i).getProjet())) {
						boolean found =false;
						int k=0;
						while(k<l1.get(j).getClasses().size()) {
							if(l1.get(j).getClasses().get(k).getNomClass().toUpperCase().startsWith(f.getController().toUpperCase()) ) {
								Rlfounded = true;
								found=true;
								APIRestclass rc = new APIRestclass();
								rc.setIdAPIRestclass(l1.get(j).getClasses().get(k).getIdClass());
								rc.setController(l1.get(j).getClasses().get(k).getNomClass());
								rcs.add(rc);
							}
							k++;
						}
						if(!found) {
							projToDel.add(rl.getAPIRestprojects().get(i).getIdAPIRestproject());	
						}
					}
				}
				if(Rlfounded) {
					rl.getAPIRestprojects().get(i).setAPIRestclasses(rcs);
				}
			}
			for(int i=0;i<projToDel.size();i++) {
				int j=0;
				boolean found=false;
				while(j<rl.getAPIRestprojects().size() && !found) {
					if(projToDel.get(i).equals(rl.getAPIRestprojects().get(j).getIdAPIRestproject())) {
						rl.getAPIRestprojects().remove(rl.getAPIRestprojects().get(j));
						found=true;
					}
					j++;
				}
			} 
		}
		return searchAPIMethod(rl,l1,f);
	}
	
	@Override
	public APIRest searchAPIMethod(APIRest rl, List<Projet> l1, SearchCritiria f) {
		if(f.getMethode()=="" && f.getDescription()=="") {
			for(int i=0;i<rl.getAPIRestprojects().size();i++) {
				for(int j=0;j<l1.size();j++) {
					if(l1.get(j).getNomProjet().equals( rl.getAPIRestprojects().get(i).getProjet())) {
						for(int o=0;o<rl.getAPIRestprojects().get(i).getAPIRestclasses().size();o++) {
							List<APIRestmethod> rms= new ArrayList<APIRestmethod>(); 
							for(int k=0;k<l1.get(j).getClasses().size();k++) {
								if(l1.get(j).getClasses().get(k).getNomClass().equals( rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getController()) )  {
									if (l1.get(j).getClasses().get(k).getMethods() != null) {
										for(int m=0;m<l1.get(j).getClasses().get(k).getMethods().size();m++) {
											APIRestmethod rm= new APIRestmethod();
											rm.setIdAPIRestmethod(l1.get(j).getClasses().get(k).getMethods().get(m).getIdMethod());
											rm.setNomMethode(l1.get(j).getClasses().get(k).getMethods().get(m).getNomMethod());
											rm.setDescription(l1.get(j).getClasses().get(k).getMethods().get(m).getDescription());
											rms.add(rm);
										} 
									}
								}
							}
							rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).setAPIRestmethods(rms);
						}
					}
				}
			}
		}
		else {
			if(f.getMethode()!="") {
				for(int i=0;i<rl.getAPIRestprojects().size();i++) {
					for(int j=0;j<l1.size();j++) {
						if(l1.get(j).getNomProjet().equals( rl.getAPIRestprojects().get(i).getProjet())) {
							ArrayList<String> classToDel = new ArrayList<String>();
							for(int o=0;o<rl.getAPIRestprojects().get(i).getAPIRestclasses().size();o++) {
								List<APIRestmethod> rms= new ArrayList<APIRestmethod>();
								boolean classIsEmpty=true;
								for(int k=0;k<l1.get(j).getClasses().size();k++) {
									if(l1.get(j).getClasses().get(k).getNomClass().equals( rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getController()) )  {
										boolean found=false;
										if (l1.get(j).getClasses().get(k).getMethods() != null) {
											for(int m=0;m<l1.get(j).getClasses().get(k).getMethods().size();m++) {
												if(l1.get(j).getClasses().get(k).getMethods().get(m).getNomMethod().toUpperCase().startsWith(f.getMethode().toUpperCase()) ) {
													if( 
															( (f.getDescription()!="") && (l1.get(j).getClasses().get(k).getMethods().get(m).getDescription().toUpperCase().contains(f.getDescription().toUpperCase())) )
															||(f.getDescription()=="")
															) {
														classIsEmpty=false;
														found=true;
														APIRestmethod rm= new APIRestmethod();
														rm.setIdAPIRestmethod(l1.get(j).getClasses().get(k).getMethods().get(m).getIdMethod());
														rm.setNomMethode(l1.get(j).getClasses().get(k).getMethods().get(m).getNomMethod());
														rm.setDescription(l1.get(j).getClasses().get(k).getMethods().get(m).getDescription());
														rms.add(rm);
													}
												}
											} 
										}	
										if(!found) {
											classToDel.add(rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getIdAPIRestclass());	
										}
									}
								}
								if(!classIsEmpty ) {
									rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).setAPIRestmethods(rms);
								}
							}
							for(int a=0;a<classToDel.size();a++) {
								for(int b=0;b<rl.getAPIRestprojects().size();b++) {
									int c=0;
									boolean found=false;
									while(c<rl.getAPIRestprojects().get(b).getAPIRestclasses().size() && !found) {
										if(classToDel.get(a).equals(rl.getAPIRestprojects().get(b).getAPIRestclasses().get(c).getIdAPIRestclass())) {
											rl.getAPIRestprojects().get(b).getAPIRestclasses().remove(rl.getAPIRestprojects().get(b).getAPIRestclasses().get(c));
											found=true;
										}
										c++;
									}
								}	
							} 
						}
					}
				}
			}
			else {
				if(f.getDescription()!="") {
					for(int i=0;i<rl.getAPIRestprojects().size();i++) {
						for(int j=0;j<l1.size();j++) {
							if(l1.get(j).getNomProjet().equals( rl.getAPIRestprojects().get(i).getProjet())) {
								ArrayList<String> classToDel = new ArrayList<String>();
								for(int o=0;o<rl.getAPIRestprojects().get(i).getAPIRestclasses().size();o++) {
									List<APIRestmethod> rms= new ArrayList<APIRestmethod>();
									boolean classIsEmpty=true;
									for(int k=0;k<l1.get(j).getClasses().size();k++) {
										if(l1.get(j).getClasses().get(k).getNomClass().equals( rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getController()) )  {
											boolean found=false;
											if (l1.get(j).getClasses().get(k).getMethods() != null) {
												for(int m=0;m<l1.get(j).getClasses().get(k).getMethods().size();m++) {
													if( l1.get(j).getClasses().get(k).getMethods().get(m).getDescription().toUpperCase().contains(f.getDescription().toUpperCase()) ) {
														classIsEmpty=false;
														found=true;
														APIRestmethod rm= new APIRestmethod();
														rm.setIdAPIRestmethod(l1.get(j).getClasses().get(k).getMethods().get(m).getIdMethod());
														rm.setNomMethode(l1.get(j).getClasses().get(k).getMethods().get(m).getNomMethod());
														rm.setDescription(l1.get(j).getClasses().get(k).getMethods().get(m).getDescription());
														rms.add(rm);
													}
												} 
											}	
											if(!found) {
												classToDel.add(rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getIdAPIRestclass());	
											}
										}
									}
									if(!classIsEmpty ) {
										rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).setAPIRestmethods(rms);
									}
								}
								for(int a=0;a<classToDel.size();a++) {
									for(int b=0;b<rl.getAPIRestprojects().size();b++) {
										int c=0;
										boolean found=false;
										while(c<rl.getAPIRestprojects().get(b).getAPIRestclasses().size() && !found) {
											if(classToDel.get(a).equals(rl.getAPIRestprojects().get(b).getAPIRestclasses().get(c).getIdAPIRestclass())) {
												rl.getAPIRestprojects().get(b).getAPIRestclasses().remove(rl.getAPIRestprojects().get(b).getAPIRestclasses().get(c));
												found=true;
											}
											c++;
										}
									}	
								} 
							}
						}
					}
				}
			}
		}
		ArrayList<String> projToDel = new ArrayList<String>();
		for(int i=0;i<rl.getAPIRestprojects().size();i++) {
			if(rl.getAPIRestprojects().get(i).getAPIRestclasses().size() == 0) {
				projToDel.add(rl.getAPIRestprojects().get(i).getIdAPIRestproject());	
			}
		}
		for(int i=0;i<projToDel.size();i++) {
			int j=0;
			boolean found=false;
			while(j<rl.getAPIRestprojects().size() && !found) {
				if(projToDel.get(i).equals(rl.getAPIRestprojects().get(j).getIdAPIRestproject())) {
					rl.getAPIRestprojects().remove(rl.getAPIRestprojects().get(j));
					found=true;
				}
				j++;
			}
		} 
		return searchAPICodeErreur(rl,l1,f);
	} 
	
	@Override
	public APIRest searchAPICodeErreur(APIRest rl,List<Projet> l1, SearchCritiria f) {
		if(f.getCodeErreur()!="") {
			for(int i=0;i<rl.getAPIRestprojects().size();i++) {
				for(int j=0;j<l1.size();j++) {
					if(l1.get(j).getNomProjet().equals( rl.getAPIRestprojects().get(i).getProjet())) {
						for(int o=0;o<rl.getAPIRestprojects().get(i).getAPIRestclasses().size();o++) {
							for(int k=0;k<l1.get(j).getClasses().size();k++) {
								if(l1.get(j).getClasses().get(k).getNomClass().equals( rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getController()) )  {
									ArrayList<String> methToDel = new ArrayList<String>();
									for(int p=0;p<rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().size();p++) {
										for(int m=0;m<l1.get(j).getClasses().get(k).getMethods().size();m++) {
											if(l1.get(j).getClasses().get(k).getMethods().get(m).getNomMethod().equals( rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().get(p).getNomMethode()) ) {
												boolean found=false;
												if (l1.get(j).getClasses().get(k).getMethods().get(m).getFunctionalErrors() != null) {
													for(int q=0;q<l1.get(j).getClasses().get(k).getMethods() .get(m).getFunctionalErrors().size();q++) {
														if( l1.get(j).getClasses().get(k).getMethods().get(m).getFunctionalErrors().get(q).getCode().toUpperCase().contains(f.getCodeErreur().toUpperCase()) ) {
															found=true;
														}
													}
												}
												if(!found) {
													methToDel.add(rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().get(p).getIdAPIRestmethod());	
												}
											}
										}
									}
									for(int a=0;a<methToDel.size();a++) {
										for(int b=0;b<rl.getAPIRestprojects().size();b++) {
											for(int c=0;c<rl.getAPIRestprojects().get(b).getAPIRestclasses().size();c++) {
												int d=0;
												boolean found=false;
												while(d<rl.getAPIRestprojects().get(b).getAPIRestclasses().get(c).getAPIRestmethods().size() && !found) {
													if(methToDel.get(a).equals(rl.getAPIRestprojects().get(b).getAPIRestclasses().get(c).getAPIRestmethods().get(d).getIdAPIRestmethod())) {
														rl.getAPIRestprojects().get(b).getAPIRestclasses().get(c).getAPIRestmethods().remove(rl.getAPIRestprojects().get(b).getAPIRestclasses().get(c).getAPIRestmethods().get(d));
														found=true;
													}
													d++;
												}
											}
										}	
									} 
								}
							}
						}
					}
					ArrayList<String> classToDel = new ArrayList<String>();
					for(int k=0;k<rl.getAPIRestprojects().size();k++) {
						for(int m=0;m<rl.getAPIRestprojects().get(k).getAPIRestclasses().size();m++) {
							if(rl.getAPIRestprojects().get(k).getAPIRestclasses().get(m).getAPIRestmethods().size() == 0) {
								classToDel.add(rl.getAPIRestprojects().get(k).getAPIRestclasses().get(m).getIdAPIRestclass());	
							}
						}
					}
					for(int k=0;k<classToDel.size();k++) {
						for(int m=0;m<rl.getAPIRestprojects().size();m++) {
							int l=0;
							boolean found=false;
							while(l<rl.getAPIRestprojects().get(m).getAPIRestclasses().size() && !found) {
								if(classToDel.get(k).equals(rl.getAPIRestprojects().get(m).getAPIRestclasses().get(l).getIdAPIRestclass())) {
									rl.getAPIRestprojects().get(m).getAPIRestclasses().remove(rl.getAPIRestprojects().get(m).getAPIRestclasses().get(l));
									found=true;
								}
								l++;
							}
						}
					}
				}
			}
		}
		ArrayList<String> projToDel = new ArrayList<String>();
		for(int i=0;i<rl.getAPIRestprojects().size();i++) {
			if(rl.getAPIRestprojects().get(i).getAPIRestclasses().size() == 0) {
				projToDel.add(rl.getAPIRestprojects().get(i).getIdAPIRestproject());	
			}
		}
		for(int i=0;i<projToDel.size();i++) {
			int j=0;
			boolean found=false;
			while(j<rl.getAPIRestprojects().size() && !found) {
				if(projToDel.get(i).equals(rl.getAPIRestprojects().get(j).getIdAPIRestproject())) {
					rl.getAPIRestprojects().remove(rl.getAPIRestprojects().get(j));
					found=true;
				}
				j++;
			}
		}
		return searchAPICriteresHTTP(rl,l1,f);
	}
	
	@Override
	public APIRest searchAPICriteresHTTP(APIRest rl,List<Projet> l1, SearchCritiria f) {
		ArrayList<String> methToDel = new ArrayList<String>();
		if(f.getUrlApi()=="" && f.getUrlRessource()=="") {
			for(int i=0;i<rl.getAPIRestprojects().size();i++) {
				for(int j=0;j<l1.size();j++) {
					if(l1.get(j).getNomProjet().equals( rl.getAPIRestprojects().get(i).getProjet())) {
						for(int o=0;o<rl.getAPIRestprojects().get(i).getAPIRestclasses().size();o++) {
							for(int k=0;k<l1.get(j).getClasses().size();k++) {
								if(l1.get(j).getClasses().get(k).getNomClass().equals( rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getController()) )  {
									for(int p=0;p<rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().size();p++) {
										List<APIRestressource> rrs= new ArrayList<APIRestressource>();
										for(int m=0;m<l1.get(j).getClasses().get(k).getMethods().size();m++) {
											if(l1.get(j).getClasses().get(k).getMethods().get(m).getNomMethod().equals( rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().get(p).getNomMethode()) ) {
												if (l1.get(j).getClasses().get(k).getMethods().get(m).getResources() != null) {
													for(int q=0;q<l1.get(j).getClasses().get(k).getMethods().get(m).getResources().size();q++) {
														APIRestressource rr= new APIRestressource();
														rr.setIdAPIRestressource(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getIdRessource());													
														rr.setApi(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getUrlApi());
														rr.setRessource(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getUrlRessource());
														rr.setVerbHTTP(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getVerbHttp());
														rrs.add(rr);
													}
												}
											}
											rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().get(p).setAPIRestresources(rrs);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		else {
			if(f.getUrlApi()!="") {
				for(int i=0;i<rl.getAPIRestprojects().size();i++) {
					for(int j=0;j<l1.size();j++) {
						if(l1.get(j).getNomProjet().equals( rl.getAPIRestprojects().get(i).getProjet())) {
							for(int o=0;o<rl.getAPIRestprojects().get(i).getAPIRestclasses().size();o++) {
								for(int k=0;k<l1.get(j).getClasses().size();k++) {
									if(l1.get(j).getClasses().get(k).getNomClass().equals( rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getController()) )  {
										boolean methodIsEmpty=true;
										for(int p=0;p<rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().size();p++) {
											List<APIRestressource> rrs= new ArrayList<APIRestressource>();
											for(int m=0;m<l1.get(j).getClasses().get(k).getMethods().size();m++) {
												if(l1.get(j).getClasses().get(k).getMethods().get(m).getNomMethod().equals( rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().get(p).getNomMethode()) ) {
													boolean found=false;
													if (l1.get(j).getClasses().get(k).getMethods().get(m).getResources() != null) {
														for(int q=0;q<l1.get(j).getClasses().get(k).getMethods().get(m).getResources().size();q++) {
															if( l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getUrlApi().toUpperCase().contains(f.getUrlApi().toUpperCase()) ) {
																if( 
																		( (f.getUrlRessource()!="") && ( l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getUrlRessource().toUpperCase().contains(f.getUrlRessource().toUpperCase()) ) )
																		||(f.getUrlRessource()=="")
																		) {
																	methodIsEmpty=false;
																	found=true;
																	APIRestressource rr= new APIRestressource();
																	rr.setIdAPIRestressource(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getIdRessource());													
																	rr.setApi(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getUrlApi());
																	rr.setRessource(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getUrlRessource());
																	rr.setVerbHTTP(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getVerbHttp());
																	rrs.add(rr);
																}
															}
														}
													}
													if(!found) {
														methToDel.add(rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().get(p).getIdAPIRestmethod());	
													}
												}
											}
											if(!methodIsEmpty ) {
												rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().get(p).setAPIRestresources(rrs);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			else {
				if(f.getUrlRessource()!="") {
					for(int i=0;i<rl.getAPIRestprojects().size();i++) {
						for(int j=0;j<l1.size();j++) {
							if(l1.get(j).getNomProjet().equals( rl.getAPIRestprojects().get(i).getProjet())) {
								for(int o=0;o<rl.getAPIRestprojects().get(i).getAPIRestclasses().size();o++) {
									for(int k=0;k<l1.get(j).getClasses().size();k++) {
										if(l1.get(j).getClasses().get(k).getNomClass().equals( rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getController()) )  {
											boolean methodIsEmpty=true;
											for(int p=0;p<rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().size();p++) {
												List<APIRestressource> rrs= new ArrayList<APIRestressource>();
												for(int m=0;m<l1.get(j).getClasses().get(k).getMethods().size();m++) {
													if(l1.get(j).getClasses().get(k).getMethods().get(m).getNomMethod().equals( rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().get(p).getNomMethode()) ) {
														boolean found=false;
														if (l1.get(j).getClasses().get(k).getMethods().get(m).getResources() != null) {
															for(int q=0;q<l1.get(j).getClasses().get(k).getMethods().get(m).getResources().size();q++) {
																if( l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getUrlRessource().toUpperCase().contains(f.getUrlRessource().toUpperCase()) ) {
																	methodIsEmpty=false;
																	found=true;
																	APIRestressource rr= new APIRestressource();
																	rr.setIdAPIRestressource(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getIdRessource());													
																	rr.setApi(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getUrlApi());
																	rr.setRessource(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getUrlRessource());
																	rr.setVerbHTTP(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getVerbHttp());
																	rrs.add(rr);
																}
															}
														}
														if(!found) {
															methToDel.add(rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().get(p).getIdAPIRestmethod());	
														}
													}
												}
												if(!methodIsEmpty ) {
													rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().get(p).setAPIRestresources(rrs);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		for(int a=0;a<methToDel.size();a++) {
			for(int b=0;b<rl.getAPIRestprojects().size();b++) {
				for(int c=0;c<rl.getAPIRestprojects().get(b).getAPIRestclasses().size();c++) {
					int d=0;
					boolean found=false;
					while(d<rl.getAPIRestprojects().get(b).getAPIRestclasses().get(c).getAPIRestmethods().size() && !found) {
						if(methToDel.get(a).equals(rl.getAPIRestprojects().get(b).getAPIRestclasses().get(c).getAPIRestmethods().get(d).getIdAPIRestmethod())) {
							rl.getAPIRestprojects().get(b).getAPIRestclasses().get(c).getAPIRestmethods().remove(rl.getAPIRestprojects().get(b).getAPIRestclasses().get(c).getAPIRestmethods().get(d));
							found=true;
						}
						d++;
					}
				}
			}	
		} 
		ArrayList<String> classToDel = new ArrayList<String>();
		for(int k=0;k<rl.getAPIRestprojects().size();k++) {
			for(int m=0;m<rl.getAPIRestprojects().get(k).getAPIRestclasses().size();m++) {
				if(rl.getAPIRestprojects().get(k).getAPIRestclasses().get(m).getAPIRestmethods().size() == 0) {
					classToDel.add(rl.getAPIRestprojects().get(k).getAPIRestclasses().get(m).getIdAPIRestclass());	
				}
			}
		}
		for(int k=0;k<classToDel.size();k++) {
			for(int m=0;m<rl.getAPIRestprojects().size();m++) {
				int l=0;
				boolean found=false;
				while(l<rl.getAPIRestprojects().get(m).getAPIRestclasses().size() && !found) {
					if(classToDel.get(k).equals(rl.getAPIRestprojects().get(m).getAPIRestclasses().get(l).getIdAPIRestclass())) {
						rl.getAPIRestprojects().get(m).getAPIRestclasses().remove(rl.getAPIRestprojects().get(m).getAPIRestclasses().get(l));
						found=true;
					}
					l++;
				}
			}
		}
		ArrayList<String> projToDel = new ArrayList<String>();
		for(int i=0;i<rl.getAPIRestprojects().size();i++) {
			if(rl.getAPIRestprojects().get(i).getAPIRestclasses().size() == 0) {
				projToDel.add(rl.getAPIRestprojects().get(i).getIdAPIRestproject());	
			}
		}
		for(int i=0;i<projToDel.size();i++) {
			int j=0;
			boolean found=false;
			while(j<rl.getAPIRestprojects().size() && !found) {
				if(projToDel.get(i).equals(rl.getAPIRestprojects().get(j).getIdAPIRestproject())) {
					rl.getAPIRestprojects().remove(rl.getAPIRestprojects().get(j));
					found=true;
				}
				j++;
			}
		}
		return rl;
	}
	
	@Override
	public Details searchAPIDetails (SearchDetailsCritiria f) {
		List<Projet> l1 = getDocumentations();
		Details d = new Details();
		int i=0;
		boolean foundp=false;
		while(i<l1.size() && !foundp) {
			if(l1.get(i).getIdProject().toUpperCase().equals(f.getIdProjet().toUpperCase())) {
				foundp=true;
				int j=0;
				boolean foundc=false;
				while(j<l1 .get(i).getClasses().size() && foundp && !foundc) {
					if(l1.get(i).getClasses().get(j).getIdClass().toUpperCase().equals(f.getIdClass().toUpperCase())) {
						foundc=true;
						int k=0;
						boolean foundm=false;
						while(k<l1 .get(i).getClasses().get(j).getMethods().size() && foundc && !foundm) {
							if(l1.get(i).getClasses().get(j).getMethods().get(k).getIdMethod().toUpperCase().equals(f.getIdMethod().toUpperCase())) {
								foundm=true;
								d.setApi(l1.get(i).getClasses().get(j).getMethods().get(k).getResources().get(0).getUrlApi());
								d.setFunctionalErrors(l1.get(i).getClasses().get(j).getMethods().get(k).getFunctionalErrors());
								d.setHttpParams(l1.get(i).getClasses().get(j).getMethods().get(k).getResources().get(0).getHttpParams());
								d.setOeParams(l1.get(i).getClasses().get(j).getMethods().get(k).getOeParams());
								d.setRessource(l1.get(i).getClasses().get(j).getMethods().get(k).getResources().get(0).getUrlRessource());
							}
							k++;
						}
					}
					j++;
				}
			}
			i++;
		}
		return d;
	}
}
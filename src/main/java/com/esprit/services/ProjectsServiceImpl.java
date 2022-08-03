
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
import com.esprit.entities.Classe;
import com.esprit.entities.Projet;
import com.esprit.entities.Projets;
import com.esprit.entities.SearchCritiria;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
					if( l1.get(i).getNomProjet().startsWith(f.getProjet()) ) {
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
			return searchAPIClasses(rl,l1,f) ; 
		}

	}

	@Override
	public APIRest searchAPIClasses( APIRest rl, List<Projet> l1, SearchCritiria f) {
		if(f.getController()=="") {
			for(int i=0;i<rl.getAPIRestprojects().size();i++) {
				List<APIRestclass> rcs = new ArrayList<APIRestclass>() ;
				for(int j=0;j<l1.size();j++) {
					if(l1.get(j).getNomProjet().startsWith( rl.getAPIRestprojects().get(i).getProjet())) {
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
							if(l1.get(j).getClasses().get(k).getNomClass().startsWith(f.getController()) ) {
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

												if(l1.get(j).getClasses().get(k).getMethods().get(m).getNomMethod().startsWith(f.getMethode()) ) {
													if( 
															( (f.getDescription()!="") && (l1.get(j).getClasses().get(k).getMethods().get(m).getDescription().contains(f.getDescription())) )
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

													if( l1.get(j).getClasses().get(k).getMethods().get(m).getDescription().contains(f.getDescription()) ) {
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
		if(f.getCodeErreur()=="") {



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
												if (l1.get(j).getClasses().get(k).getMethods().get(m).getFunctionalErrors() != null) {
													for(int q=0;q<l1.get(j).getClasses().get(k).getMethods().get(m).getResources().size();q++) {
														APIRestressource rr= new APIRestressource();
														rr.setIdAPIRestressource(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getIdRessource());													
														rr.setApi(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getUrlApi());
														rr.setRessource(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getUrlRessource());
														rr.setVerbHTTP(l1.get(j).getClasses().get(k).getMethods().get(m).getResources().get(q).getVerbHttp());
														rr.toString();
														rrs.add(rr);
														System.out.println("teeeeeeeeeeeeeeeeeeeeeeeeeeeesssst");
													}
												}

											}
										}
										rl.getAPIRestprojects().get(i).getAPIRestclasses().get(o).getAPIRestmethods().get(p).setAPIRestresources(rrs);;

									}

								}


							}

						}

					}
				}

			}




		}
		return rl;
	}

	/*
	@Override
	public APIRest searchAPICriteresHTTP(List<Projet> Lp, SearchCritiria f) {
		// TODO Auto-generated method stub
		return null;
	}

	 */

}
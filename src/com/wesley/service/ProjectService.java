package com.wesley.service;

import java.io.IOException;

import java.util.List;

import javax.faces.context.FacesContext;

import com.wesley.dao.ProjectDao;
import com.wesley.model.Project;



public class ProjectService {
	
	ProjectDao projectDao = new ProjectDao();
	
	public ProjectService(){
		projectDao= new ProjectDao();
	}
	
	public List<Project> getAll( ) {
		return projectDao.getAll();
	}
	
	public List<Project> getAlls(String name ) {
		return projectDao.getAlls(name);
	}
	
	public void findById (int project){
		 projectDao.selectFromId(project);
	}
	
	
	public void save (Project project){
		 projectDao.save(project);
		 try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("main.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete (int restaurantId){
		
		//Project prj = 	projectDao.selectFromId(restaurantId);	
		
		projectDao.delete(restaurantId);
			System.out.println("success");
		
		 
	}
	
	public void update (Project project){
		Project prj = 	projectDao.selectFromId(project.getId());
		if(prj !=null){
			projectDao.update(project);
		}
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("main.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}

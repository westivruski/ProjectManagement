package com.wesley.managedbean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.wesley.model.Project;
import com.wesley.service.ProjectService;




@ManagedBean(name = "projectBean")
@ViewScoped
public class ProjectBean implements Serializable {

	private static final long serialVersionUID = 8841400873178515754L;

	private String name;
	private String description;
	private Timestamp createdtime;
	
	private List<Project> projects;
	Project project = new Project();

	
	private ProjectService projectService;
	
	
	 

	@PostConstruct
	public void notInit() {
		projectService = new ProjectService();
		projects = projectService.getAll();
		
	}




	public void search(){
		projects = projectService.getAlls(name); 
	}

	public List<Project> getAllProjects() {
		return projects;
	}
	
	public void save (){
		projectService.save(project);
		notInit();
	}
	
	
	public void find (int id){
		projectService.findById(id);
		notInit();
	}
	
	public void delete (int id){
		try{
			projectService.delete(id);
			notInit();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succesfully Deleted!", "Table Updated"));
		}catch (RuntimeException e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
	}
		
		
	}
	public void update (){
		projectService.update(project);
		notInit();
	}
	
	
	 public void onRowEdit(RowEditEvent event) {
		 
		    Project us= (Project) event.getObject();
		    System.out.println("Project edit"+us);
		    projectService.update(us);
		 
	        FacesMessage msg = new FacesMessage("Project edited successfully!");
	        FacesContext.getCurrentInstance().addMessage(null, msg);

	    }
	     
	    public void onRowCancel(RowEditEvent event) {
	        FacesMessage msg = new FacesMessage("Row edit canceled");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	
	
	
	   
	
	
	
	
	
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public ProjectService getProjectService() {
		return projectService;
	}
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(Timestamp createdTime) {
		this.createdtime = createdTime;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	

	
}

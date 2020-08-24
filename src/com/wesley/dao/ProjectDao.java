package com.wesley.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;


import com.wesley.model.Project;
import com.wesley.utils.DatabaseConnectionManager;

public class ProjectDao {
	

	private static final String SearchQUERY = "Select project.name,project.description,project.createdtime,project.modifiedtime,project.email from project where name LIKE LOWER(?)";
	private static final String SelectQUERY = "Select * From project ";
	private static final String DeleteQUERY = "delete from project where id = ? and createdtime < (NOW() - INTERVAL '00:30:00')";
	private static final String CreateQUERY = "insert into project(name,description,email,createdtime) values (?,?,?,?)";
	private static final String UpdateQUERY = "update project set name=?,modifiedtime=?,email=? where id=?";
	private static final String FindQUERY = "select createdtime from project where id = ?";
	

	
	
	//read
	 public List <Project> getAlls(String name) {
			List<Project> projects = new ArrayList<>();
			try (Connection connection = DatabaseConnectionManager.getConnection();
					PreparedStatement statement = connection.prepareStatement(SearchQUERY);) {
				
				
				
				statement.setString(1, name+"%");
			/*	statement.setString(2, description);
				statement.setTimestamp(2, createdtime);*/
			
				
				
				System.out.println(statement);
				ResultSet result = statement.executeQuery();

				while (result.next()) {
					Project project = new Project();
				
					project.setName(result.getString("name"));
					project.setDescription(result.getString("description"));
					project.setCreatedTime(result.getTimestamp("createdtime"));
					project.setEmail(result.getString("email"));
					project.setModifiedTime(result.getTimestamp("modifiedtime"));
				
					projects.add(project);
					System.out.println(project);
				}

			} catch (Exception ex) {
				System.out.println("Something went wrong ");
				ex.printStackTrace();
			}
			return projects;
		}
	    
	//find by id
	    public Project editCell(int restaurantId) {
	        Project selectRecord = new Project();
	        System.out.println("editRestaurantRecordInDB() : Restaurant Id: " + restaurantId);
	 
	        try (Connection connection = DatabaseConnectionManager.getConnection();      
	            	PreparedStatement statement =connection.prepareStatement(FindQUERY);){
	        	statement.setInt(1, restaurantId);
	           ResultSet rs = statement.executeQuery();
	            if(rs != null){
	            rs.next();  
	            selectRecord.setName(rs.getString("name"));  
	            selectRecord.setDescription(rs.getString("address"));  
	            selectRecord.setCreatedTime(rs.getTimestamp("Cdate"));    
	            selectRecord.setModifiedTime(rs.getTimestamp("Mdate"));
	            }
	            
	        } catch(Exception sqlException) {
	            sqlException.printStackTrace();
	        }
			return selectRecord;
	    }
	 
	 
	 
	 public List <Project> getAll() {
			List<Project> projects = new ArrayList<>();
			try (Connection connection = DatabaseConnectionManager.getConnection();
					PreparedStatement statement = connection.prepareStatement(SelectQUERY);) {
				
				System.out.println(statement);
				ResultSet result = statement.executeQuery();

				while (result.next()) {
					Project project = new Project();
					project.setId(result.getInt("id"));
					project.setName(result.getString("name"));
					project.setDescription(result.getString("description"));
					project.setCreatedTime(result.getTimestamp("createdtime"));
					project.setEmail(result.getString("email"));
					project.setModifiedTime(result.getTimestamp("modifiedtime"));
				
					projects.add(project);
					System.out.println(project);
				}

			} catch (Exception ex) {
				System.out.println("Something went wrong ");
				ex.printStackTrace();
			}
			return projects;
		}
	    
	 
 //create
    public  void save(Project project) {
  System.out.println("saveeeee");
        try (Connection connection = DatabaseConnectionManager.getConnection();      
        	PreparedStatement statement =connection.prepareStatement(CreateQUERY);){  
        	
        	java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        	
        	statement.setString(1,project.getName());  
        	statement.setString(2,project.getDescription());
        	statement.setString(3,project.getEmail());
        	statement.setTimestamp(4,date);
         
        	System.out.println("saved success");
        	
        	statement.executeUpdate();  
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        System.out.println("end of save mtehod");
    }
 
    
   
//delete
    public void  delete(int restaurantId){
        
    	System.out.println("deleteRestaurantRecordInDB() : Restaurant Id: " + restaurantId);
        
    	try (Connection connection = DatabaseConnectionManager.getConnection();      
            	PreparedStatement statement =connection.prepareStatement(DeleteQUERY);){
    		statement.setInt(1, restaurantId);
    		statement.executeUpdate();  
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }  
    }
    
    
    //find by id
    public Project selectFromId(int restaurantId) {
        Project selectRecord = new Project();
        System.out.println("editRestaurantRecordInDB() : Restaurant Id: " + restaurantId);
 
        try (Connection connection = DatabaseConnectionManager.getConnection();      
            	PreparedStatement statement =connection.prepareStatement(FindQUERY);){
        	
        	statement.setInt(1, restaurantId);
        	
           ResultSet rs = statement.executeQuery();
            if(rs != null){
            rs.next();  
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            
            selectRecord.setCreatedTime(date);    
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
		return selectRecord;
    }

 
    //update
    public void update (Project project) {
    	 try (Connection connection = DatabaseConnectionManager.getConnection();      
             	PreparedStatement statement =connection.prepareStatement(UpdateQUERY);){
    		 java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
    		 
    		 statement.setString(1,project.getName());                
    		 statement.setTimestamp(2, date);
    		 statement.setString(3,project.getEmail());  
    		 statement.setInt(4, project.getId());
    		 statement.executeUpdate();    
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
       
    }
    
    

   
 
   
}


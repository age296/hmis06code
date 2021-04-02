package com.example.test.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class userconection {
	private Statement stmt;
	private Connection con;
	private ResultSet rs;
	
	public userconection() {
		
	}
	
	
	 public ArrayList<Customer> findAll(){
		 createConection();
		 ArrayList<Customer> result = new ArrayList<Customer>();
		 try {		 
		 rs=stmt.executeQuery("select * from customers");  
			while(rs.next())  {
				result.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(3)));
			}
			
		 }catch(Exception e){ 
				System.out.println(e);  
			} 
		 closeConection();
		 return result;
	 }
	 
	 public boolean userExists(String name) {
		 for(Customer c: findAll()) {
			 if(c.getFirstName().equals(name)) {
				 return true;
			 }
		 }
		 return false;
	 }
	 public Customer getUser(String name) {
		 for(Customer c: findAll()) {
			 if(c.getFirstName().equals(name)) {
				 return c;
			 }
		 }
		 return null;
	 }
	 public  boolean addUser(String firstName, String email, String password) {
		 createConection();
		 try {
		//	stmt.executeUpdate("insert into customers(id ,first_name, password, email) values (2,"+ +","+password+","+email+")");
			stmt.executeUpdate("INSERT INTO customers (id ,first_name, password, email) VALUES (2, '"+firstName+"', '"+password+"', '"+email+"')");
		} catch (SQLException e) {
			
			return false;
		}
		// closeConection();
		 return true;
	 }
	 public  boolean updateEmail(String firstName, String newEmail) {
		 createConection();
		 try {
		//	stmt.executeUpdate("insert into customers(id ,first_name, password, email) values (2,"+ +","+password+","+email+")");
			
			 stmt.executeUpdate("UPDATE customers SET email='"+newEmail+"' WHERE first_name='"+firstName+"'");
		} catch (SQLException e) {
			return false;
		}
		// closeConection();
		 return true;
	 }
	 public  boolean updatePassword(String firstName, String newPassword) {
		 createConection();
		 try {
		//	stmt.executeUpdate("insert into customers(id ,first_name, password, email) values (2,"+ +","+password+","+email+")");
			
			 stmt.executeUpdate("UPDATE customers SET password='"+newPassword+"' WHERE first_name='"+firstName+"'");
		} catch (SQLException e) {
			return false;
		}
		// closeConection();
		 return true;
	 }
	 public void createConection() {
		 try {
		    	Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/demo","admin","admin");  
				//here sonoo is database name, root is username and password  
				stmt=con.createStatement();  
		}catch(Exception e){ 
				System.out.println(e);  
			} 
	 }
	 
	 public void closeConection() {
		try {
			if(con != null)
				con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	 }
	 
}

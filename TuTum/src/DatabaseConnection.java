import java.sql.*;
import java.util.*;

// Class used to create a connection to a database
public class DatabaseConnection {
	
	// Fields
	
	// FEILDS TO CONNECT TO THE DATABASE
	private String connectionUrl = "jdbc:sqlserver://localhost:1401;database=TUTUM_TEST;"
			+ "user=SA;password=Ph11lycode2018";
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	  
	// Constructor for Database Connection for the application
	public DatabaseConnection() {
	  	establishConnection();
	}
	  
	// Method to establish a Connection to the Database
	public void establishConnection() {
		System.out.println("Establishing a Connection to the Database...");
	  	try {
	  		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	  		conn = DriverManager.getConnection(connectionUrl);
	  		System.out.println("Connection Esablished to Database");
	  	} catch (Exception e) {
	  		e.printStackTrace();
	  	}
	}
	
	
	// API LOGIC
	
	// TEST METHOD
	public void testConnection(){
		String request = "delete test_table";
		try {
    		System.out.println("Inserting Assignment to database...");
			stmt = conn.createStatement();
			stmt.executeQuery(request);
			System.out.println("Insert Assignment Complete");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	// LOGIN LOGIC \\
	
	// GET USER QUERYS \\
	// GET TEACHERS
	public ArrayList<Teacher> getTeachersFromDB(){
		String q = "SELECT * FROM TEACHER_USER";
		ArrayList<Teacher> queryResults = new ArrayList<Teacher>();
		try {
    		System.out.println("Getting Teachers from database...");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(q);
			while(rs.next()) {
    			String id = rs.getString("TEACHER_ID");
    			String fn = rs.getString("FIRST_NAME");
    			String ln = rs.getString("LAST_NAME");
    			String room = rs.getString("COURSE_ID");
    			queryResults.add(new Teacher(id, fn, ln, room));
    		}
			System.out.println("Teacher data aquired");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return queryResults;
	}
	// GET GUARDS
	public ArrayList<SecurityGuard> getGuardsFromDB(){
		String q = "SELECT * FROM SECURITY_USER";
		ArrayList<SecurityGuard> queryResults = new ArrayList<SecurityGuard>();
		try {
    		System.out.println("Getting Guard from database...");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(q);
			while(rs.next()) {
    			String id = rs.getString("SECURITY_ID");
    			String fn = rs.getString("FIRST_NAME");
    			String ln = rs.getString("LAST_NAME");
    			queryResults.add(new SecurityGuard(id, fn, ln));
    		}
			System.out.println("Guard data aquired");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return queryResults;
	}
	
	// GET STUDENTS
	public ArrayList<Student> getStudentsFromDB(){
		String q = "SELECT * FROM STUDENT";
		ArrayList<Student> queryResults = new ArrayList<Student>();
		try {
    		System.out.println("Getting Student from database...");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(q);
			while(rs.next()) {
    			String id = rs.getString("STUDENT_ID");
    			String fn = rs.getString("FIRST_NAME");
    			String ln = rs.getString("LAST_NAME");
    			String cr = rs.getString("CURRENT_ROOM");
    			String pr = rs.getString("PREVIOUS_ROOM");
    			boolean f = Boolean.getBoolean(rs.getString("STATUS_FLAG"));
    			boolean ab = Boolean.getBoolean(rs.getString("ABSENT_FLAG"));
    			queryResults.add(new Student(id, fn, ln, cr, pr, f, ab));
    		}
			System.out.println("Student data aquired");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return queryResults;
	}
}

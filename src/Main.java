import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

	public static void main(String[] args) {

		String user = "tidaa_temp"; // user name
		String pwd = "UqrAZdxNZXF8nPwc"; // password 
		String database = "tidaa_temp"; // The name of the specific database in your DBMS
        String server = 
			"jdbc:mysql://178.78.213.33:3306/" + database +
			"?UseClientEnc=UTF8"; 
        
        Connection con = null;
        try {	
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(server, user, pwd);
			System.out.println("Connected!");

			executeQuery(con, "SELECT * FROM Employee");
        }
		catch(Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, 
				"Database error, " + e.toString());
		}
        finally {
        	try {
        		if(con != null) {
        			con.close();
        			System.out.println("Connection closed.");
        		}
        	} 
        	catch(SQLException e) {}
        }
	}
	
	
	public static void executeQuery(Connection con, String query) throws SQLException {
		
		Statement stmt = null;
	    try {
	    	// Execute the SQL statement
	    	stmt = con.createStatement();
	    	ResultSet rs = stmt.executeQuery(query);
	    	
	    	// Get the attribute names
	    	ResultSetMetaData metaData = rs.getMetaData();
	    	int ccount = metaData.getColumnCount();
	    	for(int c = 1; c <= ccount; c++) {
	    		System.out.print(metaData.getColumnName(c) + "\t");
	    	}
	    	System.out.println();
	    	
	    	// Get the attribute values
	    	while (rs.next()) {
    			// NB! This is an example, not the preferred way to retrieve data.
    			// You should use methods that return a specific data type, like
    			// rs.getInt(), rs.getString() or such.
    			// It's also advisable to store each tuple (row) in an object of
    			// custom type (e.g. Employee).
	    		for(int c = 1; c <= ccount; c++) {
	    			System.out.print(rs.getObject(c) + "\t");
	    		}
		        System.out.println();
	    	}
	    	
	    }	
	    finally {
	    	if (stmt != null) { 
	    		stmt.close(); 
	    	}
	    }
	}
}

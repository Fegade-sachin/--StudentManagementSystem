package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection 
{

    public static Connection getConnection() {

        Connection con = null;
	
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","manager");
	
		} catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		return con;
	
	}
}

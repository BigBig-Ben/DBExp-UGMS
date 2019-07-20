package source.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String URL="jdbc:sqlserver://localhost:1433;DatabaseName=zhouzbMIS03";
	private static String username="sa";
	private static String password="zhou660990";
	
	public static Connection open()
	{
		try
		{
			Class.forName(driver);
			//System.out.println("open");
			return DriverManager.getConnection(URL, username, password);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void close(Connection conn)
	{
		try {
			if(conn!=null) 
				conn.close();
			//System.out.println("close");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

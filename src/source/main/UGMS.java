package source.main;

import java.sql.Connection;
import java.sql.DriverManager;

import source.DAO.DBUtil;
import source.GUI.*;

public class UGMS {
	
	static void GUIInit()		//GUI
	{
		Login l = new Login();
		//Student s = new Student("zzb");
	}
	
	public static void main(String args[])
	{
		GUIInit();
//		Connection conn = DBUtil.open();
//		DBUtil.close(conn);
	}
}

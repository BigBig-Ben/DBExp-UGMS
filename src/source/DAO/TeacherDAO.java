package source.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import source.Entity.Course;
import source.Entity.Grade;
import source.Entity.Student;
import source.Entity.Teacher;

public class TeacherDAO {
	public static boolean login(String username, String password)
	{
		Connection conn = DBUtil.open();
		String sql = "select zzb_Tpwd03 from zhouzb_Teacher03 where zzb_Tno03 = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				String pwd = rs.getString(1);
				if(pwd.equals(password))
					return true;
				else return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return false;
	}

	public static Teacher getTeacherById(String username)
	{
		String sql = "select * from zhouzb_Teacher03 where zzb_Tno03 = ?";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				Teacher t = new Teacher();
				t.setTno(rs.getString(1));
				t.setTname(rs.getString(2));
				t.setTsex(rs.getInt(3));
				t.setTage(rs.getInt(4));
				t.setTrank(rs.getString(5));
				t.setTtel(rs.getString(6));
				t.setTpwd(rs.getString(7));
				return t;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}

	public static void changePwd(String username, String pwd) {
		String sql = "update zhouzb_Teacher03 set zzb_Tpwd03 = ? where zzb_Tno03 = ?";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, username);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	public static List<String> selectTermsC()
	{
		List<String> list = new ArrayList();
		String sql = "select DISTINCT zzb_Cterm03 from zhouzb_view_Reports";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				list.add(rs.getString(1));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}	
		return null;
	}
	
	public static List<Course> getCourse(String term, String tno)
	{
		List<Course> courses = new ArrayList();
		String sql = "select * from zhouzb_view_Reports where zzb_Cterm03 = ? and zzb_Tno03 = ?";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, term);
			pstmt.setString(2, tno);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Course c = new Course();
				c.setCno(rs.getString(1));
				c.setCterm(rs.getString(2));
				c.setClno(rs.getString(3));
				c.setTno(rs.getString(4));
				c.setClname(rs.getString(5));
				c.setCname(rs.getString(6));
				c.setTname(rs.getString(7));
				c.setCtime(rs.getInt(8));
				c.setCcredit(rs.getInt(9));
				c.setCtype(rs.getInt(10));
				courses.add(c);
			}
			return courses;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;	
	}
	
	public static List<Course> getAllCourse()
	{
		List<Course> courses = new ArrayList();
		String sql = "select * from zhouzb_view_Reports ";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Course c = new Course();
				c.setCno(rs.getString(1));
				c.setCterm(rs.getString(2));
				c.setClno(rs.getString(3));
				c.setTno(rs.getString(4));
				c.setClname(rs.getString(5));
				c.setCname(rs.getString(6));
				c.setTname(rs.getString(7));
				c.setCtime(rs.getInt(8));
				c.setCcredit(rs.getInt(9));
				c.setCtype(rs.getInt(10));
				courses.add(c);
			}
			return courses;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;	
	}
	
	public static List<Grade> getGradeByTerm(String tno, String term)
	{
		List<Grade> grades = new ArrayList();
		String sql = "select * from zhouzb_view_Grade where zzb_Tno03 = ?";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tno);
			pstmt.setString(2, term);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Grade g = new Grade();
				g.setSno(rs.getString(1));
				g.setCno(rs.getString(2));
				g.setCterm(rs.getString(3));
				g.setGrade(rs.getInt(4));
				g.setSname(rs.getString(5));
				g.setClname(rs.getString(6));
				g.setTname(rs.getString(7));
				g.setCname(rs.getString(8));
				g.setTno(rs.getString(9));
				grades.add(g);
			}
			return grades;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}
	
	public static List<String> getCname(String term, String tno)
	{
		List<String> courses = new ArrayList();
		String sql = "select DISTINCT zzb_Cname03 from zhouzb_view_Reports where zzb_Cterm03 = ? and zzb_Tno03 = ?";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, term);
			pstmt.setString(2, tno);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				courses.add(rs.getString(1));
			}
			return courses;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;	
	}
	
	public static List<Grade> getGradeByCourse(String tno, String term, String cname)
	{
		List<Grade> grades = new ArrayList();
		String sql = "select * from zhouzb_view_Grade where zzb_Tno03 = ? and zzb_Cterm03 = ? and zzb_Cname03 = ? order by zzb_Grade03 DESC";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tno);
			pstmt.setString(2, term);
			pstmt.setString(3, cname);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Grade g = new Grade();
				g.setSno(rs.getString(1));
				g.setCno(rs.getString(2));
				g.setCterm(rs.getString(3));
				g.setGrade(rs.getInt(4));
				g.setSname(rs.getString(5));
				g.setClname(rs.getString(6));
				g.setTname(rs.getString(7));
				g.setCname(rs.getString(8));
				g.setTno(rs.getString(9));
				grades.add(g);
			}
			return grades;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}
	
	public static void updateGrade(String sno, String cno, String term, String score)
	{
		String sql = "update zhouzb_Grade03 set zzb_Grade03 = ? where zzb_Sno03 = ? and zzb_Cno03 = ? and zzb_Cterm03 = ?";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, score);
			pstmt.setString(2, sno);
			pstmt.setString(3, cno);
			pstmt.setString(4, term);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
}

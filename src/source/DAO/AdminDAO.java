package source.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import source.Entity.Admin;
import source.Entity.Class_;
import source.Entity.Course;
import source.Entity.Major;
import source.Entity.Student;
import source.Entity.Teacher;

public class AdminDAO {
	public static boolean login(String username, String password)
	{
		Connection conn = DBUtil.open();
		String sql = "select * from zhouzb_Admin03 where zzb_Ano03 = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				String user = rs.getString(1);
				String pwd = rs.getString(2);
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
	
	public static List<Major> getAllMajors()
	{
		List<Major> majors = new ArrayList();
		String sql = "select * from zhouzb_Major03";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while(rs.next())
			{
				Major m = new Major();
				m.setMno(rs.getString(1));
				m.setMname(rs.getString(2));
				majors.add(m);
			}
			return majors;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}

	public static void saveMajor(Major m)
	{
		Connection conn = DBUtil.open();
		String sql = "insert into zhouzb_Major03 values(?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMno());
			pstmt.setString(2, m.getMname());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	public static void updateMajor(Major m)
	{
		Connection conn = DBUtil.open();
		String sql = "update zhouzb_Major03 set zzb_Mname03 = ? where zzb_Mno03 = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMname());
			pstmt.setString(2, m.getMno());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	public static void deleteMajor(String mno)
	{
		Connection conn = DBUtil.open();
		String sql = "{call zhouzb_DeleteMajor( ? )}";
		try {
			CallableStatement stm = conn.prepareCall(sql);
			stm.setString(1, mno);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	public static List<Class_> getAllClass()
	{
		List<Class_> classes = new ArrayList();
		String sql = "select * from zhouzb_view_Class";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while(rs.next())
			{
				Class_ s = new Class_();
				s.setClno(rs.getString(1));
				s.setMno(rs.getString(2));
				s.setClname(rs.getString(3));
				s.setMname(rs.getString(4));
				classes.add(s);
			}
			return classes;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}

	public static void saveClass(Class_ c)
	{
		Connection conn = DBUtil.open();
		String sql = "insert into zhouzb_Class03 values(?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getClno());
			pstmt.setString(2, c.getMno());
			pstmt.setString(3, c.getClname());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	public static void updateClass(Class_ c)
	{
		Connection conn = DBUtil.open();
		String sql = "update zhouzb_Class03 set zzb_Clname03 = ? where zzb_Clno03 = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getClname());
			pstmt.setString(2, c.getClno());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	public static void deleteClass(String clno)
	{
		Connection conn = DBUtil.open();
		String sql = "{call zhouzb_DeleteClass( ? )}";
		try {
			CallableStatement stm = conn.prepareCall(sql);
			stm.setString(1, clno);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	public static List<Course> getAllCourse()
	{
		List<Course> courses = new ArrayList();
		String sql = "select * from zhouzb_view_Reports";
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
	
	public static void saveCourse(Course c)
	{
		Connection conn = DBUtil.open();
		String sql = "insert into zhouzb_Course03 values(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getCno());
			pstmt.setString(2, c.getCterm());
			pstmt.setString(3, c.getCname());
			pstmt.setInt(4, c.getCtime());
			pstmt.setInt(5, c.getCcredit());
			pstmt.setInt(6, c.getCtype());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	public static void updateCourse(Course c)
	{
		Connection conn = DBUtil.open();
		String sql = "update zhouzb_Course03 set zzb_Cname03 = ? , zzb_Cterm03 = ?, "
				+ "zzb_Ctime03 = ?, zzb_Ccredit03 = ?, zzb_Ctype03 = ? where zzb_Clno03 = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getClname());
			pstmt.setString(2, c.getCterm());
			pstmt.setInt(3, c.getCtime());
			pstmt.setInt(4, c.getCcredit());
			pstmt.setInt(5, c.getCtype());
			pstmt.setString(6, c.getClno());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	public static void deleteCourse(String cno)
	{
		Connection conn = DBUtil.open();
		String sql = "{call zhouzb_DeleteCourse( ? )}";
		try {
			CallableStatement stm = conn.prepareCall(sql);
			stm.setString(1, cno);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	public static void saveReport(Course c)
	{
		Connection conn = DBUtil.open();
		String sql = "insert into zhouzb_Reports03 values(?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getCno());
			pstmt.setString(2, c.getCterm());
			pstmt.setString(3, c.getClno());
			pstmt.setString(4, c.getTno());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	public static void deleteReport(Course c)
	{
		Connection conn = DBUtil.open();
		String sql = "delete from zhouzb_Reports03 where zzb_Cno03 = ? and zzb_Cterm03 = ?"
				+ "zzb_Clno03 = ? and zzb_Tno03 = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getCno());
			pstmt.setString(2, c.getCterm());
			pstmt.setString(3, c.getClno());
			pstmt.setString(4, c.getTno());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	public static List<Teacher> getAllTeacher()
	{
		List<Teacher> teachers = new ArrayList();
		String sql = "select * from zhouzb_Teacher03 ";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Teacher t = new Teacher();
				t.setTno(rs.getString(1));
				t.setTname(rs.getString(2));
				t.setTsex(rs.getInt(3));
				t.setTage(rs.getInt(4));
				t.setTrank(rs.getString(5));
				t.setTtel(rs.getString(6));
				t.setTpwd(rs.getString(7));
				teachers.add(t);
			}
			return teachers;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}
	
	public static void saveTeacher(Teacher t)
	{
		Connection conn = DBUtil.open();
		String sql = "insert into zhouzb_Teacher03 values(?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTno());
			pstmt.setString(2, t.getTname());
			pstmt.setInt(3, t.getTsex());
			pstmt.setInt(4, t.getTage());
			pstmt.setString(5, t.getTrank());
			pstmt.setString(6, t.getTtel());
			pstmt.setString(7, t.getTpwd());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	public static void updateTeacher(Teacher t)
	{
		Connection conn = DBUtil.open();
		String sql = "update zhouzb_Teacher03 set zzb_Tname03 = ? , zzb_Tsex03 = ? , "
				+ "zzb_Tage03 = ? , zzb_Trank03 = ? , zzb_Ttel03 = ? , zzb_Tpwd03 = ?"
				+ "where zzb_Tno03 = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTname());
			pstmt.setInt(2, t.getTsex());
			pstmt.setInt(3, t.getTage());
			pstmt.setString(4, t.getTrank());
			pstmt.setString(5, t.getTtel());
			pstmt.setString(6, t.getTpwd());
			pstmt.setString(7, t.getTno());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	public static void deleteTeacher(String tno)
	{
		Connection conn = DBUtil.open();
		String sql = "{call zhouzb_DeleteTeacher( ? )}";
		try {
			CallableStatement stm = conn.prepareCall(sql);
			stm.setString(1, tno);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	public static List<Student> getAllStudent()
	{
		List<Student> stus = new ArrayList();
		String sql = "select * from zhouzb_view_Student";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Student stu = new Student();
				stu.setSno(rs.getString(1));
				stu.setClno(rs.getString(2));
				stu.setSname(rs.getString(3));
				stu.setSsex(rs.getInt(4));
				stu.setSage(rs.getInt(5));
				stu.setShome(rs.getString(6));
				stu.setScredit(rs.getInt(7));
				stu.setSpwd(rs.getString(8));
				stu.setClname(rs.getString(9));
				stus.add(stu);
			}
			return stus;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}

	public static void saveStudent(Student stu)
	{
		Connection conn = DBUtil.open();
		String sql = "insert into zhouzb_Student03 values(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stu.getSno());
			pstmt.setString(2, stu.getClno());
			pstmt.setString(3, stu.getSname());
			pstmt.setInt(4, stu.getSsex());
			pstmt.setInt(5, stu.getSage());
			pstmt.setString(6, stu.getShome());
			pstmt.setInt(7, stu.getScredit());
			pstmt.setString(8, stu.getSpwd());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	public static void updateStudent(Student stu)
	{
		Connection conn = DBUtil.open();
		String sql = "update zhouzb_Student03 set zzb_Clno03 = ?, zzb_Sname03 = ?, zzb_Ssex03 = ?,"
				+ "zzb_Sage03 = ?, zzb_Shome03 = ?, zzb_Scredit03 = ?, zzb_Spwd03 = ? "
				+ "where zzb_Sno03 = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stu.getClno());
			pstmt.setString(2, stu.getSname());
			pstmt.setInt(3, stu.getSsex());
			pstmt.setInt(4, stu.getSage());
			pstmt.setString(5, stu.getShome());
			pstmt.setInt(6, stu.getScredit());
			pstmt.setString(7, stu.getSpwd());
			pstmt.setString(8, stu.getSno());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	public static void deleteStudent(String sno)
	{
		Connection conn = DBUtil.open();
		String sql = "{call zhouzb_DeleteStu( ? )}";
		try {
			CallableStatement stm = conn.prepareCall(sql);
			stm.setString(1, sno);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	public static Admin getAdminById(String Ano)
	{
		String sql = "select * from zhouzb_Admin03 where zzb_Ano03 = ?";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Ano);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				Admin dba = new Admin();
				dba.setAno(rs.getString(1));
				dba.setApwd(rs.getString(2));
				return dba;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}

	public static void changePwd(String username, String pwd) {
		String sql = "update zhouzb_Admin03 set zzb_Apwd03 = ? where zzb_Ano03 = ?";
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
}

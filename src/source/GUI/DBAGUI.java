package source.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import source.DAO.AdminDAO;
import source.DAO.TeacherDAO;
import source.Entity.Admin;
import source.Entity.Class_;
import source.Entity.Course;
import source.Entity.Major;
import source.Entity.Student;
import source.Entity.Teacher;

public class DBAGUI extends JFrame{
	/**
	 * DBA界面
	 */
	private static final long serialVersionUID = -5810093441099369931L;
	private Font font= new Font("黑体", Font.PLAIN, 20);
	private Font font1= new Font("黑体", Font.PLAIN, 30);
	String username = null;
	static JMenuBar mb = new JMenuBar();
	static FgMenu	major = new FgMenu("专业管理"),
					clas = new FgMenu("班级管理"),
					course = new FgMenu("课程管理"),
					teacher = new FgMenu("教师管理"),
					student = new FgMenu("学生管理"),
					manager = new FgMenu("DBA管理");
	static JMenuItem majorInfo = new JMenuItem("专业信息维护"),
					 clasInfo = new JMenuItem("班级信息维护"),
					 courseInfo = new JMenuItem("课程信息维护"),
					 reportInfo = new JMenuItem("开班信息维护"),
					 teacherInfo = new JMenuItem("教师信息维护"),
					 studentInfo = new JMenuItem("学生信息维护"),
					 DBAInfo = new JMenuItem("DBA信息管理");
	JPanel pan = new JPanel();
	public DBAGUI(String user)
	{
		super("成绩管理系统-DBA-"+user);
		username = user;
		major.add(majorInfo);major.setFont(font);majorInfo.setFont(font);
		clas.add(clasInfo);clas.setFont(font);clasInfo.setFont(font);
		course.add(courseInfo);course.setFont(font);courseInfo.setFont(font);
		course.add(reportInfo);reportInfo.setFont(font);
		teacher.add(teacherInfo);teacher.setFont(font);teacherInfo.setFont(font);
		student.add(studentInfo);student.setFont(font);studentInfo.setFont(font);
		manager.add(DBAInfo);manager.setFont(font);DBAInfo.setFont(font);
		mb.add(major);
		mb.add(clas);
		mb.add(course);
		mb.add(teacher);
		mb.add(student);
		mb.add(manager);
		setJMenuBar(mb);
		pan.setSize(1080, 600);
		this.add(pan);
		setSize(1080, 760);
		centerWindow(this);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		majorInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MajorInfo(e);
			}
		});
		
		clasInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassInfo(e);
			}
		});
		
		courseInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseInfo(e);
			}
		});
		
		reportInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportInfo(e);
			}
		});
		
		teacherInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherInfo(e);
			}
		});
		
		studentInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentInfo(e);
			}
		});
		
		DBAInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminInfo(e);
			}
		});
	}
	
	public void MajorInfo(ActionEvent e)	//专业管理
	{
		pan.removeAll();
		JTable 	table = new JTable();
		JPanel 	tablePan = new JPanel(),
				btnPan = new JPanel();
		JButton btnNew = new JButton("新建专业"),
				btnDel = new JButton("删除专业"),
				btnUpd = new JButton("专业信息更新");
		btnNew.setFont(font);
		btnDel.setFont(font);
		btnUpd.setFont(font);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.addColumn("专业代码");
		tableModel.addColumn("专业名称");
		table.setRowHeight(50);
		table.setRowMargin(5);
		table.setRowSelectionAllowed(true); 
		table.setSelectionBackground(Color.BLUE);		
		table.setSelectionForeground(Color.WHITE);
		table.setGridColor(Color.BLACK); 
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.doLayout();
		table.setBackground(Color.WHITE);
		table.setPreferredScrollableViewportSize(new Dimension(1000,560));
		table.setFont(font1);
		JTableHeader head = table.getTableHeader(); 	// 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));	// 设置表头大小
        head.setFont(font1);							// 设置表格字体
		JScrollPane sp=new JScrollPane(table);
		List<Major> majors = AdminDAO.getAllMajors();
		for(int i=0; i<majors.size(); i++)
		{
			Major m = majors.get(i);
			tableModel.addRow(new Object[] {m.getMno(), m.getMname()});
		}
		btnPan.add(btnNew);btnPan.add(btnDel);btnPan.add(btnUpd);
		pan.add(sp);
		pan.add(btnPan);
		validate();
	    repaint();
	    
	    btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MajorBox mb = new MajorBox("新建专业", null, null, 0, 0, tableModel);
			}
	    });
	    
	    btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				Major m = majors.get(selectedRow);
				tableModel.removeRow(selectedRow);
				AdminDAO.deleteMajor(m.getMno());
			}
	    });
	    
	    btnUpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				Major m = majors.get(selectedRow);
				MajorBox mb = new MajorBox("专业信息更新",m.getMno(), m.getMname(), 1, selectedRow, tableModel);
				sp.validate();
			    sp.repaint();
			}
	    });
	}

	public void ClassInfo(ActionEvent e)	//班级管理
	{
		pan.removeAll();
		JTable 	table = new JTable();
		JPanel 	tablePan = new JPanel(),
				btnPan = new JPanel();
		JButton btnNew = new JButton("新建班级"),
				btnDel = new JButton("删除班级"),
				btnUpd = new JButton("班级信息更新");
		btnNew.setFont(font);
		btnDel.setFont(font);
		btnUpd.setFont(font);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.addColumn("班级代码");
		tableModel.addColumn("专业名称");
		tableModel.addColumn("班级名称");
		table.setRowHeight(50);
		table.setRowMargin(5);
		table.setRowSelectionAllowed(true); 
		table.setSelectionBackground(Color.BLUE);		
		table.setSelectionForeground(Color.WHITE);
		table.setGridColor(Color.BLACK); 
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.doLayout();
		table.setBackground(Color.WHITE);
		table.setPreferredScrollableViewportSize(new Dimension(1000,560));
		table.setFont(font1);
		JTableHeader head = table.getTableHeader(); 	// 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));	// 设置表头大小
        head.setFont(font1);							// 设置表格字体
		JScrollPane sp=new JScrollPane(table);
		List<Class_> classes = AdminDAO.getAllClass();
		for(int i=0; i<classes.size(); i++)
		{
			Class_ c = classes.get(i);
			tableModel.addRow(new Object[] {c.getClno(), c.getMname(), c.getClname()});
		}
		btnPan.add(btnNew);btnPan.add(btnDel);btnPan.add(btnUpd);
		pan.add(sp);
		pan.add(btnPan);
		validate();
	    repaint();
	    
	    btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassBox cb = new ClassBox("新建班级", null, null, null, null, 0, 0, tableModel);
			}
	    });
	    
	    btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				Class_ c = classes.get(selectedRow);
				tableModel.removeRow(selectedRow);
				AdminDAO.deleteClass(c.getClno());
			}
	    });
	    
	    btnUpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				Class_ c = classes.get(selectedRow);
				ClassBox cb = new ClassBox("班级信息更新", c.getMno(), c.getMname(), c.getClno(), c.getClname(), 1, selectedRow, tableModel);
			}
	    });
		
	}
	
	public void CourseInfo(ActionEvent e)	//课程管理
	{
		pan.removeAll();
		JTable 	table = new JTable();
		JPanel 	tablePan = new JPanel(),
				btnPan = new JPanel();
		JButton btnNew = new JButton("新建课程"),
				btnDel = new JButton("删除课程"),
				btnUpd = new JButton("课程信息更新");
		btnNew.setFont(font);
		btnDel.setFont(font);
		btnUpd.setFont(font);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.addColumn("课程代码");
		tableModel.addColumn("开课学期");
		tableModel.addColumn("课程名称");
		tableModel.addColumn("学时");
		tableModel.addColumn("学分");
		tableModel.addColumn("测试类型");
		table.setRowHeight(50);
		table.setRowMargin(5);
		table.setRowSelectionAllowed(true); 
		table.setSelectionBackground(Color.BLUE);		
		table.setSelectionForeground(Color.WHITE);
		table.setGridColor(Color.BLACK); 
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.doLayout();
		table.setBackground(Color.WHITE);
		table.setPreferredScrollableViewportSize(new Dimension(1000,560));
		table.setFont(font1);
		JTableHeader head = table.getTableHeader(); 	// 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));	// 设置表头大小
        head.setFont(font1);							// 设置表格字体
		JScrollPane sp=new JScrollPane(table);
		List<Course> courses = AdminDAO.getAllCourse();
		for(int i=0; i<courses.size(); i++)
		{
			Course c = courses.get(i);
			String type = "考试";
			if(c.getCtype()==0)
				type = "考查";
			tableModel.addRow(new Object[] {c.getCno(), c.getCterm(), c.getCname(), c.getCtime(), c.getCcredit(), type});
		}
		btnPan.add(btnNew);btnPan.add(btnDel);btnPan.add(btnUpd);
		pan.add(sp);
		pan.add(btnPan);
		validate();
	    repaint();
	    
	    btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseBox cb = new CourseBox("新建课程", new Course(), 0, 0, tableModel);
			}
	    });
	    
	    btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				Course c = courses.get(selectedRow);
				tableModel.removeRow(selectedRow);
				AdminDAO.deleteCourse(c.getCno());
			}
	    });
	    
	    btnUpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				Course c = courses.get(selectedRow);
				CourseBox cb = new CourseBox("课程信息更新", c, 1, selectedRow, tableModel);
			}
	    });
		
	}
	
	public void ReportInfo(ActionEvent e)	//开课管理
	{
		pan.removeAll();
		JTable 	table = new JTable();
		JPanel 	tablePan = new JPanel(),
				btnPan = new JPanel();
		JButton btnNew = new JButton("新建开班"),
				btnDel = new JButton("删除开班");
		btnNew.setFont(font);
		btnDel.setFont(font);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.addColumn("开课学期");
		tableModel.addColumn("课程代码");
		tableModel.addColumn("课程名称");
		tableModel.addColumn("班级代码");
		tableModel.addColumn("班级");
		tableModel.addColumn("任课教师");
		TableColumnModel cm=table.getColumnModel();
		cm.getColumn(0).setPreferredWidth(55);			//setting column width
		cm.getColumn(1).setPreferredWidth(75);
		cm.getColumn(2).setPreferredWidth(55);
		cm.getColumn(3).setPreferredWidth(75);
		cm.getColumn(4).setPreferredWidth(270);
		cm.getColumn(5).setPreferredWidth(130);
		table.setRowHeight(50);
		table.setRowMargin(5);
		table.setRowSelectionAllowed(true); 
		table.setSelectionBackground(Color.BLUE);		
		table.setSelectionForeground(Color.WHITE);
		table.setGridColor(Color.BLACK); 
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.doLayout();
		table.setBackground(Color.WHITE);
		table.setPreferredScrollableViewportSize(new Dimension(1000,560));
		table.setFont(font1);
		JTableHeader head = table.getTableHeader(); 	// 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));	// 设置表头大小
        head.setFont(font1);							// 设置表格字体
		JScrollPane sp=new JScrollPane(table);
		List<Course> courses = TeacherDAO.getAllCourse();
		for(int i=0; i<courses.size(); i++)
		{
			Course c = courses.get(i);
			tableModel.addRow(new Object[] {c.getCterm(), c.getCno(), c.getCname(), c.getClno(), c.getClname(), c.getTname()});
		}
		btnPan.add(btnNew);btnPan.add(btnDel);
		pan.add(sp);
		pan.add(btnPan);
		validate();
	    repaint();
	    
	    btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportBox rb = new ReportBox("新建开班", tableModel);
			}
	    });
	    
	    btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				Course c = courses.get(selectedRow);
				tableModel.removeRow(selectedRow);
				AdminDAO.deleteReport(c);
			}
	    });		
	}
	
	public void TeacherInfo(ActionEvent e)	//教师管理
	{
		pan.removeAll();
		JTable 	table = new JTable();
		JPanel 	tablePan = new JPanel(),
				btnPan = new JPanel();
		JButton btnNew = new JButton("新建教师"),
				btnDel = new JButton("删除教师"),
				btnUpd = new JButton("教师信息更新");
		btnNew.setFont(font);
		btnDel.setFont(font);
		btnUpd.setFont(font);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.addColumn("工号");
		tableModel.addColumn("姓名");
		tableModel.addColumn("性别");
		tableModel.addColumn("年龄");
		tableModel.addColumn("职称");
		tableModel.addColumn("电话");
		table.setRowHeight(50);
		table.setRowMargin(5);
		table.setRowSelectionAllowed(true); 
		table.setSelectionBackground(Color.BLUE);		
		table.setSelectionForeground(Color.WHITE);
		table.setGridColor(Color.BLACK); 
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.doLayout();
		table.setBackground(Color.WHITE);
		table.setPreferredScrollableViewportSize(new Dimension(1000,560));
		table.setFont(font1);
		JTableHeader head = table.getTableHeader(); 	// 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));	// 设置表头大小
        head.setFont(font1);							// 设置表格字体
		JScrollPane sp=new JScrollPane(table);
		List<Teacher> teachers = AdminDAO.getAllTeacher();
		for(int i=0; i<teachers.size(); i++)
		{
			Teacher t = teachers.get(i);
			String gender = "男";
			if(t.getTsex()==0)
				gender = "女";
			tableModel.addRow(new Object[] {t.getTno(), t.getTname(), gender, t.getTage(), t.getTrank(), t.getTtel()});
		}
		btnPan.add(btnNew);btnPan.add(btnDel);btnPan.add(btnUpd);
		pan.add(sp);
		pan.add(btnPan);
		validate();
	    repaint();
	    
	    btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame 	frame = new JFrame("新建教师");
				JPanel 	pan = new JPanel();
				JPanel  infoPan = new JPanel(),
						buttonPan =new JPanel();
				JLabel 	noLab =  new JLabel("工号："),
						nameLab = new JLabel("姓名："),
						sexLab = new JLabel("性别："),
						ageLab = new JLabel("年龄："),
						rankLab = new JLabel("职称："),
						telLab = new JLabel("电话："),
						pwdLab = new JLabel("密码：");
				noLab.setFont(font1);
				nameLab.setFont(font1);
				sexLab.setFont(font1);
				ageLab.setFont(font1);
				rankLab.setFont(font1);
				telLab.setFont(font1);
				pwdLab.setFont(font1);
				JTextArea	no = new JTextArea(),
							name = new JTextArea(),
							rank = new JTextArea(),
							sex = new JTextArea(),
							age = new JTextArea(),
							tel = new JTextArea(),
							password = new JTextArea();
				no.setFont(font1);
				name.setFont(font1);
				rank.setFont(font1);
				sex.setFont(font1);
				age.setFont(font1);
				tel.setFont(font1);
				password.setFont(font1);
				JButton save = new JButton("新建教师");
				save.setFont(font1);
				buttonPan.setSize(60, 600);
				GridLayout layout = new GridLayout(7, 2, 10, 10);
				infoPan.setLayout(layout);
				infoPan.add(noLab);		infoPan.add(no);
				infoPan.add(nameLab);	infoPan.add(name);
				infoPan.add(sexLab);	infoPan.add(sex);
				infoPan.add(rankLab);	infoPan.add(rank);
				infoPan.add(ageLab);	infoPan.add(age);
				infoPan.add(telLab);	infoPan.add(tel);
				infoPan.add(pwdLab);	infoPan.add(password);
				buttonPan.add(save);
				GridBagLayout gbl = new GridBagLayout();
				GridBagConstraints gbs = new GridBagConstraints();
				pan.setLayout(gbl);
				gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
				gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;
				gbs.gridx=0;gbs.gridy=0;
				gbl.setConstraints(infoPan, gbs);
				gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
				gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;
				gbs.gridx=0;gbs.gridy=1;
				gbl.setConstraints(buttonPan, gbs);
				pan.add(infoPan);
				pan.add(buttonPan);
				frame.add(pan);
				frame.setSize(600, 400);
				centerWindow(frame);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
				
				save.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		Teacher t = new Teacher();
			    		t.setTno(no.getText()); 	t.setTname(name.getText());
			    		t.setTage(Integer.parseInt(age.getText()));
			    		t.setTtel(tel.getText()); 	t.setTrank(rank.getText());
			    		t.setTpwd(password.getText());
			    		int gender = 1;
			    		if(sex.getText().equals("女"))
			    			gender = 0;
			    		t.setTsex(gender);
			    		AdminDAO.saveTeacher(t);
			    		JOptionPane.showMessageDialog(null, "新建教师成功", "提示", 1);
			    		frame.dispose();
			    	}
			    });
			}
	    });
	    
	    btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				Teacher t = teachers.get(selectedRow);
				tableModel.removeRow(selectedRow);
				AdminDAO.deleteTeacher(t.getTno());
			}
	    });
	    
	    btnUpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				Teacher teacher = teachers.get(selectedRow);
				JFrame  frame = new JFrame("教师信息修改");
				JPanel  pan = new JPanel();
				JPanel  infoPan = new JPanel(),
						buttonPan =new JPanel();
				JLabel 	noLab =  new JLabel("工号："),
						nameLab = new JLabel("姓名："),
						sexLab = new JLabel("性别："),
						ageLab = new JLabel("年龄："),
						rankLab = new JLabel("职称："),
						telLab = new JLabel("电话："),
						pwdLab = new JLabel("密码：");
				noLab.setFont(font1);
				nameLab.setFont(font1);
				sexLab.setFont(font1);
				ageLab.setFont(font1);
				rankLab.setFont(font1);
				telLab.setFont(font1);
				pwdLab.setFont(font1);
				JTextArea	no = new JTextArea(teacher.getTno()),
							name = new JTextArea(teacher.getTname()),
							rank = new JTextArea(teacher.getTrank()),
							sex = new JTextArea("男"),
							age = new JTextArea(String.valueOf(teacher.getTage())),
							tel = new JTextArea(String.valueOf(teacher.getTtel())),
							password = new JTextArea(teacher.getTpwd());
				if(teacher.getTsex()==0)
					sex.setText("女");
				no.setFont(font1);
				name.setFont(font1);
				rank.setFont(font1);
				sex.setFont(font1);
				age.setFont(font1);
				tel.setFont(font1);
				password.setFont(font1);
				JButton save = new JButton("保存");
				save.setFont(font1);
				buttonPan.setSize(60, 600);
				GridLayout layout = new GridLayout(7, 2, 10, 10);
				infoPan.setLayout(layout);
				infoPan.add(noLab);		infoPan.add(no);
				infoPan.add(nameLab);	infoPan.add(name);
				infoPan.add(sexLab);	infoPan.add(sex);
				infoPan.add(rankLab);	infoPan.add(rank);
				infoPan.add(ageLab);	infoPan.add(age);
				infoPan.add(telLab);	infoPan.add(tel);
				infoPan.add(pwdLab);	infoPan.add(password);
				buttonPan.add(save);
				GridBagLayout gbl = new GridBagLayout();
				GridBagConstraints gbs = new GridBagConstraints();
				pan.setLayout(gbl);
				gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
				gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;
				gbs.gridx=0;gbs.gridy=0;
				gbl.setConstraints(infoPan, gbs);
				gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
				gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;
				gbs.gridx=0;gbs.gridy=1;
				gbl.setConstraints(buttonPan, gbs);
				pan.add(infoPan);
				pan.add(buttonPan);
				frame.add(pan);
				frame.setSize(600, 400);
				centerWindow(frame);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);

				save.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		Teacher t = new Teacher();
			    		t.setTno(no.getText()); 	t.setTname(name.getText());
			    		t.setTage(Integer.parseInt(age.getText()));
			    		t.setTtel(tel.getText()); 	t.setTrank(rank.getText());
			    		t.setTpwd(password.getText());
			    		int gender = 1;
			    		if(sex.getText().equals("女"))
			    			gender = 0;
			    		t.setTsex(gender);
			    		AdminDAO.updateTeacher(t);
			    		JOptionPane.showMessageDialog(null, "修改教师信息成功", "提示", 1);
			    		frame.dispose();
			    	}
			    });
			}
	    });
			
	}
	
	public void StudentInfo(ActionEvent e)	//学生管理
	{
		pan.removeAll();
		JTable 	table = new JTable();
		JPanel 	tablePan = new JPanel(),
				btnPan = new JPanel();
		JButton btnNew = new JButton("新建学生"),
				btnDel = new JButton("删除学生"),
				btnUpd = new JButton("学生信息更新");
		btnNew.setFont(font);
		btnDel.setFont(font);
		btnUpd.setFont(font);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.addColumn("学号");
		tableModel.addColumn("姓名");
		tableModel.addColumn("性别");
		tableModel.addColumn("年龄");
		tableModel.addColumn("生源地");
		tableModel.addColumn("已获得学分");
		tableModel.addColumn("班级");
		TableColumnModel cm=table.getColumnModel();
		cm.getColumn(0).setPreferredWidth(95);			//setting column width
		cm.getColumn(1).setPreferredWidth(40);
		cm.getColumn(2).setPreferredWidth(40);	
		cm.getColumn(3).setPreferredWidth(40);
		cm.getColumn(4).setPreferredWidth(40);
		cm.getColumn(5).setPreferredWidth(60);
		cm.getColumn(6).setPreferredWidth(220);
		table.setRowHeight(50);
		table.setRowMargin(5);
		table.setRowSelectionAllowed(true); 
		table.setSelectionBackground(Color.BLUE);		
		table.setSelectionForeground(Color.WHITE);
		table.setGridColor(Color.BLACK); 
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.doLayout();
		table.setBackground(Color.WHITE);
		table.setPreferredScrollableViewportSize(new Dimension(1000,560));
		table.setFont(font1);
		JTableHeader head = table.getTableHeader(); 	// 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));	// 设置表头大小
        head.setFont(font1);							// 设置表格字体
		JScrollPane sp=new JScrollPane(table);
		List<Student> students = AdminDAO.getAllStudent();
		for(int i=0; i<students.size(); i++)
		{
			Student stu = students.get(i);
			String gender = "男";
			if(stu.getSsex()==0)
				gender = "女";
			tableModel.addRow(new Object[] {stu.getSno(), stu.getSname(), gender, stu.getSage(), stu.getShome(), stu.getScredit(), stu.getClname()});
		}
		btnPan.add(btnNew);btnPan.add(btnDel);btnPan.add(btnUpd);
		pan.add(sp);
		pan.add(btnPan);
		validate();
	    repaint();
	    
	    btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame 	frame = new JFrame("新建教师");
				JPanel 	pan = new JPanel();
				JPanel  stuInfoPan = new JPanel(),
						buttonPan =new JPanel();
				JLabel 	noLab =  new JLabel("学号："),
						nameLab = new JLabel("姓名："),
						sexLab = new JLabel("性别："),
						ageLab = new JLabel("年龄："),
						homeLab = new JLabel("生源所在地："),
						creditLab = new JLabel("已获得学分："),
						classLab = new JLabel("班级代码："),
						pwdLab = new JLabel("密码：");
				noLab.setFont(font1);
				nameLab.setFont(font1);
				sexLab.setFont(font1);
				ageLab.setFont(font1);
				homeLab.setFont(font1);
				creditLab.setFont(font1);
				classLab.setFont(font1);
				pwdLab.setFont(font1);
				JTextArea	no = new JTextArea(),
							name = new JTextArea(),
							clno = new JTextArea(),
							sex = new JTextArea(),
							age = new JTextArea(),
							home = new JTextArea(),
							credit = new JTextArea(),
							password = new JTextArea();
				no.setFont(font1);
				name.setFont(font1);
				clno.setFont(font1);
				sex.setFont(font1);
				age.setFont(font1);
				home.setFont(font1);
				credit.setFont(font1);
				password.setFont(font1);
				JButton save = new JButton("新建学生");
				save.setFont(font1);
				buttonPan.setSize(60, 600);
				GridLayout layout = new GridLayout(8, 2, 30, 30);
				stuInfoPan.setLayout(layout);
				stuInfoPan.add(noLab);		stuInfoPan.add(no);
				stuInfoPan.add(nameLab);	stuInfoPan.add(name);
				stuInfoPan.add(sexLab);		stuInfoPan.add(sex);
				stuInfoPan.add(classLab);	stuInfoPan.add(clno);
				stuInfoPan.add(ageLab);		stuInfoPan.add(age);
				stuInfoPan.add(homeLab);	stuInfoPan.add(home);
				stuInfoPan.add(creditLab);	stuInfoPan.add(credit);
				stuInfoPan.add(pwdLab);		stuInfoPan.add(password);
				buttonPan.add(save);
				GridBagLayout gbl = new GridBagLayout();
				GridBagConstraints gbs = new GridBagConstraints();
				pan.setLayout(gbl);
				pan.add(stuInfoPan);
				pan.add(buttonPan);
				gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
				gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;
				gbs.gridx=0;gbs.gridy=0;
				gbl.setConstraints(stuInfoPan, gbs);
				gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
				gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;
				gbs.gridx=0;gbs.gridy=1;
				gbl.setConstraints(buttonPan, gbs);
				frame.add(pan);
				frame.setSize(900, 600);
				centerWindow(frame);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
				
				save.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		Student stu = new Student();
			    		stu.setSno(no.getText()); 	stu.setSname(name.getText());
			    		stu.setClno(clno.getText()); 
			    		stu.setSage(Integer.parseInt(age.getText()));
			    		stu.setScredit(Integer.parseInt(credit.getText()));
			    		stu.setShome(home.getText());
			    		stu.setSpwd(password.getText());
			    		int gender = 1;
			    		if(sex.getText().equals("女"))
			    			gender = 0;
			    		stu.setSsex(gender);
			    		AdminDAO.saveStudent(stu);
			    		JOptionPane.showMessageDialog(null, "新建学生成功", "提示", 1);
			    		frame.dispose();
			    	}
			    });
			}
	    });
	    
	    btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				Student stu = students.get(selectedRow);
				tableModel.removeRow(selectedRow);
				AdminDAO.deleteStudent(stu.getSno());
			}
	    });
	    
	    btnUpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				Student stu = students.get(selectedRow);
				JFrame 	frame = new JFrame("教师信息修改");
				JPanel 	pan = new JPanel();
				JPanel  stuInfoPan = new JPanel(),
						buttonPan =new JPanel();
				JLabel 	noLab =  new JLabel("学号："),
						nameLab = new JLabel("姓名："),
						sexLab = new JLabel("性别："),
						ageLab = new JLabel("年龄："),
						homeLab = new JLabel("生源所在地："),
						creditLab = new JLabel("已获得学分："),
						classLab = new JLabel("班级代码："),
						pwdLab = new JLabel("密码：");
				noLab.setFont(font1);
				nameLab.setFont(font1);
				sexLab.setFont(font1);
				ageLab.setFont(font1);
				homeLab.setFont(font1);
				creditLab.setFont(font1);
				classLab.setFont(font1);
				pwdLab.setFont(font1);
				JTextArea	no = new JTextArea(stu.getSno()),
							name = new JTextArea(stu.getSname()),
							clno = new JTextArea(stu.getClno()),
							sex = new JTextArea("男"),
							age = new JTextArea(String.valueOf(stu.getSage())),
							home = new JTextArea(stu.getShome()),
							credit = new JTextArea(String.valueOf(stu.getScredit())),
							password = new JTextArea(stu.getSpwd());
				if(stu.getSsex()==0)
					sex.setText("女");
				no.setFont(font1);
				name.setFont(font1);
				clno.setFont(font1);
				sex.setFont(font1);
				age.setFont(font1);
				home.setFont(font1);
				credit.setFont(font1);
				password.setFont(font1);
				JButton save = new JButton("保存");
				save.setFont(font1);
				buttonPan.setSize(60, 600);
				GridLayout layout = new GridLayout(8, 2, 30, 30);
				stuInfoPan.setLayout(layout);
				stuInfoPan.add(noLab);		stuInfoPan.add(no);
				stuInfoPan.add(nameLab);	stuInfoPan.add(name);
				stuInfoPan.add(sexLab);		stuInfoPan.add(sex);
				stuInfoPan.add(classLab);	stuInfoPan.add(clno);
				stuInfoPan.add(ageLab);		stuInfoPan.add(age);
				stuInfoPan.add(homeLab);	stuInfoPan.add(home);
				stuInfoPan.add(creditLab);	stuInfoPan.add(credit);
				stuInfoPan.add(pwdLab);		stuInfoPan.add(password);
				buttonPan.add(save);
				GridBagLayout gbl = new GridBagLayout();
				GridBagConstraints gbs = new GridBagConstraints();
				pan.setLayout(gbl);
				pan.add(stuInfoPan);
				pan.add(buttonPan);
				gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
				gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;
				gbs.gridx=0;gbs.gridy=0;
				gbl.setConstraints(stuInfoPan, gbs);
				gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
				gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;
				gbs.gridx=0;gbs.gridy=1;
				gbl.setConstraints(buttonPan, gbs);
				frame.add(pan);
				frame.setSize(900, 600);
				centerWindow(frame);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
				
				save.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		Student stu = new Student();
			    		stu.setSno(no.getText()); 	stu.setSname(name.getText());
			    		stu.setClno(clno.getText()); 
			    		stu.setSage(Integer.parseInt(age.getText()));
			    		stu.setScredit(Integer.parseInt(credit.getText()));
			    		stu.setShome(home.getText());
			    		stu.setSpwd(password.getText());
			    		int gender = 1;
			    		if(sex.getText().equals("女"))
			    			gender = 0;
			    		stu.setSsex(gender);
			    		AdminDAO.updateStudent(stu);
			    		JOptionPane.showMessageDialog(null, "修改学生成功", "提示", 1);
			    		frame.dispose();
			    	}
			    });
			}
	    });
	}
	
	public void AdminInfo(ActionEvent e)	//
	{
		pan.removeAll();
		Admin 	admin = AdminDAO.getAdminById(username);
		JPanel  infoPan = new JPanel(),
				buttonPan =new JPanel();
		JLabel 	noLab =  new JLabel("工号："),
				pwdLab = new JLabel("密码：");
		noLab.setFont(font1);
		pwdLab.setFont(font1);
		JTextArea	no = new JTextArea(admin.getAno()),
					password = new JTextArea(admin.getApwd());
		no.setFont(font1);
		password.setFont(font1);
		no.setEditable(false);
		JButton save = new JButton("修改密码");
		save.setFont(font1);
		buttonPan.setSize(60, 600);
		GridLayout layout = new GridLayout(1, 2, 30, 30);
		infoPan.setLayout(layout);
		infoPan.add(noLab);
		infoPan.add(no);
		infoPan.add(pwdLab);
		infoPan.add(password);
		buttonPan.add(save);
		pan.setLayout(new BorderLayout());
		pan.add(infoPan, BorderLayout.NORTH);
		pan.add(buttonPan, BorderLayout.SOUTH);
		this.validate();
	    this.repaint();
		
	    save.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		AdminDAO.changePwd(username, password.getText());
	    		JOptionPane.showMessageDialog(null, "密码修改成功", "提示", 1);
	    	}
	    });
	}
	
	public static void centerWindow(Window f)
	{
		Toolkit tk=f.getToolkit();
		Dimension dm=tk.getScreenSize();						
		f.setLocation((int)(dm.getWidth()-f.getWidth())/2,(int)(dm.getHeight()-f.getHeight())/2);	//�ô��ھ�����ʾ
	}
}
class MajorBox extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1621318599395743472L;
	private Font font= new Font("黑体", Font.PLAIN, 20);
	private Font font1= new Font("黑体", Font.PLAIN, 24);
	MajorBox(String title, String mno, String mname, int flag, int row, DefaultTableModel tableModel)
	{
		this.setTitle(title);
		JLabel  mnoLab = new JLabel("专业代码"),
				mnameLab = new JLabel("专业名称");
		JTextField  Mno = new JTextField(mno),
					Mname = new JTextField(mname);
		JButton btnOK = new JButton("保存"),
				btnCancel = new JButton("取消");
		mnoLab.setFont(font1);
		mnameLab.setFont(font1);
		Mno.setFont(font1);
		Mname.setFont(font1);
		btnOK.setFont(font);
		btnCancel.setFont(font);
		JPanel  panInfo = new JPanel(),
				panBtn = new JPanel(),
				pan = new JPanel();
		panInfo.setLayout(new GridLayout(2,2));
		panInfo.add(mnoLab);panInfo.add(Mno);
		panInfo.add(mnameLab);panInfo.add(Mname);
		panBtn.add(btnOK);panBtn.add(btnCancel);
		pan.add(panInfo);
		pan.add(panBtn);
		this.add(pan);
		setSize(330,220);
		TeacherGUI.centerWindow(this);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Major m = new Major();
				m.setMno(Mno.getText());  m.setMname(Mname.getText());
				if(flag==1)  
				{
					AdminDAO.updateMajor(m);
					tableModel.removeRow(row);
				}
				else AdminDAO.saveMajor(m);
				tableModel.addRow(new Object[] {Mno.getText(),Mname.getText()}); //存在只能改一次bug
				dispose();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}

class ClassBox extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9178504655000183831L;
	private Font font= new Font("黑体", Font.PLAIN, 20);
	private Font font1= new Font("黑体", Font.PLAIN, 24);
	ClassBox(String title, String mno, String mname,String clno, String clname, int flag, int row, DefaultTableModel tableModel)
	{
		this.setTitle(title);
		JLabel  clnoLab = new JLabel("课程代码"),
				clnameLab = new JLabel("课程名称"),
				mnoLab = new JLabel("专业代码");
		JTextField  Clno = new JTextField(clno),
					Clname = new JTextField(clname),
					Mno = new JTextField(mname);
		JButton btnOK = new JButton("保存"),
				btnCancel = new JButton("取消");
		clnoLab.setFont(font1);
		clnameLab.setFont(font1);
		mnoLab.setFont(font1);
		Clno.setFont(font1);
		Clname.setFont(font1);
		Mno.setFont(font1);
		btnOK.setFont(font);
		btnCancel.setFont(font);
		JPanel  panInfo = new JPanel(),
				panBtn = new JPanel(),
				pan = new JPanel();
		panInfo.setLayout(new GridLayout(3,2));
		panInfo.add(clnoLab);panInfo.add(Clno);
		panInfo.add(mnoLab);panInfo.add(Mno);
		panInfo.add(clnameLab);panInfo.add(Clname);
		panBtn.add(btnOK);panBtn.add(btnCancel);
		pan.add(panInfo);
		pan.add(panBtn);
		this.add(pan);
		setSize(330,220);
		TeacherGUI.centerWindow(this);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Class_ c = new Class_();
				c.setClno(Clno.getText());  c.setMno(Mno.getText());
				c.setClname(Clname.getText());
				if(flag==1)
				{
					AdminDAO.updateClass(c);
					tableModel.removeRow(row);
				}
				else AdminDAO.saveClass(c);
				tableModel.addRow(new Object[] {c.getClno(), "刷新显示", c.getClname()});
				dispose();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
class CourseBox extends JFrame
{
	private Font font= new Font("黑体", Font.PLAIN, 20);
	private Font font1= new Font("黑体", Font.PLAIN, 24);
	CourseBox(String title, Course c, int flag, int row, DefaultTableModel tableModel)
	{
		this.setTitle(title);
		JLabel  cnoLab = new JLabel("课程代码"),
				ctermLab = new JLabel("开课学期"),
				cnameLab = new JLabel("课程名称"),
				ctimeLab = new JLabel("学时"),
				creditLab = new JLabel("学分"),
				ctypeLab = new JLabel("测试类型");
		JTextField  Cno = new JTextField(c.getCno()),
					Cterm = new JTextField(c.getCterm()),
					Cname = new JTextField(c.getCname()),
					Ctime = new JTextField(c.getCtime()),
					Ccredit = new JTextField(c.getCcredit());
		String type = "考试";
		if(c.getCtype() == 0)
			type = "考查";
		JTextField Ctype = new JTextField(type);
		JButton btnOK = new JButton("保存"),
				btnCancel = new JButton("取消");
		cnoLab.setFont(font1);
		ctermLab.setFont(font1);
		cnameLab.setFont(font1);
		ctimeLab.setFont(font1);
		creditLab.setFont(font1);
		ctypeLab.setFont(font1);
		Cno.setFont(font1);
		Cterm.setFont(font1);
		Cname.setFont(font1);
		Ctime.setFont(font1);
		Ccredit.setFont(font1);
		Ctype.setFont(font1);
		btnOK.setFont(font);
		btnCancel.setFont(font);
		JPanel  panInfo = new JPanel(),
				panBtn = new JPanel(),
				pan = new JPanel();
		panInfo.setLayout(new GridLayout(6,2));
		panInfo.add(cnoLab);panInfo.add(Cno);
		panInfo.add(ctermLab);panInfo.add(Cterm);
		panInfo.add(cnameLab);panInfo.add(Cname);
		panInfo.add(ctimeLab);panInfo.add(Ctime);
		panInfo.add(creditLab);panInfo.add(Ccredit);
		panInfo.add(ctypeLab);panInfo.add(Ctype);
		panBtn.add(btnOK);panBtn.add(btnCancel);
		pan.add(panInfo);
		pan.add(panBtn);
		this.add(pan);
		setSize(480,250);
		TeacherGUI.centerWindow(this);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Course c = new Course();
				c.setCno(Cno.getText());  c.setCterm(Cterm.getText());
				c.setCname(Cname.getText());  c.setCtime(Integer.parseInt(Ctime.getText()));
				c.setCcredit(Integer.parseInt(Ccredit.getText()));
				int t = 1;
				if(Ctype.getText().equals("考查"))
					t = 0;
				c.setCtype(t);
				if(flag==1)
				{
					AdminDAO.updateCourse(c);
					tableModel.removeRow(row);
				}
				else AdminDAO.saveCourse(c);
				tableModel.addRow(new Object[] {c.getCno(), c.getCterm(), c.getCname(), c.getCtime(), c.getCcredit(), t});
				dispose();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}

class ReportBox extends JFrame
{
	private Font font= new Font("黑体", Font.PLAIN, 20);
	private Font font1= new Font("黑体", Font.PLAIN, 24);
	ReportBox(String title, DefaultTableModel tableModel)
	{
		this.setTitle(title);
		JLabel  cnoLab = new JLabel("课程代码"),
				ctermLab = new JLabel("开课学期"),
				clnoLab = new JLabel("班级代码"),
				tnoLab = new JLabel("教师代码");
		JTextField  Cno = new JTextField(),
					Cterm = new JTextField(),
					Clno = new JTextField(),
					Tno = new JTextField();
		JButton btnOK = new JButton("保存"),
				btnCancel = new JButton("取消");
		cnoLab.setFont(font1);
		ctermLab.setFont(font1);
		clnoLab.setFont(font1);
		tnoLab.setFont(font1);
		Cno.setFont(font1);
		Cterm.setFont(font1);
		Clno.setFont(font1);
		Tno.setFont(font1);
		btnOK.setFont(font);
		btnCancel.setFont(font);
		JPanel  panInfo = new JPanel(),
				panBtn = new JPanel(),
				pan = new JPanel();
		panInfo.setLayout(new GridLayout(6,2));
		panInfo.add(cnoLab);panInfo.add(Cno);
		panInfo.add(ctermLab);panInfo.add(Cterm);
		panInfo.add(clnoLab);panInfo.add(Clno);
		panInfo.add(tnoLab);panInfo.add(Tno);
		panBtn.add(btnOK);panBtn.add(btnCancel);
		pan.add(panInfo);
		pan.add(panBtn);
		this.add(pan);
		setSize(480,250);
		TeacherGUI.centerWindow(this);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Course c = new Course();
				c.setCno(Cno.getText());  c.setClno(Clno.getText());
				c.setCterm(Cterm.getText());  c.setTno(Tno.getText());
				//System.out.println(c.getCno()+c.getCterm()+c.getClno()+c.getTno());
				AdminDAO.saveReport(c);
				tableModel.addRow(new Object[] {c.getCterm(), c.getCno(), "刷新显示", "刷新显示", "刷新显示"});
				dispose();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}


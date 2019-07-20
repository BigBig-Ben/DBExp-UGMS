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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import source.DAO.StudentDAO;
import source.Entity.Course;
import source.Entity.Grade;
import source.Entity.Student;

public class StudentGUI extends JFrame{
	/**
	 * 学生界面
	*/
	private static final long serialVersionUID = -4230164992576575L;
	private Font font= new Font("黑体", Font.PLAIN, 20);
	private Font font1= new Font("黑体", Font.PLAIN, 30);
	String username = null;
	static JMenuBar mb = new JMenuBar();
	static FgMenu	info = new FgMenu("个人信息(I)",KeyEvent.VK_I),
					query = new FgMenu("信息查询(Q)",KeyEvent.VK_Q);
	static JMenuItem stuInfo = new JMenuItem("个人信息维护(M)", KeyEvent.VK_M),
					 gradeInfo = new JMenuItem("成绩查询(G)",KeyEvent.VK_G),
					 courseInfo = new JMenuItem("课程统计及学分统计(C)",KeyEvent.VK_C);
	JPanel pan = new JPanel();
	public StudentGUI(String stuName)
	{
		super("成绩管理系统-学生-"+stuName);
		username=stuName;
		info.setFont(font);
		query.setFont(font);
		stuInfo.setFont(font);
		gradeInfo.setFont(font);
		courseInfo.setFont(font);
		info.add(stuInfo);
		query.add(gradeInfo);
		query.add(courseInfo);
		mb.add(info);
		mb.add(query);
		setJMenuBar(mb);
		pan.setSize(1080, 600);
		this.add(pan);
		setSize(1080, 760);
		centerWindow(this);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		stuInfo.addActionListener(new ActionListener(){			//个人信息功能监听
			public void actionPerformed(ActionEvent e) 
			{
				StudentInfo(e);
			}
		});
		
		gradeInfo.addActionListener(new ActionListener(){			//成绩查询功能监听
			public void actionPerformed(ActionEvent e) 
			{
				GradeInfo(e);
			}
		});
		
		courseInfo.addActionListener(new ActionListener() {			//课程查询功能监听
			public void actionPerformed(ActionEvent e)
			{
				CourseInfo(e);
			}
		});
	}
	
	void StudentInfo(ActionEvent e)		//学生个人信息及修改密码
	{
		pan.removeAll();
		Student stu = StudentDAO.getStuById(username);
		JPanel  stuInfoPan = new JPanel(),
				buttonPan =new JPanel();
		JLabel 	noLab =  new JLabel("学号："),
				nameLab = new JLabel("姓名："),
				sexLab = new JLabel("性别："),
				ageLab = new JLabel("年龄："),
				homeLab = new JLabel("生源所在地："),
				creditLab = new JLabel("已获得学分："),
				classLab = new JLabel("班级："),
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
					clas = new JTextArea(stu.getClname()),
					sex = new JTextArea("男"),
					age = new JTextArea(String.valueOf(stu.getSage())),
					home = new JTextArea(stu.getShome()),
					credit = new JTextArea(String.valueOf(stu.getScredit())),
					password = new JTextArea(stu.getSpwd());
		if(stu.getSsex()==0)
			sex.setText("女");
		no.setFont(font1);
		name.setFont(font1);
		clas.setFont(font1);
		sex.setFont(font1);
		age.setFont(font1);
		home.setFont(font1);
		credit.setFont(font1);
		password.setFont(font1);
		no.setBackground(Color.LIGHT_GRAY);
		name.setBackground(Color.LIGHT_GRAY);
		clas.setBackground(Color.LIGHT_GRAY);
		sex.setBackground(Color.LIGHT_GRAY);
		age.setBackground(Color.LIGHT_GRAY);
		home.setBackground(Color.LIGHT_GRAY);
		credit.setBackground(Color.LIGHT_GRAY);
		no.setEditable(false);name.setEditable(false);clas.setEditable(false);
		sex.setEditable(false);age.setEditable(false);home.setEditable(false);
		credit.setEditable(false);
		JButton save = new JButton("修改密码");
		save.setFont(font1);
		buttonPan.setSize(60, 600);
		GridLayout layout = new GridLayout(8, 2, 30, 30);
		stuInfoPan.setLayout(layout);
		stuInfoPan.add(noLab);
		stuInfoPan.add(no);
		stuInfoPan.add(nameLab);
		stuInfoPan.add(name);
		stuInfoPan.add(sexLab);
		stuInfoPan.add(sex);
		stuInfoPan.add(classLab);
		stuInfoPan.add(clas);
		stuInfoPan.add(ageLab);
		stuInfoPan.add(age);
		stuInfoPan.add(homeLab);
		stuInfoPan.add(home);
		stuInfoPan.add(creditLab);
		stuInfoPan.add(credit);
		stuInfoPan.add(pwdLab);
		stuInfoPan.add(password);
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
		this.validate();
	    this.repaint();
	    
	    save.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		StudentDAO.changePwd(username, password.getText());
	    		JOptionPane.showMessageDialog(null, "密码修改成功", "提示", 1);
	    	}
	    });
	}
	
	void GradeInfo(ActionEvent e)		//学年成绩统计
	{
		pan.removeAll();
		JPanel  btnPan = new JPanel();
		JLabel  label = new JLabel("开课学期："),
				creditLab = new JLabel("已获得学分："),
				credit = new JLabel();
		JButton query = new JButton("查询");
		JComboBox cb = new JComboBox();
		JTable table=new JTable();					//成绩表
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		label.setFont(font1);
		cb.setFont(font);
		query.setFont(font1);
		creditLab.setFont(font1);
		credit.setFont(font1);
		List<String> terms = StudentDAO.selectTermsG(username);
		tableModel.addColumn("课程名称");
		tableModel.addColumn("开课学期");
		tableModel.addColumn("任课教师");
		tableModel.addColumn("成绩");
		table.setFont(font);
		for(int i=0; i<terms.size(); i++)
			cb.addItem(terms.get(i));
		btnPan.add(label);
		btnPan.add(cb);
		btnPan.add(query);
		btnPan.add(creditLab);
		btnPan.add(credit);
		pan.add(btnPan);
		table.setRowHeight(50);
		table.setRowMargin(5);
		table.setRowSelectionAllowed(false);
		table.setGridColor(Color.BLACK); 
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.doLayout();
		table.setBackground(Color.WHITE);
		table.setPreferredScrollableViewportSize(new Dimension(1000,560));
		table.setFont(font1);
		JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
        head.setFont(font1);// 设置表格字体
		JScrollPane sp=new JScrollPane(table);
		tableModel.setRowCount(0);
		pan.add(sp);
		credit.setText(String.valueOf(StudentDAO.getStuById(username).getScredit()));
		this.validate();
	    this.repaint();
	    
	    query.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		tableModel.setRowCount(0);				//clear all rows
	    		String term = cb.getSelectedItem().toString();
	    		List<Grade> grades = StudentDAO.getGradeByTerm(username, term);
	    		for(int i=0; i<grades.size(); i++)
	    		{
	    			Grade g = grades.get(i);
	    			tableModel.addRow(new Object[] {g.getCname(), g.getCterm(), g.getTname(), g.getGrade()});
	    		}
	    		
	    		validate();
	    	    repaint();
	    	}
	    });
	}
	
	void CourseInfo(ActionEvent e)		//课程查询
	{
		pan.removeAll();
		JPanel  btnPan = new JPanel();
		JButton query = new JButton("查询");
		JLabel label = new JLabel("开课学期：");
		JComboBox cb = new JComboBox();
		JTable table=new JTable();					//课程表
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		label.setFont(font1);
		cb.setFont(font);
		query.setFont(font1);
		List<String> terms = StudentDAO.selectTermsC();
		tableModel.addColumn("开课学期");
		tableModel.addColumn("课程名称");
		tableModel.addColumn("任课教师");
		tableModel.addColumn("学时");
		tableModel.addColumn("学分");
		tableModel.addColumn("班级");
		tableModel.addColumn("测试类型");
		table.setFont(font);
		for(int i=0; i<terms.size(); i++)
			cb.addItem(terms.get(i));
		btnPan.add(label);
		btnPan.add(cb);
		btnPan.add(query);
		pan.add(btnPan);
		TableColumnModel cm=table.getColumnModel();
		cm.getColumn(0).setPreferredWidth(65);			//setting column width
		cm.getColumn(1).setPreferredWidth(65);
		cm.getColumn(2).setPreferredWidth(65);	
		cm.getColumn(3).setPreferredWidth(15);
		cm.getColumn(4).setPreferredWidth(15);
		cm.getColumn(5).setPreferredWidth(270);
		cm.getColumn(6).setPreferredWidth(65);
		table.setRowHeight(50);
		table.setRowMargin(5);
		table.setRowSelectionAllowed(false);
		table.setGridColor(Color.BLACK); 
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.doLayout();
		table.setBackground(Color.WHITE);
		table.setPreferredScrollableViewportSize(new Dimension(1000,560));
		table.setFont(font1);
		JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
        head.setFont(font1);// 设置表格字体
		JScrollPane sp=new JScrollPane(table);
		pan.add(sp);
		this.validate();
	    this.repaint();
	    
	    query.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		tableModel.setRowCount(0);				//clear all rows
	    		String term = cb.getSelectedItem().toString();
	    		List<Course> courses = StudentDAO.getCourse(term);
	    		for(int i=0; i<courses.size(); i++)
	    		{
	    			Course c = courses.get(i);
	    			String type = "考试";
	    			if(c.getCtype()==0)
	    				type = "考察";
	    			tableModel.addRow(new Object[] {c.getCterm(), c.getCname(), c.getTname(), c.getCtime(), c.getCcredit(), c.getClname(), type});
	    		}
	    		
	    		validate();
	    	    repaint();
	    	}
	    });
	}
	
	void centerWindow(Window f)
	{
		Toolkit tk=f.getToolkit();
		Dimension dm=tk.getScreenSize();						
		f.setLocation((int)(dm.getWidth()-f.getWidth())/2,(int)(dm.getHeight()-f.getHeight())/2);	//�ô��ھ�����ʾ
	}
}

class FgMenu extends JMenu{
	public FgMenu(String label) {
		super(label);
	}
	public FgMenu(String label,int nAccelerator) {
		super(label);
		setMnemonic(nAccelerator);
	}
}
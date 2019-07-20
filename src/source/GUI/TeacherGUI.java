package source.GUI;

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
import javax.swing.JComboBox;
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

import source.DAO.StudentDAO;
import source.DAO.TeacherDAO;
import source.Entity.Course;
import source.Entity.Grade;
import source.Entity.Student;
import source.Entity.Teacher;

public class TeacherGUI extends JFrame{
	/**
	 * 教师界面
	 */
	private static final long serialVersionUID = 7587990060395879683L;
	private Font font= new Font("黑体", Font.PLAIN, 20);
	private Font font1= new Font("黑体", Font.PLAIN, 30);
	String username = null;
	static JMenuBar mb = new JMenuBar();
	static FgMenu	info = new FgMenu("个人信息(I)",KeyEvent.VK_I),
					query = new FgMenu("信息查询(Q)",KeyEvent.VK_Q),
					manage = new FgMenu("信息管理(M)",KeyEvent.VK_M);
	static JMenuItem teacherInfo = new JMenuItem("个人信息维护(O)", KeyEvent.VK_O),
					 gradeInfo = new JMenuItem("成绩管理与录入(G)",KeyEvent.VK_G),
					 courseInfo = new JMenuItem("任课查询(C)",KeyEvent.VK_C);
	JPanel pan = new JPanel();
	public TeacherGUI(String teacher)
	{
		super("成绩管理系统-教师-"+teacher);
		username = teacher;
		info.setFont(font);
		query.setFont(font);
		manage.setFont(font);
		teacherInfo.setFont(font);
		gradeInfo.setFont(font);
		courseInfo.setFont(font);
		info.add(teacherInfo);
		query.add(courseInfo);
		manage.add(gradeInfo);
		mb.add(info);
		mb.add(query);
		mb.add(manage);
		setJMenuBar(mb);
		pan.setSize(1080, 600);
		this.add(pan);
		setSize(1080, 760);
		centerWindow(this);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		teacherInfo.addActionListener(new ActionListener() {	//教师信息查询
			public void actionPerformed(ActionEvent e)
			{
				TeacherInfo(e);
			}
		});
		
		gradeInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				GradeManagement(e);
			}
		});
		
		courseInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				CourseInfo(e);
			}
		});
	}
	
	public void TeacherInfo(ActionEvent e)
	{
		pan.removeAll();
		Teacher teacher = TeacherDAO.getTeacherById(username);
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
		no.setBackground(Color.LIGHT_GRAY);
		name.setBackground(Color.LIGHT_GRAY);
		rank.setBackground(Color.LIGHT_GRAY);
		sex.setBackground(Color.LIGHT_GRAY);
		age.setBackground(Color.LIGHT_GRAY);
		tel.setBackground(Color.LIGHT_GRAY);
		no.setEditable(false);name.setEditable(false);rank.setEditable(false);
		sex.setEditable(false);age.setEditable(false);tel.setEditable(false);
		JButton save = new JButton("修改密码");
		save.setFont(font1);
		buttonPan.setSize(60, 600);
		//infoPan.setPreferredSize(new Dimension(900, 500));
		GridLayout layout = new GridLayout(7, 2, 30, 30);
		infoPan.setLayout(layout);
		infoPan.add(noLab);
		infoPan.add(no);
		infoPan.add(nameLab);
		infoPan.add(name);
		infoPan.add(sexLab);
		infoPan.add(sex);
		infoPan.add(rankLab);
		infoPan.add(rank);
		infoPan.add(ageLab);
		infoPan.add(age);
		infoPan.add(telLab);
		infoPan.add(tel);
		infoPan.add(pwdLab);
		infoPan.add(password);
		buttonPan.add(save);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbs = new GridBagConstraints();
		pan.setLayout(gbl);
		pan.add(infoPan);
		pan.add(buttonPan);
		gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;
		gbs.gridx=0;gbs.gridy=0;
		gbl.setConstraints(infoPan, gbs);
		gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;
		gbs.gridx=0;gbs.gridy=1;
		gbl.setConstraints(buttonPan, gbs);
		this.validate();
	    this.repaint();
		
	    save.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		TeacherDAO.changePwd(username, password.getText());
	    		JOptionPane.showMessageDialog(null, "密码修改成功", "提示", 1);
	    	}
	    });
	}
	
	public void GradeManagement(ActionEvent e)
	{
		pan.removeAll();
		JLabel  termLab = new JLabel("开课学期："),
				courseLab = new JLabel("课程：");
		JComboBox cbTerm = new JComboBox(),
				  cbCourse = new JComboBox();
		JButton queryC = new JButton("查询课程"),
				queryG = new JButton("查询成绩"),
				update = new JButton("修改成绩");
		JPanel  btnPan1 = new JPanel(),
				btnPan2 = new JPanel(),
				updatePan = new JPanel();
		JLabel  avgLab = new JLabel("平均成绩"),
				avg = new JLabel();
		JTable  table=new JTable();					
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		termLab.setFont(font1);
		courseLab.setFont(font1);
		cbTerm.setFont(font1);
		cbCourse.setFont(font1);
		queryC.setFont(font1);
		queryG.setFont(font1);
		update.setFont(font1);
		avgLab.setFont(font1); avg.setFont(font1);
		btnPan1.add(termLab);btnPan1.add(cbTerm);btnPan1.add(queryC);
		btnPan2.add(courseLab);btnPan2.add(cbCourse);btnPan2.add(queryG);
		updatePan.add(update);updatePan.add(avgLab);updatePan.add(avg);
		cbCourse.setSelectedItem("-");
		btnPan1.setLayout(new GridLayout(1, 3));
		btnPan2.setLayout(new GridLayout(1, 3));
		List<String> terms = TeacherDAO.selectTermsC();
		for(int i=0; i<terms.size(); i++)
			cbTerm.addItem(terms.get(i));
		tableModel.addColumn("开课学期");
		tableModel.addColumn("学号");
		tableModel.addColumn("学生姓名");
		tableModel.addColumn("课程代码");
		tableModel.addColumn("课程名称");
		tableModel.addColumn("成绩");
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
		table.setPreferredScrollableViewportSize(new Dimension(1000,530));
		table.setFont(font1);
		JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
        head.setFont(font1);// 设置表格字体
		JScrollPane sp=new JScrollPane(table);
		pan.add(btnPan1);
		pan.add(btnPan2);
		pan.add(sp);
		pan.add(updatePan);
		this.validate();
	    this.repaint();
	    
	    queryC.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		String term = cbTerm.getSelectedItem().toString();
	    		List<String> cnames = TeacherDAO.getCname(term, username);
	    		cbCourse.removeAllItems();
	    		for(int i=0; i<cnames.size(); i++)
	    			cbCourse.addItem(cnames.get(i));
	    		validate();
	    	    repaint();
	    	}
	    });
	    
	    queryG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sum = 0;
				int cnt = 0;
				if(cbTerm.getSelectedItem().toString().equals("-"))
					JOptionPane.showMessageDialog(null, "请先选择开课时间", "错误提示", JOptionPane.ERROR_MESSAGE);
				else {
					tableModel.setRowCount(0);
		    		String term = cbTerm.getSelectedItem().toString();
		    		String course = cbCourse.getSelectedItem().toString();
		    		List<Grade> grades = TeacherDAO.getGradeByCourse(username, term, course);
		    		for(int i=0; i<grades.size(); i++)
		    		{
		    			Grade g = grades.get(i);
		    			if(g.getGrade()>0)
		    			{
		    				sum+=g.getGrade();
		    				cnt++;
		    			}
		    			tableModel.addRow(new Object[] {g.getCterm(), g.getSno(), g.getSname(), g.getCno(), g.getCname(), g.getGrade() });
		    		}
		    		avg.setText(String.valueOf(sum/cnt));
		    		validate();
		    	    repaint();
				}
			}
	    });
	    
	    update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				String term = table.getValueAt(selectedRow, 0).toString();
				String sno = table.getValueAt(selectedRow, 1).toString();
				String cno = table.getValueAt(selectedRow, 3).toString();
				String cname = table.getValueAt(selectedRow, 4).toString();
				String grade = table.getValueAt(selectedRow, 5).toString();
				gradeEdit gg = new gradeEdit(sno, cno, cname, term, grade);
			}
	    });
	}
	
	public void CourseInfo(ActionEvent e)
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
		List<String> terms = TeacherDAO.selectTermsC();
		tableModel.addColumn("开课学期");
		tableModel.addColumn("课程名称");
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
		TableColumnModel cm=table.getColumnModel();
		cm.getColumn(0).setPreferredWidth(75);			//setting column width
		cm.getColumn(1).setPreferredWidth(75);
		cm.getColumn(2).setPreferredWidth(23);
		cm.getColumn(3).setPreferredWidth(22);
		cm.getColumn(4).setPreferredWidth(280);
		cm.getColumn(5).setPreferredWidth(75);
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
		tableModel.setRowCount(0);
		JScrollPane sp=new JScrollPane(table);
		pan.add(btnPan);
		pan.add(sp);
		this.validate();
	    this.repaint();
	    
	    query.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		tableModel.setRowCount(0);				//clear all rows
	    		String term = cb.getSelectedItem().toString();
	    		List<Course> courses = TeacherDAO.getCourse(term, username);
	    		for(int i=0; i<courses.size(); i++)
	    		{
	    			Course c = courses.get(i);
	    			String type = "考试";
	    			if(c.getCtype()==0)
	    				type = "考察";
	    			tableModel.addRow(new Object[] {c.getCterm(), c.getCname(), c.getCtime(), c.getCcredit(), c.getClname(), type});
	    		}
	    		validate();
	    	    repaint();
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
class gradeEdit extends JFrame
{
	private Font font= new Font("黑体", Font.PLAIN, 20);
	private Font font1= new Font("黑体", Font.PLAIN, 24);
	gradeEdit(String sno, String cno, String cname, String term, String score)
	{
		this.setTitle("成绩修改");
		JLabel  snoLab = new JLabel("学号"),
				cnoLab = new JLabel("课程"),
				gradeLab = new JLabel("成绩");
		JTextField  Sno = new JTextField(sno),
					Cno = new JTextField(cname),
					grade = new JTextField(score);
		Sno.setEditable(false);Cno.setEditable(false);
		JButton btnOK = new JButton("保存成绩"),
				btnCancel = new JButton("取消修改");
		snoLab.setFont(font1);
		cnoLab.setFont(font1);
		gradeLab.setFont(font1);
		Sno.setFont(font1);
		Cno.setFont(font1);
		grade.setFont(font1);
		btnOK.setFont(font);
		btnCancel.setFont(font);
		JPanel  panI = new JPanel(),
				panBtn = new JPanel(),
				pan = new JPanel();
		panI.setLayout(new GridLayout(4,2));
		panI.add(snoLab);panI.add(Sno);
		panI.add(cnoLab);panI.add(Cno);
		panI.add(gradeLab);panI.add(grade);
		panBtn.add(btnOK);panBtn.add(btnCancel);
		pan.add(panI);
		pan.add(panBtn);
		this.add(pan);
		setSize(330,220);
		TeacherGUI.centerWindow(this);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherDAO.updateGrade(sno, cno, term, grade.getText());
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
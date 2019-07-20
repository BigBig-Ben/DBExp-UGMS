package source.GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import source.DAO.AdminDAO;
import source.DAO.StudentDAO;
import source.DAO.TeacherDAO;

public class Login {
	/**
	 * 登录界面
	 */
	private static final long serialVersionUID = -7899228102358391474L;
	final String[] roles= {"-", "学生", "教师", "DBA"};
	String role = "-";

	JFrame 	frame = new JFrame("高校成绩管理系统");
	JPanel 	pan = new JPanel(),
			panRole = new JPanel(),
			panCount = new JPanel(),
			panPwd = new JPanel(),
			panButton = new JPanel();
	JButton enter = new JButton("登录");
	JLabel 	roleLab = new JLabel("登录角色"),
			countLab = new JLabel("学号"),
			pwdLab = new JLabel("密码");
	JComboBox cbRoles = new JComboBox();
	JTextField count = new JTextField("", 15);
	JTextField password = new JTextField("", 15);
	public Login()
	{
		for(int i=0; i<=3; i++)
		{
			cbRoles.addItem(roles[i]);
		}
		cbRoles.setFont(new Font("黑体", Font.PLAIN, 20));
		enter.setFont(new Font("黑体", Font.PLAIN, 20));
		roleLab.setFont(new Font("黑体", Font.PLAIN, 20));
		countLab.setFont(new Font("黑体", Font.PLAIN, 20));
		pwdLab.setFont(new Font("黑体", Font.PLAIN, 20));
		count.setFont(new Font("黑体", Font.PLAIN, 20));
		password.setFont(new Font("黑体", Font.PLAIN, 20));
		panRole.add(roleLab);
		panRole.add(cbRoles);
		panCount.add(countLab);
		panCount.add(count);
		panPwd.add(pwdLab);
		panPwd.add(password);
		pan.add(panRole);
		pan.add(panCount);
		pan.add(panPwd);
		
		enter.setPreferredSize(new Dimension(100,45));
		panButton.add(enter);
		pan.add(panButton);
		pan.setLayout(new GridLayout(4, 1));
		frame.add(pan);
		frame.setSize(400, 260);
		//frame.setSize(1080, 760);
		centerWindow(frame);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		cbRoles.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				role = cbRoles.getSelectedItem().toString();
			}
		});
		
		enter.addActionListener(new ActionListener() {			//登陆按钮监测
			public void actionPerformed(ActionEvent e)
			{
				login(e);
			}
		});
	}
	
	//登陆响应
	void login(ActionEvent e)
	{
		if(role.equals("-"))
		{
			JOptionPane.showMessageDialog(null, "请选择登录角色");
		}
		else if(role.equals("DBA"))
		{
			String username = count.getText();
			String key = password.getText();
			//System.out.println(role+","+username+","+key);
			if(AdminDAO.login(username, key))
			{
				DBAGUI dba = new DBAGUI(username);
				//System.out.println("login success");
			}
			else JOptionPane.showMessageDialog(null, "密码或用户名错误");
		}
		else if(role.equals("学生"))
		{
			String username = count.getText();
			String key = password.getText();
			//System.out.println(role+","+username+","+key);
			if(StudentDAO.login(username, key))
			{
				StudentGUI stu = new StudentGUI(username);
				//System.out.println("login success");
			}
			else JOptionPane.showMessageDialog(null, "密码或用户名错误");
		}
		else 
		{
			String username = count.getText();
			String key = password.getText();
			//System.out.println(role+","+username+","+key);
			if(TeacherDAO.login(username, key))
			{
				TeacherGUI teacher = new TeacherGUI(username);
				System.out.println("login success");
			}
			else JOptionPane.showMessageDialog(null, "密码或用户名错误");
		}
	}
	
	void centerWindow(Window f)
	{
		Toolkit tk=f.getToolkit();
		Dimension dm=tk.getScreenSize();						
		f.setLocation((int)(dm.getWidth()-f.getWidth())/2,(int)(dm.getHeight()-f.getHeight())/2);	//�ô��ھ�����ʾ
	}
}

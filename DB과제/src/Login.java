import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Login extends JFrame {

	String choice = null;
	
	public Login() {
		
		setTitle("로그인 화면");
		
		// 1. 컴포넌트들을 만들어 보자.
		JLabel title = 
			new JLabel("돼동여지도", JLabel.CENTER);
		
		title.setForeground(new Color(5, 0, 153));
		title.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		
		JButton login = new JButton("로그인");
		JButton join = new JButton("회원가입");
		JButton cancel = new JButton("취소");
		
		JTextField id = new JTextField(10);
		JPasswordField pwd = new JPasswordField(10);
		JTextField name = new JTextField(10);
		JTextField phone = new JTextField(10);
		

		// form panel
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		idPanel.add(new JLabel("아이디 : "));
		idPanel.add(id);
		
		
		JPanel pwdPanel = new JPanel();
		pwdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pwdPanel.add(new JLabel("비밀번호 : "));
		pwdPanel.add(pwd);
		
		

		
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(2, 1));
		formPanel.add(idPanel);
		formPanel.add(pwdPanel);

		
		// radio + form panel
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.add(formPanel);
		
		// button panel
		JPanel panel = new JPanel();
		panel.add(login);
		panel.add(join);
		panel.add(cancel);
		
		
		add(title, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		
		
		setBounds(200, 200, 250, 250);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		
		// 이벤트 처리
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String myId = id.getText();
				String myPwd = new String(pwd.getPassword());
			

				JOptionPane.showMessageDialog
					(null , "환영합니다.");
			}
		});
		
		
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new LoginScreen();
				
				dispose();
				
			}
		});
		
		class Frame2 extends Frame {
			private static final int EXIT_ON_CLOSE = 0;

			public Frame2() {
				
				setTitle("회원가입 화면");
			      title.setForeground(new Color(5, 0, 153));
			      title.setFont(new Font("휴먼편지체", Font.BOLD, 30));
			      
			      JButton join = new JButton("확인");
			      JButton cancel = new JButton("취소");
			      
			      JTextField id = new JTextField(10);
			      JPasswordField pwd = new JPasswordField(10);
			      JTextField name = new JTextField(10);
			      JTextField phone = new JTextField(10);


				
				  JPanel idPanel = new JPanel();
			      idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			      idPanel.add(new JLabel("아이디 : "));
			      idPanel.add(id);
			      
			      
			      JPanel pwdPanel = new JPanel();
			      pwdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			      pwdPanel.add(new JLabel("비밀번호 : "));
			      pwdPanel.add(pwd);
			      
			      
			      JPanel namePanel = new JPanel();
			      namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			      namePanel.add(new JLabel("이    름 : "));
			      namePanel.add(name);
			      
			      
			      JPanel phonePanel = new JPanel();
			      phonePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			      phonePanel.add(new JLabel("연 락 처 : "));
			      phonePanel.add(phone);

			      
			      
			      JPanel formPanel = new JPanel();
			      formPanel.setLayout(new GridLayout(4, 1));
			      formPanel.add(idPanel);
			      formPanel.add(pwdPanel);
			      formPanel.add(namePanel);
			      formPanel.add(phonePanel);
			      
			      // radio + form panel
			      JPanel contentPanel = new JPanel();
			      contentPanel.setLayout(new FlowLayout());
			 
			      contentPanel.add(formPanel);
			      
			      // button panel
			      JPanel panel = new JPanel();
			      panel.add(join);
			      panel.add(cancel);
			      
			      
			      add(title, BorderLayout.NORTH);
			      add(contentPanel, BorderLayout.CENTER);
			      add(panel, BorderLayout.SOUTH);
			      
			      
			      setBounds(200, 200, 250, 300);
			      
			      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			      
			      setVisible(true);
			      


				setSize(300, 300);
				setVisible(true);
				
				
				
			}
		}
		join.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Frame2();
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				
				
				
			}
		});
	}
public static void main(String[] args) {
	new Login().setVisible(true);

	}
}
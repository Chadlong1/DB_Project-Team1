package GUI;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;

public class GUI2 extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI2 frame = new GUI2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 200, 1000, 735);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon icon = new ImageIcon("부산_리사이징.png");
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				// Approach 1: Dispaly image at at full size
				g.drawImage(icon.getImage(), 0, 0, null);
				// Approach 2: Scale image to size of component
				// Dimension d = getSize();
				// g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				// Approach 3: Fix the image position in the scroll pane
				// Point p = scrollPane.getViewport().getViewPosition();
				// g.drawImage(icon.getImage(), p.x, p.y, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};
		
		panel.setBounds(0, 0, 1000, 735);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("돼동여지도");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(50, 30, 133, 39);
		panel.add(lblNewLabel);
		
		String[] zone = { "부산 전체", "부산진구", "사상구", "북구", "남구", "서구", "중구", "동구", "강서구", "수영구", "동래구", "연제구", "해운대구",
				"영도구", "금정구", "사하구" };
		String[] food = { "음식종류", "한식", "중식", "양식", "일식", "분식", "패스트푸드" };
		String[] time = { "영업시간", "am 10 ~", "am 11 ~", "pm 12~", "pm 1 ~", "pm 2 ~", "pm 3 ~", "pm 4 ~" };
		
		JComboBox zoneComboBox = new JComboBox(zone);
		zoneComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		zoneComboBox.setBackground(Color.WHITE);
		zoneComboBox.setBounds(50, 90, 80, 30);
		panel.add(zoneComboBox);
		
		JComboBox foodComboBox = new JComboBox(food);
		foodComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		foodComboBox.setBackground(Color.WHITE);
		foodComboBox.setBounds(150, 90, 80, 30);
		panel.add(foodComboBox);
		
		JComboBox timeComboBox = new JComboBox(time);
		timeComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		timeComboBox.setBackground(Color.WHITE);
		timeComboBox.setBounds(250, 90, 80, 30);
		panel.add(timeComboBox);
		
		JButton searchButton = new JButton("검색");
		searchButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		searchButton.setBackground(new Color(190, 204, 216));
		searchButton.setBounds(350, 90, 80, 30);
		panel.add(searchButton);

	}
}

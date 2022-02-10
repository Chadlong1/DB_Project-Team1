package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import actionlisteners.SearchActionListener;

public class GUI2 extends JFrame {

	private JPanel contentPane;
	private Container c;
	private CardLayout card = new CardLayout(0, 0);
	private JComboBox<String> zoneComboBox;
	private JComboBox<String> foodComboBox;
	private JComboBox<String> timeComboBox;

	public String selectedItemFromZone() {
		return (String) zoneComboBox.getSelectedItem();
	}

	public String selectedItemFromFood() {
		return (String) foodComboBox.getSelectedItem();
	}

	public String selectedItemFromTime() {
		return (String) timeComboBox.getSelectedItem();
	}

	public CardLayout getCard() {
		return card;
	}

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
		setTitle("돼동여지도");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 200, 1000, 735);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(3, 3, 3, 3));
		setContentPane(contentPane);

		ImageIcon icon = new ImageIcon("부산_리사이징.png");

		contentPane.setLayout(card);
		c = getContentPane();

		JPanel panel1 = new JPanel() {
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
		panel1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel1, "FirstScreen");
		panel1.setLayout(null);

		JLabel lblNewLabel = new JLabel("돼동여지도");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(50, 30, 133, 39);
		panel1.add(lblNewLabel);

		String[] zone = { "부산 전체", "부산진구", "사상구", "북구", "남구", "서구", "중구", "동구", "강서구", "수영구", "동래구", "연제구", "해운대구",
				"영도구", "금정구", "사하구" };
		String[] food = { "음식종류", "한식", "중식", "양식", "일식", "분식", "패스트푸드" };
		String[] time = { "영업시간", "am 10 ~", "am 11 ~", "pm 12~", "pm 1 ~", "pm 2 ~", "pm 3 ~", "pm 4 ~" };

		zoneComboBox = new JComboBox(zone);
		zoneComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		zoneComboBox.setBackground(Color.WHITE);
		zoneComboBox.setBounds(50, 90, 80, 30);
		panel1.add(zoneComboBox);

		foodComboBox = new JComboBox(food);
		foodComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		foodComboBox.setBackground(Color.WHITE);
		foodComboBox.setBounds(150, 90, 80, 30);
		panel1.add(foodComboBox);

		timeComboBox = new JComboBox(time);
		timeComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		timeComboBox.setBackground(Color.WHITE);
		timeComboBox.setBounds(250, 90, 80, 30);
		panel1.add(timeComboBox);

		JButton searchButton = new JButton("검색");
		searchButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		searchButton.setBackground(new Color(190, 204, 216));
		searchButton.setBounds(350, 90, 80, 30);
		panel1.add(searchButton);
		searchButton.addActionListener(new SearchActionListener(GUI2.this));

		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 255, 255));
		contentPane.add(panel2, "SecondScreen");

		JButton goBackButton = new JButton("뒤로가기");
		goBackButton.setBounds(869, 648, 97, 32);
		goBackButton.setBackground(new Color(135, 206, 235));
		goBackButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				card.next(c);
				card.show(c, "FirstScreen");
			}
		});
		panel2.setLayout(null);
		panel2.add(goBackButton);

		JList list = new JList();
		list.setBounds(20, 449, 357, 217);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "눅눅", "윽윽" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});

		list.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		list.setBorder(new LineBorder(new Color(128, 128, 128)));
		list.setValueIsAdjusting(true);
		panel2.add(list);

		JPanel panel = new JPanel();
		panel.setBounds(20, 20, 938, 360);
		panel.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel.setBackground(new Color(255, 255, 255));
		panel2.add(panel);
		panel.setLayout(null);
		
		Image image = null;
		try {
			URL url = new URL("https://www.visitbusan.net/uploadImgs/files/cntnts/20191216135832825_thumbL");
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(499, 10, 427, 330);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(image));
		
		
		
		
	}
}
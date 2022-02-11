package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import SEARCHINFO.SEARCHTOOLS;
import listeners.SearchActionListener;

public class GUI2 extends JFrame {

	private JPanel contentPane;
	private Container c;
	private CardLayout card = new CardLayout(0, 0);
	private JComboBox<String> zoneComboBox;
	private JComboBox<String> foodComboBox;
	private JComboBox<String> ratingComboBox;
	private String rprsntvMenu;
	private String aDDR;
	private String cntctTEL;
	private String usageTime;
	private String itemCntnts;
	private URL url;
	private Image image;
	private JList<String> searchingList;
	private JLabel thumbL;
	private JLabel restTitle;
	private JLabel restRprsntvMenu;
	private JLabel restADDR;
	private JLabel restCntctTEL;
	private JLabel restUsageTime;
	private JLabel restItemCntnts;
	private JScrollBar scrollBar;

	public JLabel getRestTitle() {
		return restTitle;
	}

	public void setRestTitle(JLabel restTitle) {
		this.restTitle = restTitle;
	}

	public JLabel getRestRprsntvMenu() {
		return restRprsntvMenu;
	}

	public void setRestRprsntvMenu(JLabel restRprsntvMenu) {
		this.restRprsntvMenu = restRprsntvMenu;
	}

	public JLabel getRestADDR() {
		return restADDR;
	}

	public void setRestADDR(JLabel restADDR) {
		this.restADDR = restADDR;
	}

	public JLabel getRestCntctTEL() {
		return restCntctTEL;
	}

	public void setRestCntctTEL(JLabel restCntctTEL) {
		this.restCntctTEL = restCntctTEL;
	}

	public JLabel getRestUsageTime() {
		return restUsageTime;
	}

	public void setRestUsageTime(JLabel restUsageTime) {
		this.restUsageTime = restUsageTime;
	}

	public JLabel getRestItemCntnts() {
		return restItemCntnts;
	}

	public void setRestItemCntnts(JLabel restItemCntnts) {
		this.restItemCntnts = restItemCntnts;
	}

	public JList<String> getSearchingList() {
		return searchingList;
	}

	public void setSearchingList(JList<String> searchingList) {
		this.searchingList = searchingList;
	}

	public String selectedItemFromZone() {
		return (String) zoneComboBox.getSelectedItem();
	}

	public String selectedItemFromFood() {
		return (String) foodComboBox.getSelectedItem();
	}

	public String selectedItemFromRating() {
		return (String) ratingComboBox.getSelectedItem();
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
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(3, 3, 3, 3));
		setContentPane(contentPane);

		ImageIcon icon = new ImageIcon("부산_리사이징.png");

		contentPane.setLayout(card);
		c = getContentPane();

		JPanel firstPanel = new JPanel() {
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
		firstPanel.setBackground(new Color(255, 255, 255));
		contentPane.add(firstPanel, "FirstScreen");
		firstPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("돼동여지도");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(50, 30, 133, 39);
		firstPanel.add(lblNewLabel);

		String[] zone = { "부산 전체", "부산진구", "사상구", "북구", "남구", "서구", "중구", "동구", "강서구", "수영구", "동래구", "연제구", "해운대구",
				"영도구", "금정구", "사하구" };
		String[] food = { "음식종류", "분류 없음", "한식", "중식", "양식", "일식", "분식" };
		String[] rating = { "★이상", "★★이상", "★★★이상", "★★★★이상", "★★★★★" };
		SEARCHTOOLS searchTool = new SEARCHTOOLS();
		List<String> list = searchTool.searchLoca();

		zoneComboBox = new JComboBox(zone);
		zoneComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		zoneComboBox.setBackground(Color.WHITE);
		zoneComboBox.setBounds(50, 90, 80, 30);
		firstPanel.add(zoneComboBox);

		foodComboBox = new JComboBox(food);
		foodComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		foodComboBox.setBackground(Color.WHITE);
		foodComboBox.setBounds(150, 90, 80, 30);
		firstPanel.add(foodComboBox);

		ratingComboBox = new JComboBox(rating);
		ratingComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		ratingComboBox.setBackground(Color.WHITE);
		ratingComboBox.setBounds(250, 90, 80, 30);
		firstPanel.add(ratingComboBox);

		JButton searchButton = new JButton("검색");
		searchButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		searchButton.setBackground(new Color(190, 204, 216));
		searchButton.setBounds(350, 90, 80, 30);
		firstPanel.add(searchButton);
		searchButton.addActionListener(new SearchActionListener(GUI2.this));

		JPanel secondPanel = new JPanel();
		secondPanel.setBackground(new Color(255, 255, 255));
		contentPane.add(secondPanel, "SecondScreen");

		JButton goBackButton = new JButton("뒤로가기");
		goBackButton.setBounds(861, 643, 97, 32);
		goBackButton.setBackground(new Color(135, 206, 235));
		goBackButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				card.next(c);
				card.show(c, "FirstScreen");
			}
		});
		secondPanel.setLayout(null);
		secondPanel.add(goBackButton);

		JPanel secondMainPanel = new JPanel();
		secondMainPanel.setBounds(20, 20, 938, 360);
		secondMainPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
		secondMainPanel.setBackground(new Color(255, 255, 255));
		secondPanel.add(secondMainPanel);
		secondMainPanel.setLayout(null);

		image = null;

		thumbL = new JLabel("");
		thumbL.setHorizontalAlignment(SwingConstants.CENTER);
		thumbL.setBounds(494, 15, 427, 330);
		thumbL.setBorder(new LineBorder((new Color(128, 128, 128)), 1, false));
		secondMainPanel.add(thumbL);

		restTitle = new JLabel("가게 이름");
		restTitle.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		restTitle.setBounds(27, 26, 337, 47);
		secondMainPanel.add(restTitle);

		rprsntvMenu = "";
		restRprsntvMenu = new JLabel("• 주요 메뉴 : " + rprsntvMenu);
		restRprsntvMenu.setVerticalAlignment(SwingConstants.TOP);
		restRprsntvMenu.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restRprsntvMenu.setBounds(27, 94, 449, 25);
		secondMainPanel.add(restRprsntvMenu);

		aDDR = "";
		restADDR = new JLabel("• 주소지 : " + aDDR);
		restADDR.setVerticalAlignment(SwingConstants.TOP);
		restADDR.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restADDR.setBounds(27, 129, 449, 25);
		secondMainPanel.add(restADDR);

		cntctTEL = "";
		restCntctTEL = new JLabel("• 연락처 : " + cntctTEL);
		restCntctTEL.setVerticalAlignment(SwingConstants.TOP);
		restCntctTEL.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restCntctTEL.setBounds(27, 164, 449, 25);
		secondMainPanel.add(restCntctTEL);

		usageTime = "";
		restUsageTime = new JLabel("• 영업시간 : " + usageTime);
		restUsageTime.setVerticalAlignment(SwingConstants.TOP);
		restUsageTime.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restUsageTime.setBounds(27, 199, 449, 25);
		secondMainPanel.add(restUsageTime);

		itemCntnts = "";
		restItemCntnts = new JLabel("• 소개 : " + itemCntnts);
		restItemCntnts.setVerticalAlignment(SwingConstants.TOP);
		restItemCntnts.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restItemCntnts.setBounds(27, 234, 449, 80);
		secondMainPanel.add(restItemCntnts);

		JScrollPane listScrollPane = new JScrollPane();
		listScrollPane.setBounds(20, 395, 400, 280);

		secondPanel.add(listScrollPane);
		
		searchingList = new JList<>();
		listScrollPane.setViewportView(searchingList);
		searchingList.setVisibleRowCount(10);
		searchingList.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {};

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});

		searchingList.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		searchingList.setBorder(new LineBorder(new Color(128, 128, 128)));
		searchingList.setValueIsAdjusting(true);
		
	
		searchingList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (searchingList.getSelectedIndex() == 0) {
					restTitle.setText("만호갈미 샤브샤브");
					rprsntvMenu = "만호갈미 샤브샤브";
					restRprsntvMenu.setText("• 주요 메뉴 : " + rprsntvMenu);
					aDDR = "강서구 르노삼성대로 602";
					restADDR.setText("• 주소지 : " + aDDR);
					cntctTEL = "051-271-4389";
					restCntctTEL.setText("• 영업시간 : " + usageTime);
					usageTime = "12:00p.m. ~ 21:30p.m.";
					restUsageTime.setText("• 연락처 : " + cntctTEL);
					itemCntnts = "샤브샤브, 수육, 구이 등 다양한 방식으로 갈미조개를 요리하는 갈미조개 전문 식당. 국물이 맛있기로 유명한 이 곳은 갈미샤브샤브와 갈미수육이 대표메뉴이다.";
					restItemCntnts.setText("• 소개 : " + itemCntnts);
					try {
						url = new URL("https://www.visitbusan.net/uploadImgs/files/cntnts/20191216135832825_thumbL");
						image = ImageIO.read(url);
						thumbL.setIcon(new ImageIcon(image));

					} catch (IOException e1) {
						e1.printStackTrace();
					}

				} else if (searchingList.getSelectedIndex() == 1) {
					restTitle.setText("민물가든");
					rprsntvMenu = "묵은지붕어조림, 붕어찜";
					restRprsntvMenu.setText("• 주요 메뉴 : " + rprsntvMenu);
					aDDR = "강서구 둔치중앙길5(봉림동)";
					restADDR.setText("• 주소지 : " + aDDR);
					cntctTEL = "051-971-8428";
					restCntctTEL.setText("• 영업시간 : " + usageTime);
					usageTime = "11:00p.m. ~ 21:00p.m.";
					restUsageTime.setText("• 연락처 : " + cntctTEL);
					itemCntnts = "30년간 운영해온 생선찜전문점으로, 전통방식인 나무통을 사용하여 조리하는 것이 특징이다. 20가지 이상의 재료로 만든 양념을 사용하는 이 곳은 묵은지 붕어조림과 붕어찜이 대표메뉴이다.";
					restItemCntnts.setText("• 소개 : " + itemCntnts);
					try {
						url = new URL("https://www.visitbusan.net/uploadImgs/files/cntnts/20191217101816206_thumbL");
						image = ImageIO.read(url);
						thumbL.setIcon(new ImageIcon(image));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

	}
}
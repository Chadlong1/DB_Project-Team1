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
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import SEARCHINFO.SEARCHTOOLS;
import busan.Main;
import listeners.ComboBoxListener;
import listeners.GoBackActionListener;
import listeners.SearchActionListener;
import listeners.SecondPanelListListener;

public class GUI2 extends JFrame {

	private JPanel contentPane;
	private Container c;
	private CardLayout card = new CardLayout(0, 0);
	private JComboBox<String> zoneComboBox;
	private JComboBox<String> foodComboBox;
	private JComboBox<String> ratingComboBox;
	private Image image;
	private JList<String> searchingList;
	private JLabel thumbL;
	private JLabel restTitle;
	private JLabel restRprsntvMenu;
	private JLabel restADDR;
	private JLabel restCntctTEL;
	private JLabel restUsageTime;
	private JLabel restItemCntnts2;
	private JPanel firstPanel;
	private String[] zones;
	private JLabel mapTitleLabel;
	private JPanel staticMapPanel;
	private JLabel staitcMap;

	public String[] getZones() {
		return zones;
	}

	public JPanel getFirstPanel() {
		return firstPanel;
	}

	public void setFirstPanel(JPanel firstPanel) {
		this.firstPanel = firstPanel;
	}

	public void setThumbL(String RestURL) {
		try {
			URL url = new URL(RestURL);
			image = ImageIO.read(url);
			thumbL.setIcon(new ImageIcon(image));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void setStaitcMap(String RestURL) {
		try {
			URL url = new URL(RestURL);
			image = ImageIO.read(url);
			staitcMap.setIcon(new ImageIcon(image));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void setThumbLEmpty() {
		ImageIcon image = new ImageIcon("emptyImg.png");
		thumbL.setIcon(image);
	}

	public void setStaitcMapEmpty() {
		ImageIcon image = new ImageIcon("emptyImg.png");
		staitcMap.setIcon(image);
	}

	public JLabel getRestTitle() {
		return restTitle;
	}

	public void setRestTitle(String restTitle) {
		this.restTitle.setText(restTitle);
	}

	public JLabel getRestRprsntvMenu() {
		return restRprsntvMenu;
	}

	public void setRestRprsntvMenu(String restRprsntvMenu) {
		this.restRprsntvMenu.setText("• 주요 메뉴 : " + restRprsntvMenu);
	}

	public JLabel getRestADDR() {
		return restADDR;
	}

	public void setRestADDR(String restADDR) {
		this.restADDR.setText("• 주소지 : " + restADDR);
	}

	public JLabel getRestCntctTEL() {
		return restCntctTEL;
	}

	public void setRestCntctTEL(String restCntctTEL) {
		this.restCntctTEL.setText("• 연락처 : " + restCntctTEL);
	}

	public JLabel getRestUsageTime() {
		return restUsageTime;
	}

	public void setRestUsageTime(String restUsageTime) {
		this.restUsageTime.setText("• 영업시간 : " + restUsageTime);
	}

	public JLabel getRestItemCntnts() {
		return restItemCntnts2;
	}

	public void setRestItemCntnts2(String restItemCntnts2) {
		this.restItemCntnts2.setText("<html><p style=\"width:300px;\">" + restItemCntnts2 + "</p></html>");
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
		Main busanDB = new Main();
		busanDB.main(args);
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

		contentPane.setLayout(card);
		c = getContentPane();
		ImageIcon icon = new ImageIcon("부산_리사이징.png");
		firstPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		firstPanel.setBackground(new Color(255, 255, 255));
		contentPane.add(firstPanel, "FirstScreen");
		firstPanel.setLayout(null);

		JLabel programMainTitle = new JLabel("돼동여지도");
		programMainTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		programMainTitle.setBounds(50, 30, 133, 39);
		firstPanel.add(programMainTitle);

		zones = new String[] { "부산 전체", "부산진구", "사상구", "북구", "남구", "서구", "중구", "동구", "강서구", "수영구", "동래구", "연제구", "해운대구",
				"영도구", "금정구", "사하구" };
		String[] food = { "분류 없음", "한식", "중식", "양식", "일식", };
		String[] rating = { "★이상", "★★이상", "★★★이상", "★★★★이상", "★★★★★" };
		SEARCHTOOLS searchTool = new SEARCHTOOLS();
		List<String> list = searchTool.searchLoca();

		zoneComboBox = new JComboBox(zones);
		zoneComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		zoneComboBox.setBackground(Color.WHITE);
		zoneComboBox.setBounds(50, 90, 80, 30);
		zoneComboBox.addActionListener(new ComboBoxListener(GUI2.this));
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
		secondPanel.setLayout(null);
		secondPanel.setBackground(new Color(255, 255, 255));
		contentPane.add(secondPanel, "SecondScreen");

		JButton goBackButton = new JButton("뒤로가기");
		goBackButton.setBounds(861, 643, 97, 32);
		goBackButton.setBackground(new Color(135, 206, 235));
		goBackButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		goBackButton.addActionListener(new GoBackActionListener(GUI2.this));
		secondPanel.add(goBackButton);

		JPanel secondMainPanel = new JPanel();
		secondMainPanel.setForeground(Color.GRAY);
		secondMainPanel.setBounds(20, 20, 938, 360);
		secondMainPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
		secondMainPanel.setBackground(new Color(255, 255, 255));
		secondPanel.add(secondMainPanel);
		secondMainPanel.setLayout(null);

		thumbL = new JLabel("");
		thumbL.setForeground(Color.GRAY);
		thumbL.setHorizontalAlignment(SwingConstants.CENTER);
		thumbL.setBounds(494, 15, 427, 330);
		thumbL.setBorder(new LineBorder((new Color(128, 128, 128)), 1, false));
		secondMainPanel.add(thumbL);

		restTitle = new JLabel("목록에서 식당을 선택하세요");
		restTitle.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		restTitle.setBounds(27, 20, 337, 47);
		secondMainPanel.add(restTitle);

		restRprsntvMenu = new JLabel("• 주요 메뉴 : ");
		restRprsntvMenu.setVerticalAlignment(SwingConstants.TOP);
		restRprsntvMenu.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restRprsntvMenu.setBounds(27, 85, 449, 25);
		secondMainPanel.add(restRprsntvMenu);

		restADDR = new JLabel("• 주소지 : ");
		restADDR.setVerticalAlignment(SwingConstants.TOP);
		restADDR.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restADDR.setBounds(27, 120, 449, 25);
		secondMainPanel.add(restADDR);

		restCntctTEL = new JLabel("• 연락처 : ");
		restCntctTEL.setVerticalAlignment(SwingConstants.TOP);
		restCntctTEL.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restCntctTEL.setBounds(27, 155, 449, 25);
		secondMainPanel.add(restCntctTEL);

		restUsageTime = new JLabel("• 영업시간 : ");
		restUsageTime.setVerticalAlignment(SwingConstants.TOP);
		restUsageTime.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restUsageTime.setBounds(27, 190, 449, 25);
		secondMainPanel.add(restUsageTime);

		JLabel restItemCntnts = new JLabel("• 소개 : ");
		restItemCntnts.setVerticalAlignment(SwingConstants.TOP);
		restItemCntnts.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restItemCntnts.setBounds(27, 225, 60, 111);
		secondMainPanel.add(restItemCntnts);

		restItemCntnts2 = new JLabel("");
		restItemCntnts2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restItemCntnts2.setVerticalAlignment(SwingConstants.TOP);
		restItemCntnts2.setBounds(83, 225, 388, 125);
		secondMainPanel.add(restItemCntnts2);

		JButton btnReviewDialogPopUp = new JButton("review");
		secondMainPanel.add(btnReviewDialogPopUp);
		btnReviewDialogPopUp.setBounds(400, 320, 80, 20);

		JScrollPane listScrollPane = new JScrollPane();
		listScrollPane.setViewportBorder(new EmptyBorder(3, 3, 0, 0));
		listScrollPane.setBounds(20, 425, 350, 250);
		secondPanel.add(listScrollPane);

		// 리뷰창 팝업 액션리스너
		btnReviewDialogPopUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reviewDialog dialog = new reviewDialog(GUI2.this);
				dialog.setVisible(true);
			}
		});

		searchingList = new JList<>();
		searchingList.setForeground(Color.BLACK);
		searchingList.setBorder(new LineBorder(Color.WHITE));
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
		searchingList.setValueIsAdjusting(true);
		searchingList.addListSelectionListener(new SecondPanelListListener(this));

		JLabel restList = new JLabel("식당 목록");
		restList.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		restList.setVerticalAlignment(SwingConstants.BOTTOM);
		restList.setBounds(23, 390, 90, 30);
		secondPanel.add(restList);

		mapTitleLabel = new JLabel("지도");
		mapTitleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		mapTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mapTitleLabel.setBounds(403, 390, 50, 30);
		secondPanel.add(mapTitleLabel);

		staticMapPanel = new JPanel();
		staticMapPanel.setForeground(Color.GRAY);
		staticMapPanel.setBorder(new LineBorder(Color.GRAY));
		staticMapPanel.setBackground(Color.WHITE);
		staticMapPanel.setBounds(400, 425, 350, 250);
		secondPanel.add(staticMapPanel);
		staticMapPanel.setLayout(null);

		staitcMap = new JLabel("");
		staitcMap.setBounds(3, 3, 344, 244);
		staitcMap.setHorizontalAlignment(SwingConstants.CENTER);
		staticMapPanel.add(staitcMap);

	}
}
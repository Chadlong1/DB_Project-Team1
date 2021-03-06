package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
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

import ReviewAndRatings.ReviewDB.ReviewRepository;
import SEARCHINFO.SEARCHTOOLS;
import busan.Main;
import listeners.MainGUI.ComboBoxListener;
import listeners.MainGUI.GoBackActionListener;
import listeners.MainGUI.MapZoneSelectionMouseListener;
import listeners.MainGUI.SearchActionListener;
import listeners.MainGUI.SecondPanelListListener;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private CardLayout card = new CardLayout(0, 0);
	private JComboBox<String> zoneComboBox;
	private JComboBox<String> foodComboBox;
	private JComboBox<String> ratingComboBox;
	private Image image;
	private JList<String> searchingList;
	private JLabel thumbL;
	private JLabel restTitle;
	private JLabel rating;
	private JLabel restRprsntvMenu;
	private JLabel restADDR;
	private JLabel restCntctTEL;
	private JLabel restUsageTime;
	private JLabel restItemCntnts2;
	private JPanel firstPanel;
	private String[] zones = new String[] { "부산 전체", "부산진구", "사상구", "북구", "남구", "서구", "중구", "동구", "강서구", "수영구", "동래구",
			"연제구", "해운대구", "영도구", "금정구", "사하구", "기장군" };
	private JLabel mapTitleLabel;
	private JPanel staticMapPanel;
	private JLabel staticMap;
	public JButton btnReviewDialogPopUp;
	private JPanel[] zonePanArr;
	private JLabel loadingLabel;
	private int selectedBpmId;

	public int getSelectedBpmId() {
		return selectedBpmId;
	}

	public void setSelectedBpmId(int selectedBpmId) {
		this.selectedBpmId = selectedBpmId;
	}

	public void setLoadingLabel(ImageIcon ii) {
		loadingLabel.setIcon(ii);
	}

	public JPanel[] getZonePanArr() {
		return zonePanArr;
	}

	public void setZoneComboBox(String zone) {
		zoneComboBox.setSelectedItem(zone);
	}

	public String[] getZones() {
		return zones;
	}

	public void setCursor(Cursor cursor) {
		firstPanel.setCursor(cursor);
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
			staticMap.setIcon(new ImageIcon(image));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public JLabel getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating.setText(rating);
	}

	public void setThumbLEmpty() {
		ImageIcon image = new ImageIcon("");
		thumbL.setIcon(image);
	}

	public void setStaitcMapEmpty() {
		ImageIcon image = new ImageIcon("");
		staticMap.setIcon(image);
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
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainGUI() {
		setTitle("돼동여지도");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 200, 1000, 735);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(3, 3, 3, 3));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		contentPane.setLayout(card);
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

		String[] food = { "분류 없음", "한식", "중식", "양식", "일식", };
		String[] stars = { "선택안함", "★이상", "★★이상", "★★★이상", "★★★★이상", "★★★★★" };
		SEARCHTOOLS searchTool = new SEARCHTOOLS();
		List<String> list = searchTool.searchLoca();

		zoneComboBox = new JComboBox(zones);
		zoneComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		zoneComboBox.setBackground(Color.WHITE);
		zoneComboBox.setBounds(50, 90, 80, 30);
		zoneComboBox.addActionListener(new ComboBoxListener(MainGUI.this));
		firstPanel.add(zoneComboBox);

		foodComboBox = new JComboBox(food);
		foodComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		foodComboBox.setBackground(Color.WHITE);
		foodComboBox.setBounds(150, 90, 80, 30);
		firstPanel.add(foodComboBox);

		ratingComboBox = new JComboBox(stars);
		ratingComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		ratingComboBox.setBackground(Color.WHITE);
		ratingComboBox.setBounds(250, 90, 100, 30);
		firstPanel.add(ratingComboBox);

		JButton searchButton = new JButton("검색");
		searchButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		searchButton.setBackground(new Color(190, 204, 216));
		searchButton.setBounds(370, 90, 80, 30);
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchButton.addActionListener(new SearchActionListener(MainGUI.this));
		firstPanel.add(searchButton);

		JPanel panelSAHA = new JPanel();
		panelSAHA.setBounds(505, 505, 45, 45);

		JPanel panelGEUMJEONG = new JPanel();
		panelGEUMJEONG.setBounds(640, 220, 80, 40);

		JPanel panelHAEUNDAE = new JPanel();
		panelHAEUNDAE.setBounds(730, 335, 70, 35);

		JPanel panelDONGNAE = new JPanel();
		panelDONGNAE.setBounds(635, 315, 65, 35);

		JPanel panelYEONJE = new JPanel();
		panelYEONJE.setBounds(650, 350, 50, 25);

		JPanel panelSUYEONG = new JPanel();
		panelSUYEONG.setBounds(695, 385, 35, 30);

		JPanel panelNAM = new JPanel();
		panelNAM.setBounds(665, 440, 35, 30);

		JPanel panelJIN = new JPanel();
		panelJIN.setBounds(600, 385, 55, 30);

		JPanel panelBUK = new JPanel();
		panelBUK.setBounds(575, 280, 50, 35);

		JPanel panelSASANG = new JPanel();
		panelSASANG.setBounds(515, 380, 55, 35);

		JPanel panelDONG = new JPanel();
		panelDONG.setBounds(605, 445, 35, 25);

		JPanel panelSEO = new JPanel();
		panelSEO.setBounds(555, 455, 35, 30);

		JPanel panelJUNG = new JPanel();
		panelJUNG.setBounds(595, 484, 30, 32);

		JPanel panelYEONGDO = new JPanel();
		panelYEONGDO.setBounds(620, 520, 60, 35);

		JPanel panelGANGSEO = new JPanel();
		panelGANGSEO.setBounds(340, 440, 80, 50);

		JPanel panelGANGSEO2 = new JPanel();
		panelGANGSEO2.setBounds(430, 300, 80, 80);

		JPanel panelGIJANG = new JPanel();
		panelGIJANG.setBounds(800, 150, 80, 60);

		zonePanArr = new JPanel[] { panelSAHA, panelGEUMJEONG, panelHAEUNDAE, panelDONGNAE, panelYEONJE, panelSUYEONG,
				panelNAM, panelJIN, panelBUK, panelSASANG, panelDONG, panelSEO, panelJUNG, panelYEONGDO, panelGANGSEO,
				panelGANGSEO2, panelGIJANG };

		for (int i = 0; i < zonePanArr.length; i++) {
			firstPanel.add(zonePanArr[i]);
			zonePanArr[i].setOpaque(false);
			zonePanArr[i].addMouseListener(new MapZoneSelectionMouseListener(MainGUI.this));
		}

		JPanel secondPanel = new JPanel();
		secondPanel.setLayout(null);
		secondPanel.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(secondPanel, "SecondScreen");

		loadingLabel = new JLabel();
		loadingLabel.setOpaque(false);
		loadingLabel.setBounds(297, 264, 400, 200);
		secondPanel.add(loadingLabel);

		JPanel secondMainPanel = new JPanel();
		secondMainPanel.setForeground(Color.GRAY);
		secondMainPanel.setBounds(20, 20, 938, 360);
		secondMainPanel.setBorder(new LineBorder(SystemColor.inactiveCaption));
		secondMainPanel.setBackground(new Color(255, 255, 255));
		secondPanel.add(secondMainPanel);
		secondMainPanel.setLayout(null);

		thumbL = new JLabel("");
		thumbL.setForeground(Color.GRAY);
		thumbL.setHorizontalAlignment(SwingConstants.CENTER);
		thumbL.setBounds(494, 15, 427, 330);
		thumbL.setBorder(new LineBorder(SystemColor.inactiveCaption));
		secondMainPanel.add(thumbL);

		restTitle = new JLabel("목록에서 식당을 선택하세요");
		restTitle.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		restTitle.setBounds(27, 20, 337, 47);
		secondMainPanel.add(restTitle);

		rating = new JLabel("평점");
		rating.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		rating.setBounds(400, 20, 82, 47);
		secondMainPanel.add(rating);
		secondMainPanel.revalidate();
		secondMainPanel.repaint();

		restRprsntvMenu = new JLabel("• 주요 메뉴 : ");
		restRprsntvMenu.setVerticalAlignment(SwingConstants.TOP);
		restRprsntvMenu.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restRprsntvMenu.setBounds(27, 75, 449, 25);
		secondMainPanel.add(restRprsntvMenu);

		restADDR = new JLabel("• 주소지 : ");
		restADDR.setVerticalAlignment(SwingConstants.TOP);
		restADDR.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restADDR.setBounds(27, 110, 449, 25);
		secondMainPanel.add(restADDR);

		restCntctTEL = new JLabel("• 연락처 : ");
		restCntctTEL.setVerticalAlignment(SwingConstants.TOP);
		restCntctTEL.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restCntctTEL.setBounds(27, 145, 449, 25);
		secondMainPanel.add(restCntctTEL);

		restUsageTime = new JLabel("• 영업시간 : ");
		restUsageTime.setVerticalAlignment(SwingConstants.TOP);
		restUsageTime.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restUsageTime.setBounds(27, 180, 449, 25);
		secondMainPanel.add(restUsageTime);

		JLabel restItemCntnts = new JLabel("• 소개 : ");
		restItemCntnts.setVerticalAlignment(SwingConstants.TOP);
		restItemCntnts.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restItemCntnts.setBounds(27, 215, 60, 111);
		secondMainPanel.add(restItemCntnts);

		restItemCntnts2 = new JLabel("");
		restItemCntnts2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restItemCntnts2.setVerticalAlignment(SwingConstants.TOP);
		restItemCntnts2.setBounds(83, 215, 388, 125);
		secondMainPanel.add(restItemCntnts2);

		btnReviewDialogPopUp = new JButton("review");
		btnReviewDialogPopUp.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnReviewDialogPopUp.setBackground(new Color(135, 206, 235));
		btnReviewDialogPopUp.setBounds(400, 320, 80, 32);
		btnReviewDialogPopUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		secondMainPanel.add(btnReviewDialogPopUp);
		secondMainPanel.setComponentZOrder(btnReviewDialogPopUp, 0);
		btnReviewDialogPopUp.setBounds(400, 333, 80, 20);
		btnReviewDialogPopUp.setVisible(false);

		JButton goBackButton = new JButton("뒤로가기");
		goBackButton.setBounds(861, 643, 97, 32);
		goBackButton.setBackground(new Color(135, 206, 235));
		goBackButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		goBackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		goBackButton.addActionListener(new GoBackActionListener(this, btnReviewDialogPopUp));
		secondPanel.add(goBackButton);

		JScrollPane listScrollPane = new JScrollPane();
		listScrollPane.setBorder(new LineBorder(SystemColor.inactiveCaption));
		listScrollPane.setViewportBorder(new EmptyBorder(3, 3, 0, 0));
		listScrollPane.setBounds(20, 425, 350, 250);
		secondPanel.add(listScrollPane);

		// 리뷰창 팝업 액션리스너
		btnReviewDialogPopUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int commentCount = ReviewRepository.getCommentCount(selectedBpmId);
				ReviewDialog dialog = new ReviewDialog(MainGUI.this, commentCount);
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
		searchingList.addListSelectionListener(new SecondPanelListListener(this, btnReviewDialogPopUp));

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
		staticMapPanel.setBorder(new LineBorder(SystemColor.inactiveCaption));
		staticMapPanel.setBackground(Color.WHITE);
		staticMapPanel.setBounds(400, 425, 350, 250);
		secondPanel.add(staticMapPanel);
		staticMapPanel.setLayout(null);

		staticMap = new JLabel("");
		staticMap.setBorder(new LineBorder(SystemColor.inactiveCaptionBorder));
		staticMap.setBounds(3, 3, 344, 244);
		staticMap.setHorizontalAlignment(SwingConstants.CENTER);
		staticMapPanel.add(staticMap);

	}
}
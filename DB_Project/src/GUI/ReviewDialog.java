package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ReviewAndRatings.ReviewDB.ReviewOutput;
import ReviewAndRatings.ReviewDB.ReviewRepository;
import SEARCHINFO.SEARCHTOOLS;
import busan.Restaurant;
import listeners.ReviewDialog.CommentFieldML;
import listeners.ReviewDialog.ReplyCommentFieldKL;
import listeners.ReviewDialog.ReplyCommentFieldML;
import listeners.ReviewDialog.CommentFieldKL;
import listeners.ReviewDialog.leaveCommentAL;

public class ReviewDialog extends JDialog {
	private JList<String> searchingList;
	private int bpmIdNum;
	private int count;
	private int depth;
	private double rating;

	private int selectedBundleNum;

	private final JPanel contentPanel = new JPanel();
	private JTextField commentField;
	private JTextField replyCommentField;
	private JPanel commentCard;
	private JPanel ratingPanel;
	private JPanel ratingPanel_2;
	private CardLayout card = new CardLayout(0, 0);
	private JPanel commentScreen;
	private JScrollPane scrollPane;
	private JLabel resTitleReview;
	private JPanel commentLayout;
	private JPanel tempReplyCommPanel;
	private static String basicComment = "후기를 입력해주세요";
	private static String basicReplyComment = "대댓글을 작성해주세요";
	private JButton leaveBtn;
	private JButton replyLeaveBtn;
	private JComboBox<String> scoreComboBox;
	private int tempReplyCommentLayOutPointY;
	private String[] stars = new String[] { "별점 입력", "★", "★★", "★★★", "★★★★", "★★★★★" };

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JPanel getCommentLayout() {
		return commentLayout;
	}

	public JPanel getRatingPanel2() {
		return ratingPanel_2;
	}

	public JPanel getRatingPanel() {
		return ratingPanel;
	}

	public JPanel getCommentScreen() {
		return commentScreen;
	}

	public int getCount() {
		return count;
	}

	public int getSelectedBundleNum() {
		return selectedBundleNum;
	}

	public void leaveBtnEnable() {
		leaveBtn.setEnabled(true);
	}

	public void leaveBtnDisable() {
		leaveBtn.setEnabled(false);
	}

	public double getRating() {
		return rating;
	}

	public CardLayout getCard() {
		return card;
	}

	public JTextField getCommentField() {
		return commentField;
	}

	public JTextField getReplyCommentField() {
		return replyCommentField;
	}

	public String selectedRating() {
		return (String) scoreComboBox.getSelectedItem();
	}

	public String[] getStars() {
		return stars;
	}

	public ReviewDialog(MainGUI parent, int commentCount) {
		super(parent, "후기", true);
		searchingList = parent.getSearchingList();
		setResizable(false);

//		setBounds(100, 100, 590, 490); // 리뷰창 크기조절 막고 크기조정함. 확인 후 변경 가능
//		setLocation(1200, 350); // 리뷰창 위치. 뒤로가기 버튼 안 가리고 사진도 덜 가리게 조절함. 확인 후 변경 가능
		setBounds(parent.getX() + 205, parent.getY() + 122, 590, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(SystemColor.activeCaption);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		commentLayout = new JPanel(null);
		commentLayout.setBorder(new LineBorder(SystemColor.activeCaption));
		commentLayout.setBackground(SystemColor.inactiveCaptionBorder);
		commentLayout.setBounds(10, 10, 564, 338);
		contentPanel.add(commentLayout);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 40, 396, 286);
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도 조절
		commentLayout.add(scrollPane);

		commentScreen = new JPanel(null);
		commentScreen.setBorder(new LineBorder(SystemColor.inactiveCaption));
		commentScreen.setBackground(Color.WHITE);
		commentScreen.setSize(396, commentCount * 90);
		scrollPane.setViewportView(commentScreen);

		// JList로 선택된 음식점 정보 (tempReviewPanel 패널에 노출)
		for (int i = 0; i <= searchingList.getLastVisibleIndex(); i++) {
			if (searchingList.getSelectedIndex() == i) {
				String selectedItemStr = searchingList.getSelectedValue();
				Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
				bpmIdNum = SEARCHTOOLS.searchIdNum(tempRest.getTitle());
				ReviewRepository.viewReviewAll(bpmIdNum);
				resTitleReview = new JLabel(tempRest.getTitle());

				createRatingPanel1(ReviewRepository.viewRating(bpmIdNum));
				int[] arr = { 0, 0, 0, 0, 0 };
				for (int j = 0; j < ReviewRepository.viewReviewAtBpmId(bpmIdNum).size(); j++) {
					List<ReviewOutput> list = new ArrayList<>();
					list = ReviewRepository.viewReviewAtBpmId(bpmIdNum);

					double ratingArr = list.get(j).getRating();
					if (ratingArr == 1.0) {
						arr[0]++;
					} else if (ratingArr == 2.0) {
						arr[1]++;
					} else if (ratingArr == 3.0) {
						arr[2]++;
					} else if (ratingArr == 4.0) {
						arr[3]++;
					} else if (ratingArr == 5.0) {
						arr[4]++;
					}
				}

				for (int j = 0; j < ReviewRepository.viewReviewAll(bpmIdNum).size(); j++) {
					List<ReviewOutput> list = new ArrayList<>();
					list = ReviewRepository.viewReviewAll(bpmIdNum);

					leaveComment(list.get(j), count++);
				}
				createRatingPanel2(arr);
			}
		}
		// -----------------------------------------------------------

		resTitleReview.setVerticalAlignment(SwingConstants.TOP);
		resTitleReview.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		resTitleReview.setBounds(12, 12, 396, 28);
		commentLayout.add(resTitleReview);

		commentCard = new JPanel(card);
		commentCard.setBounds(10, 358, 564, 93);
		contentPanel.add(commentCard);

		JPanel normalComment = new JPanel();
		normalComment.setBorder(new LineBorder(SystemColor.activeCaption));
		normalComment.setBackground(SystemColor.inactiveCaptionBorder);
		commentCard.add(normalComment, "NORMAL");
		normalComment.setLayout(null);

		commentField = new JTextField(basicComment);
		commentField.setBorder(new LineBorder(SystemColor.inactiveCaption));
		commentField.setForeground(Color.GRAY);
		commentField.setHorizontalAlignment(SwingConstants.LEFT);
		commentField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		commentField.setBounds(12, 12, 430, 70);
		normalComment.add(commentField);
		commentField.setColumns(10);
		commentField.addMouseListener(new CommentFieldML(commentField));
		commentField.addKeyListener(new CommentFieldKL(ReviewDialog.this, commentField));
		// 마우스리스너, 키리스너 코드 다른 클래스파일로 이전

		leaveBtn = new JButton("등록");
		leaveBtn.setEnabled(false);
		leaveBtn.setBackground(new Color(135, 206, 235));
		leaveBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		leaveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		leaveBtn.setBounds(455, 12, 97, 29);
		normalComment.add(leaveBtn);
		leaveBtn.addActionListener(new leaveCommentAL(parent, ReviewDialog.this, searchingList));
		// 액션리스너 코드 leaveCommentAL 클래스 파일로 이전

		scoreComboBox = new JComboBox(stars);
		scoreComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		scoreComboBox.setBackground(Color.WHITE);
		scoreComboBox.setBounds(455, 53, 97, 29);
		normalComment.add(scoreComboBox);
		scoreComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String countStar = (String) scoreComboBox.getSelectedItem();
				if (countStar == stars[1]) {
					rating = 1.0;
				} else if (countStar == stars[2]) {
					rating = 2.0;
				} else if (countStar == stars[3]) {
					rating = 3.0;
				} else if (countStar == stars[4]) {
					rating = 4.0;
				} else if (countStar == stars[5]) {
					rating = 5.0;
				}

				if (countStar == stars[0]) {
					leaveBtn.setEnabled(false);
				} else {
					leaveBtn.setEnabled(true);
				}

			}
		});

		JPanel replyComment = new JPanel();
		replyComment.setBorder(new LineBorder(SystemColor.activeCaption));
		replyComment.setBackground(SystemColor.inactiveCaptionBorder);
		commentCard.add(replyComment, "ReplyComment");
		replyComment.setLayout(null);

		replyCommentField = new JTextField(basicReplyComment);
		replyCommentField.setForeground(Color.GRAY);
		replyCommentField.setHorizontalAlignment(SwingConstants.LEFT);
		replyCommentField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		replyCommentField.setColumns(10);
		replyCommentField.setBounds(12, 12, 430, 70);
		replyComment.add(replyCommentField);
		replyCommentField.addMouseListener(new ReplyCommentFieldML(replyCommentField));
		replyCommentField.addKeyListener(new ReplyCommentFieldKL(replyCommentField));
		// replyCommentField 키 리스너, 마우스 리스너 코드 리스너스 패키지로 이전

		// 두번째 카드레이아웃에서 다이얼로그 UI를 똑같이 생성후, 선택한 댓글만 최상단에 생성, depth가 1인 리뷰만 노출되게 설정해야함
		replyLeaveBtn = new JButton("등록");
		replyLeaveBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		replyLeaveBtn.setBackground(new Color(135, 206, 235));
		replyLeaveBtn.setBounds(455, 12, 97, 29);
		replyLeaveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		replyComment.add(replyLeaveBtn);
		replyLeaveBtn.addActionListener(new replyLeaveBtnAL(ReviewDialog.this, searchingList));

		JButton replyGoBackBtn = new JButton("작성 취소");
		replyGoBackBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		replyGoBackBtn.setBackground(new Color(135, 206, 235));
		replyGoBackBtn.setBounds(455, 53, 97, 29);
		replyGoBackBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		replyComment.add(replyGoBackBtn);
		replyGoBackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getCard().show(commentCard, "NORMAL");
				remove(tempReplyCommPanel);
				commentScreen.revalidate();
				commentScreen.repaint();
			}
		});

	}

	// tempReviewPanel 패널에 리뷰 생성 메소드 ----------------------------
	public void leaveComment(ReviewOutput ro, int count) {
		ro.getReviewId();
		ro.getBundleNum();
		int depth = ro.getDepth();

		// 그냥 리뷰 입력
		if (depth == 0) {
			JPanel tempReviewPanel = new JPanel(null);
			tempReviewPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
			tempReviewPanel.setBackground(Color.WHITE);
			tempReviewPanel.setBounds(12, 12 + (97 * count), 330, 85);
			tempReviewPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			commentScreen.add(tempReviewPanel);

			// 리뷰창에서 댓글 선택시 해당 리뷰의 bundleNum을 저장할 searchingBundleNum 생성
			JLabel bundleNumLbl = new JLabel(String.valueOf(ro.getBundleNum()));
			tempReviewPanel.add(bundleNumLbl);
			bundleNumLbl.setVisible(false);

			tempReviewPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Object o = e.getSource();
					if (o == tempReviewPanel) {
						tempReplyCommentLayOutPointY = tempReviewPanel.getY() + tempReviewPanel.getHeight() + 5;
					}

					CardLayout card = getCard();
					card.show(commentCard, "ReplyComment");
					selectedBundleNum = Integer.valueOf(bundleNumLbl.getText());
					if(tempReviewPanel.getBackground() == Color.white) {
						tempReviewPanel.setBackground(Color.PINK);
					} else {
						tempReviewPanel.setBackground(Color.WHITE);
					}
				}
			});
			System.out.println(commentScreen.getHeight());
			// tempReviewPanel패널에 별점 등록
			rating = ro.getRating();
			JLabel star_5_1 = new JLabel("★");
			JLabel star_4_1 = new JLabel("★");
			JLabel star_3_1 = new JLabel("★");
			JLabel star_2_1 = new JLabel("★");
			JLabel star_1_1 = new JLabel("★");

			if (rating == 5) {
				star_5_1.setHorizontalAlignment(SwingConstants.LEFT);
				star_5_1.setForeground(Color.ORANGE);
				star_5_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_5_1.setBounds(56, 7, 12, 15);
				tempReviewPanel.add(star_5_1);
			}
			if (rating >= 4) {
				star_4_1.setHorizontalAlignment(SwingConstants.LEFT);
				star_4_1.setForeground(Color.ORANGE);
				star_4_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_4_1.setBounds(45, 7, 12, 15);
				tempReviewPanel.add(star_4_1);
			}
			if (rating >= 3) {
				star_3_1.setHorizontalAlignment(SwingConstants.LEFT);
				star_3_1.setForeground(Color.ORANGE);
				star_3_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_3_1.setBounds(34, 7, 12, 15);
				tempReviewPanel.add(star_3_1);
			}
			if (rating >= 2) {
				star_2_1.setHorizontalAlignment(SwingConstants.LEFT);
				star_2_1.setForeground(Color.ORANGE);
				star_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_2_1.setBounds(23, 7, 12, 15);
				tempReviewPanel.add(star_2_1);
			}
			if (rating >= 1) {
				star_1_1.setHorizontalAlignment(SwingConstants.LEFT);
				star_1_1.setForeground(Color.ORANGE);
				star_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_1_1.setBounds(12, 7, 12, 15);
				tempReviewPanel.add(star_1_1);
			} else {
				JLabel star_0_1 = new JLabel("★");
				star_0_1.setHorizontalAlignment(SwingConstants.LEFT);
				star_0_1.setForeground(Color.DARK_GRAY);
				star_0_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_0_1.setBounds(12, 7, 12, 15);
				tempReviewPanel.add(star_0_1);
			}
			// tempReviewPanel패널에 리뷰 등록
			JLabel comment = new JLabel("<html><p style=\"width:230px;\">" + ro.getReview() + "</p></html>");
			comment.setVerticalAlignment(SwingConstants.TOP);
			comment.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
			comment.setBounds(12, 22, 306, 40);
			tempReviewPanel.add(comment);

			// tempReviewPanel패널에 작성날짜 등록
			JLabel reviewDate = new JLabel(String.valueOf(ro.getTimestamp()));
			reviewDate.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			reviewDate.setHorizontalAlignment(SwingConstants.RIGHT);
			reviewDate.setBounds(160, 65, 158, 15);
			tempReviewPanel.add(reviewDate);

			if (count >= 2) {
				Dimension size = new Dimension();
				int y = commentScreen.getHeight() + (97 * (count - 1));
				size.setSize(396, y);
				commentScreen.setPreferredSize(size);
				scrollPane.setViewportView(commentScreen);
			}
			commentScreen.repaint();
			commentScreen.revalidate();

			// 대댓글 입력
		} else if (depth == 1) {
			tempReplyCommPanel = new JPanel(null);
			tempReplyCommPanel.setBackground(SystemColor.inactiveCaptionBorder);
			tempReplyCommPanel.setBounds(35, 12 + ((count) * 97), 330, 85);
			tempReplyCommPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
			commentScreen.add(tempReplyCommPanel);

			JLabel replyComment = new JLabel(ro.getReview());
			replyComment.setVerticalAlignment(SwingConstants.TOP);
			replyComment.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
			replyComment.setBounds(12, 12, 306, 40);
			tempReplyCommPanel.add(replyComment);

			JLabel replyReviewDate = new JLabel(String.valueOf(ro.getTimestamp()));
			replyReviewDate.setHorizontalAlignment(SwingConstants.RIGHT);
			replyReviewDate.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			replyReviewDate.setBounds(160, 55, 158, 15);
			tempReplyCommPanel.add(replyReviewDate);

			Dimension size = new Dimension();
			int y = commentScreen.getHeight() + 80;
			size.setSize(396, y);
			commentScreen.setPreferredSize(size);
			scrollPane.setViewportView(commentScreen);
		}
	}

//	public void leaveReplyComment(ReviewOutput ro, int count) {
//		tempReplyCommPanel = new JPanel(null);
//		tempReplyCommPanel.setBackground(SystemColor.inactiveCaptionBorder);
//		tempReplyCommPanel.setBounds(12, tempReplyCommentLayOutPointY + ((count - 1) * 80), 450, 75);
//		tempReplyCommPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
//		commentScreen.add(tempReplyCommPanel);
//
//		JLabel replyComment = new JLabel(ro.getReview());
//		replyComment.setVerticalAlignment(SwingConstants.TOP);
//		replyComment.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
//		replyComment.setBounds(12, 12, 306, 40);
//		tempReplyCommPanel.add(replyComment);
//
//		JLabel replyReviewDate = new JLabel(String.valueOf(ro.getTimestamp()));
//		replyReviewDate.setHorizontalAlignment(SwingConstants.RIGHT);
//		replyReviewDate.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
//		replyReviewDate.setBounds(160, 55, 158, 15);
//		tempReplyCommPanel.add(replyReviewDate);
//
//		Dimension size = new Dimension();
//		int y = commentScreen.getHeight() + 80;
//		size.setSize(396, y);
//		commentScreen.setPreferredSize(size);
//		scrollPane.setViewportView(commentScreen);
//	}

	// ratingPanel패널에 리뷰가 존재하면 평점,별 출력
	// 존재하지 않으면 그림 표시
	public void createRatingPanel1(double avgRating) {
		ratingPanel = new JPanel();
		ratingPanel.setBorder(new LineBorder(SystemColor.inactiveCaption));
		ratingPanel.setBackground(Color.WHITE);
		ratingPanel.setBounds(420, 40, 130, 130);
		commentLayout.add(ratingPanel);
		ratingPanel.setLayout(null);

		if (avgRating > 0) {
			String strRating = String.format("%.2f", avgRating);

			String.valueOf(avgRating);
			JLabel avgScore = new JLabel(strRating);
			avgScore.setBounds(25, 35, 80, 50);
			ratingPanel.add(avgScore);
			avgScore.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
			avgScore.setHorizontalAlignment(SwingConstants.CENTER);

			if (avgRating > 0 && avgRating < 1) {
				JLabel star_1 = new JLabel("☆");
				star_1.setHorizontalAlignment(SwingConstants.LEFT);
				star_1.setForeground(Color.ORANGE);
				star_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_1.setBounds(38, 82, 12, 15);
				ratingPanel.add(star_1);
			}
			if (avgRating >= 1) {
				JLabel star_1 = new JLabel("★");
				star_1.setHorizontalAlignment(SwingConstants.LEFT);
				star_1.setForeground(Color.ORANGE);
				star_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_1.setBounds(38, 82, 12, 15);
				ratingPanel.add(star_1);
			}
			if (avgRating >= 1.50 && avgRating < 2) {
				JLabel star_2 = new JLabel("☆");
				star_2.setHorizontalAlignment(SwingConstants.LEFT);
				star_2.setForeground(Color.ORANGE);
				star_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_2.setBounds(49, 82, 12, 15);
				ratingPanel.add(star_2);
			}
			if (avgRating >= 2) {
				JLabel star_2 = new JLabel("★");
				star_2.setHorizontalAlignment(SwingConstants.LEFT);
				star_2.setForeground(Color.ORANGE);
				star_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_2.setBounds(49, 82, 12, 15);
				ratingPanel.add(star_2);
			}
			if (avgRating >= 2.50 && avgRating < 3) {
				JLabel star_3 = new JLabel("☆");
				star_3.setHorizontalAlignment(SwingConstants.LEFT);
				star_3.setForeground(Color.ORANGE);
				star_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_3.setBounds(60, 82, 12, 15);
				ratingPanel.add(star_3);
			}
			if (avgRating >= 3) {
				JLabel star_3 = new JLabel("★");
				star_3.setHorizontalAlignment(SwingConstants.LEFT);
				star_3.setForeground(Color.ORANGE);
				star_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_3.setBounds(60, 82, 12, 15);
				ratingPanel.add(star_3);
			}
			if (avgRating >= 3.50 && avgRating < 4) {
				JLabel star_4 = new JLabel("☆");
				star_4.setHorizontalAlignment(SwingConstants.LEFT);
				star_4.setForeground(Color.ORANGE);
				star_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_4.setBounds(71, 82, 12, 15);
				ratingPanel.add(star_4);
			}
			if (avgRating >= 4) {
				JLabel star_4 = new JLabel("★");
				star_4.setHorizontalAlignment(SwingConstants.LEFT);
				star_4.setForeground(Color.ORANGE);
				star_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_4.setBounds(71, 82, 12, 15);
				ratingPanel.add(star_4);
			}
			if (avgRating >= 4.50 && avgRating < 5) {
				JLabel star_5 = new JLabel("☆");
				star_5.setHorizontalAlignment(SwingConstants.LEFT);
				star_5.setForeground(Color.ORANGE);
				star_5.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_5.setBounds(82, 82, 12, 15);
				ratingPanel.add(star_5);
			}
			if (avgRating == 5) {
				JLabel star_5 = new JLabel("★");
				star_5.setHorizontalAlignment(SwingConstants.LEFT);
				star_5.setForeground(Color.ORANGE);
				star_5.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				star_5.setBounds(82, 82, 12, 15);
				ratingPanel.add(star_5);
			}

		} else {
			ImageIcon resImage = new ImageIcon("resImage.png");
			JLabel resImageLabel = new JLabel(resImage);
			resImageLabel.setBounds(0, 0, 130, 130);
			resImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
			ratingPanel.add(resImageLabel);
		}

	}

	// ratingPanel패널에 평점별 선택된 개수 출력
	public void createRatingPanel2(int[] arr) {
		ratingPanel_2 = new JPanel();
		ratingPanel_2.setBorder(new LineBorder(SystemColor.inactiveCaption));
		ratingPanel_2.setBackground(Color.WHITE);
		ratingPanel_2.setBounds(420, 185, 130, 125);
		commentLayout.add(ratingPanel_2);
		ratingPanel_2.setLayout(null);

		JLabel score_5 = new JLabel("5점");
		score_5.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		score_5.setBounds(15, 15, 25, 15);
		ratingPanel_2.add(score_5);

		JLabel score_4 = new JLabel("4점");
		score_4.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		score_4.setBounds(15, 35, 25, 15);
		ratingPanel_2.add(score_4);

		JLabel score_3 = new JLabel("3점");
		score_3.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		score_3.setBounds(15, 55, 25, 15);
		ratingPanel_2.add(score_3);

		JLabel score_2 = new JLabel("2점");
		score_2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		score_2.setBounds(15, 75, 25, 15);
		ratingPanel_2.add(score_2);

		JLabel score_1 = new JLabel("1점");
		score_1.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		score_1.setBounds(15, 95, 25, 15);
		ratingPanel_2.add(score_1);

		JLabel scoreCount_5 = new JLabel(String.valueOf(arr[4]));
		scoreCount_5.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreCount_5.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		scoreCount_5.setBounds(55, 15, 59, 15);
		ratingPanel_2.add(scoreCount_5);

		JLabel scoreCount_4 = new JLabel(String.valueOf(arr[3]));
		scoreCount_4.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreCount_4.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		scoreCount_4.setBounds(55, 35, 59, 15);
		ratingPanel_2.add(scoreCount_4);

		JLabel scoreCount_3 = new JLabel(String.valueOf(arr[2]));
		scoreCount_3.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreCount_3.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		scoreCount_3.setBounds(55, 55, 59, 15);
		ratingPanel_2.add(scoreCount_3);

		JLabel scoreCount_2 = new JLabel(String.valueOf(arr[1]));
		scoreCount_2.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreCount_2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		scoreCount_2.setBounds(55, 75, 59, 15);
		ratingPanel_2.add(scoreCount_2);

		JLabel scoreCount_1 = new JLabel(String.valueOf(arr[0]));
		scoreCount_1.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreCount_1.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		scoreCount_1.setBounds(55, 95, 59, 15);
		ratingPanel_2.add(scoreCount_1);
	}
}

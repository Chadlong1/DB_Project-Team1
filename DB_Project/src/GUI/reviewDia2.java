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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class reviewDia2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField commentField;
	private JTextField replyCommentField;
	private JPanel commentCard;
	private CardLayout card = new CardLayout(0, 0);
	private JPanel commentScreen;
	private int count;
	private JScrollPane scrollPane;

	public CardLayout getCard() {
		return card;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			reviewDia2 dialog = new reviewDia2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public reviewDia2() {
		setTitle("후기");
		setBounds(100, 100, 600, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(SystemColor.activeCaption);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel commentLayout = new JPanel();
		commentLayout.setBorder(new LineBorder(SystemColor.activeCaption));
		commentLayout.setBackground(SystemColor.inactiveCaptionBorder);
		commentLayout.setBounds(10, 10, 564, 338);
		contentPanel.add(commentLayout);
		commentLayout.setLayout(null);

		JPanel ratingPanel = new JPanel();
		ratingPanel.setBorder(new LineBorder(SystemColor.inactiveCaption));
		ratingPanel.setBackground(Color.WHITE);
		ratingPanel.setBounds(420, 40, 130, 130);
		commentLayout.add(ratingPanel);
		ratingPanel.setLayout(null);

		JLabel avgScore = new JLabel("0.0");
		avgScore.setBounds(25, 35, 80, 50);
		ratingPanel.add(avgScore);
		avgScore.setFont(new Font("Comic Sans MS", Font.BOLD, 43));
		avgScore.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel star_1 = new JLabel("★");
		star_1.setHorizontalAlignment(SwingConstants.LEFT);
		star_1.setForeground(Color.ORANGE);
		star_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		star_1.setBounds(38, 82, 12, 15);
		ratingPanel.add(star_1);

		JLabel star_2 = new JLabel("★");
		star_2.setHorizontalAlignment(SwingConstants.LEFT);
		star_2.setForeground(Color.ORANGE);
		star_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		star_2.setBounds(49, 82, 12, 15);
		ratingPanel.add(star_2);

		JLabel star_3 = new JLabel("★");
		star_3.setHorizontalAlignment(SwingConstants.LEFT);
		star_3.setForeground(Color.ORANGE);
		star_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		star_3.setBounds(60, 82, 12, 15);
		ratingPanel.add(star_3);

		JLabel star_4 = new JLabel("★");
		star_4.setHorizontalAlignment(SwingConstants.LEFT);
		star_4.setForeground(Color.ORANGE);
		star_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		star_4.setBounds(71, 82, 12, 15);
		ratingPanel.add(star_4);

		JLabel star_5 = new JLabel("★");
		star_5.setHorizontalAlignment(SwingConstants.LEFT);
		star_5.setForeground(Color.ORANGE);
		star_5.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		star_5.setBounds(82, 82, 12, 15);
		ratingPanel.add(star_5);

		JPanel ratingPanel_2 = new JPanel();
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

		JLabel scoreCount_5 = new JLabel("0");
		scoreCount_5.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreCount_5.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		scoreCount_5.setBounds(55, 15, 59, 15);
		ratingPanel_2.add(scoreCount_5);

		JLabel scoreCount_4 = new JLabel("0");
		scoreCount_4.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreCount_4.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		scoreCount_4.setBounds(55, 35, 59, 15);
		ratingPanel_2.add(scoreCount_4);

		JLabel scoreCount_3 = new JLabel("0");
		scoreCount_3.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreCount_3.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		scoreCount_3.setBounds(55, 55, 59, 15);
		ratingPanel_2.add(scoreCount_3);

		JLabel scoreCount_2 = new JLabel("0");
		scoreCount_2.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreCount_2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		scoreCount_2.setBounds(55, 75, 59, 15);
		ratingPanel_2.add(scoreCount_2);

		JLabel scoreCount_1 = new JLabel("0");
		scoreCount_1.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreCount_1.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		scoreCount_1.setBounds(55, 95, 59, 15);
		ratingPanel_2.add(scoreCount_1);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 40, 396, 286);
		scrollPane.setBorder(null);
		commentLayout.add(scrollPane);

		commentScreen = new JPanel(null);
		commentScreen.setBorder(new LineBorder(SystemColor.inactiveCaption));
		commentScreen.setBackground(Color.WHITE);
		scrollPane.setViewportView(commentScreen);

		JLabel resTitleReview = new JLabel("가게이름 리뷰");
		resTitleReview.setVerticalAlignment(SwingConstants.TOP);
		resTitleReview.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		resTitleReview.setBounds(12, 12, 396, 28);
		commentLayout.add(resTitleReview);

		commentCard = new JPanel();
		commentCard.setBounds(10, 358, 564, 93);
		contentPanel.add(commentCard);
		commentCard.setLayout(card);

		JPanel normalComment = new JPanel();
		normalComment.setBorder(new LineBorder(SystemColor.activeCaption));
		normalComment.setBackground(SystemColor.inactiveCaptionBorder);
		commentCard.add(normalComment, "NORMAL");
		normalComment.setLayout(null);

		commentField = new JTextField();
		commentField.setForeground(Color.GRAY);
		commentField.setHorizontalAlignment(SwingConstants.LEFT);
		commentField.setText("후기를 입력해주세요");
		commentField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		commentField.setBounds(12, 12, 430, 70);
		normalComment.add(commentField);
		commentField.setColumns(10);
		commentField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				commentField.setText("");
				commentField.setForeground(Color.BLACK);
			}
		});

		JButton leaveBtn = new JButton("등록");
		leaveBtn.setBackground(new Color(135, 206, 235));
		leaveBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		leaveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		leaveBtn.setBounds(455, 12, 97, 29);
		normalComment.add(leaveBtn);
		leaveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leaveComment(count++);

			}
		});

		JComboBox scoreComboBox = new JComboBox();
		scoreComboBox.setBackground(Color.WHITE);
		scoreComboBox.setBounds(455, 53, 97, 29);
		normalComment.add(scoreComboBox);

		JPanel replyComment = new JPanel();
		replyComment.setBorder(new LineBorder(SystemColor.activeCaption));
		replyComment.setBackground(SystemColor.inactiveCaptionBorder);
		commentCard.add(replyComment, "ReplyComment");
		replyComment.setLayout(null);

		replyCommentField = new JTextField();
		replyCommentField.setText("대댓글을 작성해주세요");
		replyCommentField.setForeground(Color.GRAY);
		replyCommentField.setHorizontalAlignment(SwingConstants.LEFT);
		replyCommentField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		replyCommentField.setColumns(10);
		replyCommentField.setBounds(12, 12, 430, 70);
		replyComment.add(replyCommentField);

		replyCommentField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				commentField.setText("");
				commentField.setForeground(Color.BLACK);
			}
		});

		JButton replyLeaveBtn = new JButton("등록");
		replyLeaveBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		replyLeaveBtn.setBackground(new Color(135, 206, 235));
		replyLeaveBtn.setBounds(455, 12, 97, 29);
		replyLeaveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		replyComment.add(replyLeaveBtn);

		JButton replyGoBackBtn = new JButton("뒤로가기");
		replyGoBackBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		replyGoBackBtn.setBackground(new Color(135, 206, 235));
		replyGoBackBtn.setBounds(455, 53, 97, 29);
		replyGoBackBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		replyComment.add(replyGoBackBtn);
		replyGoBackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getCard().show(commentCard, "NORMAL");
			}
		});

	}

	public void leaveComment(int count) {
		JPanel tempReviewPanel = new JPanel(null);
		tempReviewPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
		tempReviewPanel.setBackground(Color.WHITE);
		tempReviewPanel.setBounds(12, 12 + (97 * count), 330, 85);
		tempReviewPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		commentScreen.add(tempReviewPanel);
		tempReviewPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				CardLayout card = getCard();
				card.show(commentCard, "ReplyComment");
				System.out.println("클릭반응");
			}

		});

		JLabel comment = new JLabel("<html><p style=\"width:230px;\">"
				+ "222222222222222222222222222222222222222222222222222222222222222222222222222222" + "</p></html>");
		comment.setVerticalAlignment(SwingConstants.TOP);
		comment.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		comment.setBounds(12, 22, 306, 40);
		tempReviewPanel.add(comment);

		JLabel star_1_1 = new JLabel("★");
		star_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		star_1_1.setForeground(Color.ORANGE);
		star_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		star_1_1.setBounds(12, 7, 12, 15);
		tempReviewPanel.add(star_1_1);

		JLabel star_2_1 = new JLabel("★");
		star_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		star_2_1.setForeground(Color.ORANGE);
		star_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		star_2_1.setBounds(23, 7, 12, 15);
		tempReviewPanel.add(star_2_1);

		JLabel star_3_1 = new JLabel("★");
		star_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		star_3_1.setForeground(Color.ORANGE);
		star_3_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		star_3_1.setBounds(34, 7, 12, 15);
		tempReviewPanel.add(star_3_1);

		JLabel star_4_1 = new JLabel("★");
		star_4_1.setHorizontalAlignment(SwingConstants.LEFT);
		star_4_1.setForeground(Color.ORANGE);
		star_4_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		star_4_1.setBounds(45, 7, 12, 15);
		tempReviewPanel.add(star_4_1);

		JLabel star_5_1 = new JLabel("★");
		star_5_1.setHorizontalAlignment(SwingConstants.LEFT);
		star_5_1.setForeground(Color.ORANGE);
		star_5_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		star_5_1.setBounds(56, 7, 12, 15);
		tempReviewPanel.add(star_5_1);

		JLabel reviewDate = new JLabel("날짜");
		reviewDate.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		reviewDate.setHorizontalAlignment(SwingConstants.RIGHT);
		reviewDate.setBounds(222, 65, 96, 15);
		tempReviewPanel.add(reviewDate);

		if (count >= 2) {
			Dimension size = new Dimension();
			int y = 286 + (97 * (count - 1));
			size.setSize(396, y);
			commentScreen.setPreferredSize(size);
			scrollPane.setViewportView(commentScreen);
		}

		commentScreen.repaint();
		commentScreen.revalidate();
	}
}

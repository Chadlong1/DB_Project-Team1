package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ReviewAndRatings.ReviewDB.ReviewInput;
import ReviewAndRatings.ReviewDB.ReviewRepository;
import SEARCHINFO.SEARCHTOOLS;
import busan.Restaurant;


//리뷰 다이얼로그 창
class reviewDialog extends JDialog {
	private JList<String> searchingList;
	double rating;
	private Date writingTime;
	public reviewDialog(GUI2 parent) {
		super(parent, "리뷰창", true);
		searchingList = parent.getSearchingList();

		setLayout(null);
		setBackground(Color.white);

		JPanel dialogPane = new JPanel();
		dialogPane.setLayout(new BoxLayout(dialogPane, BoxLayout.Y_AXIS));
		dialogPane.setBackground(Color.white);

		
		
		JPanel reviewInputInfo = new JPanel(new GridLayout(0, 2));
		reviewInputInfo.setBackground(Color.white);
		JPanel bottomPanel = new JPanel(new GridLayout(0, 2));
		// 리뷰Label과 평점Label이 표기될 패널
		JPanel reviewPanel = new JPanel(new GridLayout(0, 2));
		reviewPanel.setBackground(Color.white);
		// 리뷰작성 텍스트필드
		TextField box = new TextField(35);

		// 리뷰 다이얼로그 실행시 JList 상에 선택된 가게의 리뷰/평점 Label 생성 및 표시
		int idNum;
		for (int i = 0; i <= searchingList.getLastVisibleIndex(); i++) {
			if (searchingList.getSelectedIndex() == i) {
				String selectedItemStr = searchingList.getSelectedValue();
				Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
				idNum = SEARCHTOOLS.searchIdNum(tempRest.getTitle());
				ReviewRepository.viewReviewAtBpmId(idNum);
				for (int j = 0; j < ReviewRepository.viewReviewAtBpmId(idNum).size(); j++) {
					JLabel review = new JLabel(ReviewRepository.viewReviewAtBpmId(idNum).get(j).getReview());
					JLabel rating = new JLabel(
							String.valueOf(ReviewRepository.viewReviewAtBpmId(idNum).get(j).getRating()));
					writingTime = new Date();
					JLabel timeStamp = new JLabel(String.valueOf(writingTime));
					review.setFont(new Font("맑은 고딕", Font.BOLD, 15));
					rating.setFont(new Font("맑은 고딕", Font.BOLD, 15));

					reviewPanel.add(review);
					reviewPanel.add(rating);
					reviewPanel.add(timeStamp);
				}
			}
		}
		JScrollPane reviewScrollPane = new JScrollPane();
		reviewScrollPane.setBounds(1085, 200, 200, 200);
		reviewScrollPane.setViewportView(reviewPanel);
		dialogPane.add(reviewScrollPane);

		JButton btnOK = new JButton("확인");
		btnOK.setEnabled(false);
		JButton btnCloseDialog = new JButton("닫기");

		String[] star = new String[] { "별점 입력", "★", "★★", "★★★", "★★★★", "★★★★★" };

		JComboBox starComboBox = new JComboBox(star);
		starComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		starComboBox.setBackground(Color.WHITE);
		starComboBox.setBounds(50, 90, 80, 30);

		reviewInputInfo.add(box);
		reviewInputInfo.add(starComboBox);
		// 리뷰 다이얼로그에서 확인 버튼 누를 시 busan.review테이블에 리뷰 및 평점 저장

		starComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String countStar = (String) starComboBox.getSelectedItem();
				if (countStar == star[1]) {
					rating = 1.0;
				} else if (countStar == star[2]) {
					rating = 2.0;
				} else if (countStar == star[3]) {
					rating = 3.0;
				} else if (countStar == star[4]) {
					rating = 4.0;
				} else if (countStar == star[5]) {
					rating = 5.0;
				}
				btnOK.setEnabled(true);
			}
		});

		// 리뷰다이얼로그 창에서 확인버튼 누를시 작성한 리뷰와 선택한 평점이 ReviewInput 테이블에 Insert 됨
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int idNum = 0;
				for (int i = 0; i <= searchingList.getLastVisibleIndex(); i++) {
					if (searchingList.getSelectedIndex() == i) {
						String selectedItemStr = searchingList.getSelectedValue();
						Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
						idNum = SEARCHTOOLS.searchIdNum(tempRest.getTitle());
						ReviewRepository.insert(new ReviewInput(box.getText(), rating), idNum);

					}
				}
				JLabel review = new JLabel(ReviewRepository.viewReviewAtBpmId(idNum)
						.get(ReviewRepository.viewReviewAtBpmId(idNum).size() - 1).getReview());
				JLabel rating = new JLabel(String.valueOf(ReviewRepository.viewReviewAtBpmId(idNum)
						.get(ReviewRepository.viewReviewAtBpmId(idNum).size() - 1).getRating()));
				review.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				rating.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				reviewPanel.add(review);
				reviewPanel.add(rating);
				revalidate();
				repaint();

				box.setText("");
			}
		});

		setLayout(new FlowLayout());

		btnCloseDialog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				reviewDialog.this.dispose();
			}
		});
		reviewInputInfo.add(box);
		reviewInputInfo.add(starComboBox);

		bottomPanel.add(btnOK);
		bottomPanel.add(btnCloseDialog);

		dialogPane.add(reviewPanel);
		dialogPane.add(reviewInputInfo);
		dialogPane.add(bottomPanel);

		add(dialogPane);
//		setResizable(false);
//		setSize(400, 400);
		pack();
		setLocation(1085, 200);
	}
}
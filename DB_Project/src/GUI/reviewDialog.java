package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ReviewAndRatings.ReviewDB.ReviewInput;
import ReviewAndRatings.ReviewDB.ReviewRepository;
import SEARCHINFO.SEARCHTOOLS;
import busan.Restaurant;
import listeners.SearchActionListener;

//리뷰 다이얼로그 창
class reviewDialog extends JDialog {
	private JList<String> searchingList;
	double rating;

	public reviewDialog(GUI2 parent) {
		super(parent, "리뷰창", true);
		searchingList = parent.getSearchingList();
		setLayout(new BorderLayout());

		JPanel reviewInputInfo = new JPanel(new GridLayout(1, 2));

		TextField box = new TextField(30);
		JPanel reviewPanel = new JPanel(new GridLayout(0,2));
		
		int idNum;
		for (int i = 0; i < SearchActionListener.getListSize(); i++) {
			if (searchingList.getSelectedIndex() == i) {
				String selectedItemStr = searchingList.getSelectedValue();
				Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
				idNum = SEARCHTOOLS.searchIdNum(tempRest.getTitle());
				ReviewRepository.viewReviewAtBpmId(idNum);
				for (int j = 0; j < ReviewRepository.viewReviewAtBpmId(idNum).size(); j++) {
					JLabel review = new JLabel(ReviewRepository.viewReviewAtBpmId(idNum).get(j).getReview());
					JLabel rating = new JLabel(String.valueOf(ReviewRepository.viewReviewAtBpmId(idNum).get(j).getRating()));
					reviewPanel.add(review);
					reviewPanel.add(rating);
				}
			}
		}
		
		JScrollPane reviewScrollPane = new JScrollPane();
		reviewScrollPane.setBounds(20, 395, 400, 280);
		reviewScrollPane.setViewportView(reviewPanel);
		add(reviewScrollPane);
		
		
		JButton btnOK = new JButton("확인");
		JButton btnCloseDialog = new JButton("닫기");

		String[] star = new String[] { "★", "★★", "★★★", "★★★★", "★★★★★" };
		
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
				if (countStar == star[0]) {
					rating = 1.0;
				} else if (countStar == star[1]) {
					rating = 2.0;
				} else if (countStar == star[2]) {
					rating = 3.0;
				} else if (countStar == star[3]) {
					rating = 4.0;
				} else if (countStar == star[4]) {
					rating = 5.0;
				}

			}
		});
		
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < SearchActionListener.getListSize(); i++) {
					if (searchingList.getSelectedIndex() == i) {
						String selectedItemStr = searchingList.getSelectedValue();
						Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
						int idNum = SEARCHTOOLS.searchIdNum(tempRest.getTitle());
						ReviewRepository.insert(new ReviewInput(box.getText(), rating), idNum);
					}
				}
//				List<ReviewInput> reviewList = new ArrayList<>();
//				ReviewRepository.viewReviewAtBpmId(idNum);
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
		add(reviewPanel);
		add(box);
		add(reviewInputInfo);

		add(btnOK);
		add(btnCloseDialog);


		setSize(400, 400);
		setLocation(1085, 200);
	}
}
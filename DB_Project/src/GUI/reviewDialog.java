package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
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

	public reviewDialog(GUI2 parent) {
		super(parent, "리뷰창", true);
		searchingList = parent.getSearchingList();
		setLayout(new BorderLayout());

		JTextArea text = new JTextArea(12, 21);
		TextField box = new TextField(30);

		Container c = getContentPane();

		c.add(text);
		JButton btnOK = new JButton("확인");
		JButton btnCloseDialog = new JButton("닫기");
		
		// 리뷰 다이얼로그에서 확인 버튼 누를 시 busan.review테이블에 리뷰 및 평점 저장
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.append(box.getText() + "\n");
				System.out.println(searchingList.getSelectedIndex());
				for (int i = 0; i < SearchActionListener.getListSize(); i++) {
					if (searchingList.getSelectedIndex() == i) {
						System.out.println(searchingList.getSelectedIndex());
						String selectedItemStr = searchingList.getSelectedValue();
						Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
						int idNum = SEARCHTOOLS.searchIdNum(tempRest.getTitle());
						ReviewRepository.insert(new ReviewInput("장사 이렇게 하면 안되는데", 5.0), idNum);
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
		add(text);
		add(box);

		add(btnOK);
		add(btnCloseDialog);

		JScrollPane textScrollPane = new JScrollPane();
		textScrollPane.setBounds(20, 395, 400, 280);
		textScrollPane.setViewportView(text);
		add(textScrollPane);

		setSize(300, 300);
		setLocation(1085, 200);
	}
}
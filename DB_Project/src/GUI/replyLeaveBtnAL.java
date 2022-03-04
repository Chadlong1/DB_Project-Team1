package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ReviewAndRatings.ReviewDB.ReviewInput;
import ReviewAndRatings.ReviewDB.ReviewOutput;
import ReviewAndRatings.ReviewDB.ReviewRepository;
import SEARCHINFO.SEARCHTOOLS;
import busan.Restaurant;

public class replyLeaveBtnAL implements ActionListener {
	private ReviewDialog dialog;
	private JList<String> searchingList;
	private JPanel commentScreen;
	private int bundleNum;
	private int bpmIdNum;
	private int count;
	private JTextField replyCommentField;
	

	public replyLeaveBtnAL(ReviewDialog dialog, JList<String> searchingList) {
		super();
		this.dialog = dialog;
		this.searchingList = searchingList;
		this.bundleNum = dialog.getBundleNum();
		this.replyCommentField = dialog.getReplyCommentField();
//		this.count = dialog.getCount();
		this.commentScreen = dialog.getCommentScreen();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		commentScreen.removeAll();
		commentScreen.revalidate();
		commentScreen.repaint();
		int depth = 1;
		int selectedBundleNum = dialog.getSelectedBundleNum();
		for (int i = 0; i <= searchingList.getLastVisibleIndex(); i++) {
			if (searchingList.getSelectedIndex() == i) {
				String selectedItemStr = searchingList.getSelectedValue();
				Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
				bpmIdNum = SEARCHTOOLS.searchIdNum(tempRest.getTitle());
				ReviewRepository.insert(new ReviewInput(replyCommentField.getText(), dialog.getRating(),
						selectedBundleNum, depth, bpmIdNum));
			}
		}
//		List<ReviewOutput> list = new ArrayList<>();
//		list = ReviewRepository.getReplyWithSelectedBundleNum(bpmIdNum, selectedBundleNum);
//		int index = list.size();
//		dialog.leaveReplyComment(list.get(index - 1) , index - 1);
		

		for (int j = 0; j < ReviewRepository.viewReviewAll(bpmIdNum).size(); j++) {
			List<ReviewOutput> list = new ArrayList<>();
			list = ReviewRepository.viewReviewAll(bpmIdNum);
			System.out.println(list.toString());
			dialog.leaveComment(list.get(j), j);
			System.out.println("댓글 등록 후 해당 bpmId의 총 댓글 수 : " + list.size());
		}

	}
}

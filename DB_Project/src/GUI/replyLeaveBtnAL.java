package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import ReviewAndRatings.ReviewDB.ReviewInput;
import ReviewAndRatings.ReviewDB.ReviewRepository;
import SEARCHINFO.SEARCHTOOLS;
import busan.Restaurant;

public class replyLeaveBtnAL implements ActionListener {
	private ReviewDialog dialog;
	private JList<String> searchingList;
	private int bundleNum;
	private int bpmIdNum;

	public replyLeaveBtnAL(ReviewDialog dialog, JList<String> searchingList) {
		super();
		this.dialog = dialog;
		this.searchingList = searchingList;
		this.bundleNum = dialog.getBundleNum();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int depth = 1;
		for (int i = 0; i <= searchingList.getLastVisibleIndex(); i++) {
			if (searchingList.getSelectedIndex() == i) {
				String selectedItemStr = searchingList.getSelectedValue();
				Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
				int bpmIdNum = SEARCHTOOLS.searchIdNum(tempRest.getTitle());
				ReviewRepository.insert(
						new ReviewInput(dialog.getCommentField().getText(), dialog.getRating(), bundleNum + 1, depth, bpmIdNum));

			}
		}
//		dialog.leaveComment(ReviewRepository.viewReviewAtBpmId(bpmIdNum)
//				.get(ReviewRepository.viewReviewAtBpmId(bpmIdNum).size() - 1), numOfReview++);
		dialog.leaveReplyComment(bundleNum);
	}
}

package listeners.ReviewDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import GUI.ReviewDialog;
import ReviewAndRatings.ReviewDB.ReviewInput;
import ReviewAndRatings.ReviewDB.ReviewRepository;
import SEARCHINFO.SEARCHTOOLS;
import busan.Restaurant;

public class leaveCommentAL implements ActionListener {
	private ReviewDialog dialog;
	private JList<String> searchingList;
	private int bpmIdNum;
	private int depth;
	private int numOfReview;

	public leaveCommentAL(ReviewDialog dialog, JList<String> searchingList) {
		super();
		this.dialog = dialog;
		this.searchingList = searchingList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i <= searchingList.getLastVisibleIndex(); i++) {
			if (searchingList.getSelectedIndex() == i) {
				String selectedItemStr = searchingList.getSelectedValue();
				Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
				bpmIdNum = SEARCHTOOLS.searchIdNum(tempRest.getTitle());

				ReviewRepository.insert(new ReviewInput(dialog.getCommentField().getText(), dialog.getRating(), depth, bpmIdNum));

			}
		}
		dialog.leaveComment(ReviewRepository.viewReviewAtBpmId(bpmIdNum)
				.get(ReviewRepository.viewReviewAtBpmId(bpmIdNum).size() - 1), numOfReview++);

	}
}

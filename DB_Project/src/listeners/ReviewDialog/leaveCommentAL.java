package listeners.ReviewDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

import GUI.ReviewDialog;
import ReviewAndRatings.ReviewDB.ReviewInput;
import ReviewAndRatings.ReviewDB.ReviewOutput;
import ReviewAndRatings.ReviewDB.ReviewRepository;
import SEARCHINFO.SEARCHTOOLS;
import busan.Restaurant;

public class leaveCommentAL implements ActionListener {
	private ReviewDialog dialog;
	private JList<String> searchingList;
	private int bpmIdNum;
	private int depth;
	private int count;

	public leaveCommentAL(ReviewDialog dialog, JList<String> searchingList) {
		super();
		this.dialog = dialog;
		this.searchingList = searchingList;
		this.count = dialog.getCount();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int bundleNum = dialog.getBundleNum();
		for (int i = 0; i <= searchingList.getLastVisibleIndex(); i++) {
			if (searchingList.getSelectedIndex() == i) {
				String selectedItemStr = searchingList.getSelectedValue();
				Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
				bpmIdNum = SEARCHTOOLS.searchIdNum(tempRest.getTitle());

				ReviewRepository.insert(new ReviewInput(dialog.getCommentField().getText(), dialog.getRating(), bundleNum + 1, depth, bpmIdNum));
			}
		}
		for (int j = 0; j < ReviewRepository.viewReviewAll(bpmIdNum).size(); j++) {
			List<ReviewOutput> list = new ArrayList<>();
			list = ReviewRepository.viewReviewAll(bpmIdNum);
			System.out.println(list.toString());
			dialog.leaveComment(list.get(j), j);
			System.out.println("댓글 등록 후 해당 bpmId의 총 댓글 수 : " + list.size());
		}
	}
}

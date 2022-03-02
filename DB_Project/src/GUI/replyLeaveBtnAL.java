package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JTextField;

import ReviewAndRatings.ReviewDB.ReviewInput;
import ReviewAndRatings.ReviewDB.ReviewOutput;
import ReviewAndRatings.ReviewDB.ReviewRepository;
import SEARCHINFO.SEARCHTOOLS;
import busan.Restaurant;

public class replyLeaveBtnAL implements ActionListener {
	private ReviewDialog dialog;
	private JList<String> searchingList;
	private int bundleNum;
	private int bpmIdNum;
	private JTextField replyCommentField;

	public replyLeaveBtnAL(ReviewDialog dialog, JList<String> searchingList) {
		super();
		this.dialog = dialog;
		this.searchingList = searchingList;
		this.bundleNum = dialog.getBundleNum();
		this.replyCommentField = dialog.getReplyCommentField();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int depth = 1;
		int selectedBundleNum = dialog.getSelectedBundleNum();
		for (int i = 0; i <= searchingList.getLastVisibleIndex(); i++) {
			if (searchingList.getSelectedIndex() == i) {
				String selectedItemStr = searchingList.getSelectedValue();
				Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
				bpmIdNum = SEARCHTOOLS.searchIdNum(tempRest.getTitle());
				System.out.println("선택된 bundleNum: " + selectedBundleNum);
				ReviewRepository.insert(new ReviewInput(replyCommentField.getText(), dialog.getRating(),
						selectedBundleNum, depth, bpmIdNum));

			}
		}
		List<ReviewOutput> list = new ArrayList<>();
		list = ReviewRepository.getReplyWithSelectedBundleNum(bpmIdNum, selectedBundleNum);
		int count = list.size();
		dialog.leaveReplyComment(list.get(count - 1) , count - 1);

	}
}

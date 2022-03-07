package listeners.ReviewDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import GUI.MainGUI;
import GUI.ReviewDialog;
import ReviewAndRatings.ReviewDB.ReviewInput;
import ReviewAndRatings.ReviewDB.ReviewOutput;
import ReviewAndRatings.ReviewDB.ReviewRepository;
import SEARCHINFO.SEARCHTOOLS;
import busan.Restaurant;

public class leaveCommentAL implements ActionListener {
	private MainGUI parent;
	private ReviewDialog dialog;
	private JList<String> searchingList;
	private int bpmIdNum;
	private int depth;
	private int count;
	private JTextField commentField;
	private JPanel ratingPanel;
	private JPanel ratingPanel_2;
	private JPanel commentLayout;
	private JScrollPane scrollPane;
	private int commentCount;

	public leaveCommentAL(MainGUI parent, ReviewDialog dialog, JList<String> searchingList, int commentCount) {
		super();
		this.parent = parent;
		this.dialog = dialog;
		this.commentCount = commentCount;
		this.searchingList = searchingList;
		this.count = dialog.getCount();
		this.commentField = dialog.getCommentField();
		this.ratingPanel = dialog.getRatingPanel();
		this.ratingPanel_2 = dialog.getRatingPanel2();
		this.commentLayout = dialog.getCommentLayout();
		this.scrollPane = dialog.getScrollPane();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (dialog.getCommentField().getText().length() != 0) {
			ratingPanel.removeAll();
			ratingPanel_2.removeAll();

			scrollPane.revalidate();
			scrollPane.repaint();

			int bundleNum;
			int[] arr = { 0, 0, 0, 0, 0 };
			for (int i = 0; i <= searchingList.getLastVisibleIndex(); i++) {
				if (searchingList.getSelectedIndex() == i) {
					String selectedItemStr = searchingList.getSelectedValue();
					Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
					bpmIdNum = SEARCHTOOLS.searchIdNum(tempRest.getTitle());
					bundleNum = ReviewRepository.viewReviewAtBpmId(bpmIdNum).size();
					ReviewRepository.insert(new ReviewInput(dialog.getCommentField().getText(), dialog.getRating(),
							bundleNum + 1, depth, bpmIdNum));
				}
			}
			for (int j = 0; j < ReviewRepository.viewReviewAll(bpmIdNum).size(); j++) {
				List<ReviewOutput> list = new ArrayList<>();
				list = ReviewRepository.viewReviewAll(bpmIdNum);
				dialog.leaveComment(list.get(j), j);

				if (list.get(j).getDepth() == 0) {
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
			}
			dialog.createRatingPanel1(ReviewRepository.viewRating(bpmIdNum));
			dialog.createRatingPanel2(arr);

		}
		parent.setRating((String) String.valueOf(ReviewRepository.viewRating(bpmIdNum)));
		
		commentField.setText("");
		commentField.addMouseListener(new CommentFieldML(commentField));
		commentField.addKeyListener(new CommentFieldKL(dialog, commentField));
		dialog.increaseCommentCount();

//		commentLayout.revalidate();
//		commentLayout.repaint();
	}
}

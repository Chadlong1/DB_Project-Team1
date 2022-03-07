package listeners.ReviewDialog;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import GUI.ReviewDialog;

public class ReplyCommentFieldKL extends KeyAdapter {
	private ReviewDialog dialog;
	private JTextField replyCommentField;
	private static String basicReplyComment = "대댓글을 작성해주세요";

	public ReplyCommentFieldKL(ReviewDialog dialog, JTextField replyCommentField) {
		super();
		this.dialog = dialog;
		this.replyCommentField = replyCommentField;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		super.keyTyped(e);
		if (replyCommentField.getText().equals(basicReplyComment)) {
			replyCommentField.setText("");
			replyCommentField.setForeground(Color.BLACK);
		}

		if (replyCommentField.getText().isEmpty()) {
			dialog.replyLeaveBtnDisable();
		} else {
			dialog.replyLeaveBtnEnable();
		}

	}
}

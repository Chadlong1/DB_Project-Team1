package listeners.ReviewDialog;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import GUI.ReviewDialog;

public class ReplyCommentFieldML extends MouseAdapter {
	private static String basicReplyComment = "대댓글을 작성해주세요";
	private JTextField replyCommentField;

	public ReplyCommentFieldML(JTextField replyCommentField) {
		super();
		this.replyCommentField = replyCommentField;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if (replyCommentField.getText().equals(basicReplyComment)) {
			replyCommentField.setText("");
			replyCommentField.setForeground(Color.BLACK);
		}

	}
}

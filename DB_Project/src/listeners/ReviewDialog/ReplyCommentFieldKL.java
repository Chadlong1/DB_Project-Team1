package listeners.ReviewDialog;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class ReplyCommentFieldKL extends KeyAdapter {
	private static String basicReplyComment = "대댓글을 작성해주세요";
	private JTextField replyCommentField;

	public ReplyCommentFieldKL(JTextField replyCommentField) {
		super();
		this.replyCommentField = replyCommentField;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		super.keyTyped(e);
		if (replyCommentField.getText().equals(basicReplyComment)) {
			replyCommentField.setText("");
			replyCommentField.setForeground(Color.BLACK);
		}
	}
}

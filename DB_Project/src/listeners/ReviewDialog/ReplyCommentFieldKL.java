package listeners.ReviewDialog;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;

import javax.swing.JTextField;

import GUI.ReviewDialog;

public class ReplyCommentFieldKL extends KeyAdapter {
	private ReviewDialog dialog;
	private JTextField replyCommentField;
	private byte[] strTemps;
	private static String basicReplyComment = "대댓글을 작성해주세요";
	private static String charset = "euc-kr";

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
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (replyCommentField.getText().equals(basicReplyComment)) {
			replyCommentField.setText("");
			replyCommentField.setForeground(Color.BLACK);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		String countStar = dialog.selectedRating();
		if (replyCommentField.getText().isEmpty()) {
			dialog.leaveBtnDisable();
		} else {
			dialog.leaveBtnEnable();
		}
		
		do { // 코멘트 바이트가 EUC_KR 인코딩 기준으로 75바이트가 초과되지않게
			try {
				strTemps = replyCommentField.getText().getBytes(charset);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			if (strTemps.length > 75) {
				String str = replyCommentField.getText();
				String result = str.substring(0, str.length() - 1);
				replyCommentField.setText(result);
			}

		} while (strTemps.length > 75);
	}

	
}

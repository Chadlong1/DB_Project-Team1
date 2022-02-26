package listeners.ReviewDialog;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;

import javax.swing.JTextField;

import GUI.ReviewDialog;

public class commentFieldKL extends KeyAdapter {
	private ReviewDialog dialog;
	private JTextField commentField;
	private byte[] strTemps;
	private String[] stars;
	private static String basicComment = "후기를 입력해주세요";
	private static String charset = "euc-kr";

	public commentFieldKL(ReviewDialog dialog, JTextField commentField) {
		super();
		this.dialog = dialog;
		this.stars = dialog.getStars();
		this.commentField = commentField;

	}

	@Override
	public void keyTyped(KeyEvent e) {
		String countStar = dialog.selectedRating();
		System.out.println(countStar);
		if (commentField.getText().equals("") || countStar.equals(stars[0])) {
			dialog.leaveBtnDisable();
		} else {
			dialog.leaveBtnEnable();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (commentField.getText().equals(basicComment)) {
			commentField.setText("");
			commentField.setForeground(Color.BLACK);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		String countStar = dialog.selectedRating();
		if (commentField.getText().equals("") || countStar.equals(stars[0])) {
			dialog.leaveBtnDisable();
		} else {
			dialog.leaveBtnEnable();
		}

		do { // 코멘트 바이트가 EUC_KR 인코딩 기준으로 75바이트가 초과되지않게
			try {
				strTemps = commentField.getText().getBytes(charset);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			if (strTemps.length > 75) {
				String str = commentField.getText();
				String result = str.substring(0, str.length() - 1);
				commentField.setText(result);
			}

		} while (strTemps.length > 75);
	}

}

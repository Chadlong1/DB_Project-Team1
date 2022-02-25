package listeners.ReviewDialog;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

public class CommentFieldML extends MouseAdapter {
	private JTextField commentField;
	private static String basicComment = "후기를 입력해주세요";

	public CommentFieldML(JTextField commentField) {
		super();
		this.commentField = commentField;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if (commentField.getText().equals(basicComment)) {
			commentField.setText("");
			commentField.setForeground(Color.BLACK);
		}

	}
}

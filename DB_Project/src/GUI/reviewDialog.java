package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ReviewAndRatings.ReviewDB.ReviewInput;
import ReviewAndRatings.ReviewDB.ReviewRepository;
import SEARCHINFO.SEARCHTOOLS;

//리뷰 다이얼로그 창
class reviewDialog extends JDialog {
	public reviewDialog(JFrame parent) {
		super(parent, "리뷰창", true);

		setLayout(new BorderLayout());

		JTextArea text = new JTextArea(12, 21);
		TextField box = new TextField(30);

		Container c = getContentPane();

		c.add(text);
		JButton btnOK = new JButton("확인");
		JButton btnCloseDialog = new JButton("닫기");

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.append(box.getText() + "\n");
				int idNum = SEARCHTOOLS.searchIdNum("민물가든");
				ReviewRepository.insert(new ReviewInput("맛있다",5.0), idNum);
				box.setText("");
				
			}
		});
		setLayout(new FlowLayout());

		btnCloseDialog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reviewDialog.this.dispose();
			}
		});
		add(text);
		add(box);

		add(btnOK);
		add(btnCloseDialog);

		JScrollPane textScrollPane = new JScrollPane();
		textScrollPane.setBounds(20, 395, 400, 280);
		textScrollPane.setViewportView(text);
		add(textScrollPane);

		setSize(300, 300);
		setLocation(1085, 200);
	}
}
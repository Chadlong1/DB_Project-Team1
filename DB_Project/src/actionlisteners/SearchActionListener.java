package actionlisteners;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GUI2;

public class SearchActionListener implements ActionListener {
	private GUI2 frame;

	public SearchActionListener(GUI2 frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Container c = frame.getContentPane();
		CardLayout card = frame.getCard();
		card.next(c);

	}
}

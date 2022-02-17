package listeners;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GUI2;

public class GoBackActionListener implements ActionListener {
	private GUI2 frame;

	public GoBackActionListener(GUI2 frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.setRestTitle("목록에서 식당을 선택하세요");
		frame.setRestRprsntvMenu("");
		frame.setRestADDR("");
		frame.setRestCntctTEL("");
		frame.setRestUsageTime("");
		frame.setRestItemCntnts2("");
		frame.setThumbLEmpty();
		frame.setStaitcMapEmpty();

		Container c = frame.getContentPane();
		CardLayout card = frame.getCard();
		card.show(c, "FirstScreen");
	}

}

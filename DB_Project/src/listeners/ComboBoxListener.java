package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GUI.GUI2;

public class ComboBoxListener implements ActionListener {

	private GUI2 frame;
	private JLabel pin;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String loca = frame.selectedItemFromZone();
		System.out.println(loca.length());
		String[] zone = { "부산 전체", "부산진구", "사상구", "북구", "남구", "서구", "중구", "동구", "강서구", "수영구", "동래구", "연제구", "해운대구",
				"영도구", "금정구", "사하구" };
		ImageIcon pinIcon = new ImageIcon("pin.png");
		
		for (int i = 0; i < 16; i++) {
			if (loca == zone[i]) {
				pin = new JLabel(pinIcon);
			}
		}
	}

}

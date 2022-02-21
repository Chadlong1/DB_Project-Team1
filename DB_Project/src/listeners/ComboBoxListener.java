package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.GUI2;
import GUI.MapPin;

public class ComboBoxListener implements ActionListener {

	private GUI2 frame;
	private ImageIcon pinIcon = new ImageIcon("pin.png");
	private JLabel pin = new JLabel(pinIcon);
	
	
	public ComboBoxListener(GUI2 frame) {
		super();
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel framePanel = frame.getFirstPanel();
		String loca = frame.selectedItemFromZone();
		String[] zone = frame.getZones();
		framePanel.remove(pin);

		List<MapPin> localist = new ArrayList<>();
		localist.add(new MapPin(0, 0));
		localist.add(new MapPin(570, 320)); // 부산진구
		localist.add(new MapPin(500, 320)); // 사상구
		localist.add(new MapPin(550, 220)); // 북구
		localist.add(new MapPin(640, 380)); // 남구
		localist.add(new MapPin(525, 400)); // 서구
		localist.add(new MapPin(550, 440)); // 중구
		localist.add(new MapPin(570, 380)); // 동구
		localist.add(new MapPin(325, 380)); // 강서구
		localist.add(new MapPin(660, 350)); // 수영구
		localist.add(new MapPin(600, 260)); // 동래구
		localist.add(new MapPin(630, 300)); // 연제구
		localist.add(new MapPin(720, 280)); // 해운대구
		localist.add(new MapPin(600, 460)); // 영도구
		localist.add(new MapPin(630, 160)); // 금정구
		localist.add(new MapPin(480, 440)); // 사하구
		localist.add(new MapPin(770, 100)); // 기장군

	
		
		for (int i = 1; i < localist.size(); i++) {
			if (loca == zone[i]) {
				pin.setBounds(localist.get(i).getX(), localist.get(i).getY(), 100, 100);

				framePanel.add(pin);
				framePanel.revalidate();
				framePanel.repaint();

			}
		}
		
		frame.setFirstPanel(framePanel);
	}
}

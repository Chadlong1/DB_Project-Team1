package listeners;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import GUI.GUI2;

public class MapZoneSelectionMouseListener extends MouseAdapter {

	private GUI2 frame;
	private JPanel[] zonePanArr;

	public MapZoneSelectionMouseListener(GUI2 Frame) {
		super();
		this.frame = Frame;
		this.zonePanArr = frame.getZonePanArr();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o == zonePanArr[0]) {
			frame.setZoneComboBox("사하구");
		} else if (o == zonePanArr[1]) {
			frame.setZoneComboBox("금정구");
		} else if (o == zonePanArr[2]) {
			frame.setZoneComboBox("해운대구");
		} else if (o == zonePanArr[3]) {
			frame.setZoneComboBox("동래구");
		} else if (o == zonePanArr[4]) {
			frame.setZoneComboBox("연제구");
		} else if (o == zonePanArr[5]) {
			frame.setZoneComboBox("수영구");
		} else if (o == zonePanArr[6]) {
			frame.setZoneComboBox("남구");
		} else if (o == zonePanArr[7]) {
			frame.setZoneComboBox("부산진구");
		} else if (o == zonePanArr[8]) {
			frame.setZoneComboBox("북구");
		} else if (o == zonePanArr[9]) {
			frame.setZoneComboBox("사상구");
		} else if (o == zonePanArr[10]) {
			frame.setZoneComboBox("동구");
		} else if (o == zonePanArr[11]) {
			frame.setZoneComboBox("서구");
		} else if (o == zonePanArr[12]) {
			frame.setZoneComboBox("중구");
		} else if (o == zonePanArr[13]) {
			frame.setZoneComboBox("영도구");
		} else if (o == zonePanArr[14]) {
			frame.setZoneComboBox("강서구");
		} else if (o == zonePanArr[15]) {
			frame.setZoneComboBox("강서구");
		} else if (o == zonePanArr[16]) {
			frame.setZoneComboBox("기장군");
		}
	}
}

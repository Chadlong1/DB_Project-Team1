package listeners;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import GUI.GUI2;

public class MapZoneSelectionMouseListener extends MouseAdapter {

	private GUI2 frame;
	private JPanel firstPanel;
	public MapZoneSelectionMouseListener(GUI2 Frame) {
		super();
		this.frame = Frame;
		this.firstPanel = frame.getFirstPanel();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if (505 <= e.getX() && e.getX() <= 550) {
			if (505 <= e.getY() && e.getY() <= 550) {
				frame.setZoneComboBox("사하구");
			}
		}
		if (640 <= e.getX() && e.getX() <= 720) {
			if (220 <= e.getY() && e.getY() <= 260) {
				frame.setZoneComboBox("금정구");
			}
		}
		if (730 <= e.getX() && e.getX() <= 800) {
			if (335 <= e.getY() && e.getY() <= 370) {
				frame.setZoneComboBox("해운대구");
			}
		}
		if (635 <= e.getX() && e.getX() <= 700) {
			if (315 <= e.getY() && e.getY() <= 340) {
				frame.setZoneComboBox("동래구");
			}
		}
		if (650 <= e.getX() && e.getX() <= 700) {
			if (350 <= e.getY() && e.getY() <= 375) {
				frame.setZoneComboBox("연제구");
			}
		}
		if (695 <= e.getX() && e.getX() <= 730) {
			if (385 <= e.getY() && e.getY() <= 415) {
				frame.setZoneComboBox("수영구");
			}
		}
		if (665 <= e.getX() && e.getX() <= 700) {
			if (440 <= e.getY() && e.getY() <= 470) {
				frame.setZoneComboBox("남구");
			}
		}
		if (600 <= e.getX() && e.getX() <= 655) {
			if (385 <= e.getY() && e.getY() <= 415) {
				frame.setZoneComboBox("부산진구");
			}
		}
		if (575 <= e.getX() && e.getX() <= 625) {
			if (280 <= e.getY() && e.getY() <= 315) {
				frame.setZoneComboBox("북구");
			}
		}
		if (515 <= e.getX() && e.getX() <= 570) {
			if (380 <= e.getY() && e.getY() <= 415) {
				frame.setZoneComboBox("사상구");
			}
		}
		if (605 <= e.getX() && e.getX() <= 640) {
			if (445 <= e.getY() && e.getY() <= 470) {
				frame.setZoneComboBox("동구");
			}
		}
		if (560 <= e.getX() && e.getX() <= 590) {
			if (460 <= e.getY() && e.getY() <= 480) {
				frame.setZoneComboBox("서구");
			}
		}
		if (600 <= e.getX() && e.getX() <= 620) {
			if (484 <= e.getY() && e.getY() <= 506) {
				frame.setZoneComboBox("중구");
			}
		}
		if (620 <= e.getX() && e.getX() <= 680) {
			if (520 <= e.getY() && e.getY() <= 550) {
				frame.setZoneComboBox("영도구");
			}
		}
		if (340 <= e.getX() && e.getX() <= 420) {
			if (450 <= e.getY() && e.getY() <= 480) {
				frame.setZoneComboBox("강서구");
			}
		}
		if (430 <= e.getX() && e.getX() <= 530) {
			if (300 <= e.getY() && e.getY() <= 380) {
				frame.setZoneComboBox("강서구");
			}
		}
		if (800 <= e.getX() && e.getX() <= 880) {
			if (150 <= e.getY() && e.getY() <= 200) {
				frame.setZoneComboBox("기장군");
			}
		}

	}
	
	public void MouseMoved(MouseEvent e) {
		frame.setLocation(e.getX(), e.getY());
		if (505 <= e.getX() && e.getX() <= 550) {
			if (505 <= e.getY() && e.getY() <= 550) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
				System.out.println("enter in");
			}
		}
		if (640 <= e.getX() && e.getX() <= 720) {
			if (220 <= e.getY() && e.getY() <= 260) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
				System.out.println("enter in");
			}
		}
		if (730 <= e.getX() && e.getX() <= 800) {
			if (335 <= e.getY() && e.getY() <= 370) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
				System.out.println("enter in");
			}
		}
		if (635 <= e.getX() && e.getX() <= 700) {
			if (315 <= e.getY() && e.getY() <= 340) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
				System.out.println("enter in");
			}
		}
		if (650 <= e.getX() && e.getX() <= 700) {
			if (350 <= e.getY() && e.getY() <= 375) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
				System.out.println("enter in");
			}
		}
		if (695 <= e.getX() && e.getX() <= 730) {
			if (385 <= e.getY() && e.getY() <= 415) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		if (665 <= e.getX() && e.getX() <= 700) {
			if (440 <= e.getY() && e.getY() <= 470) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		if (600 <= e.getX() && e.getX() <= 655) {
			if (385 <= e.getY() && e.getY() <= 415) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		if (575 <= e.getX() && e.getX() <= 625) {
			if (280 <= e.getY() && e.getY() <= 315) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		if (515 <= e.getX() && e.getX() <= 570) {
			if (380 <= e.getY() && e.getY() <= 415) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		if (605 <= e.getX() && e.getX() <= 640) {
			if (445 <= e.getY() && e.getY() <= 470) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		if (560 <= e.getX() && e.getX() <= 590) {
			if (460 <= e.getY() && e.getY() <= 480) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		if (600 <= e.getX() && e.getX() <= 620) {
			if (484 <= e.getY() && e.getY() <= 506) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		if (620 <= e.getX() && e.getX() <= 680) {
			if (520 <= e.getY() && e.getY() <= 550) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		if (340 <= e.getX() && e.getX() <= 420) {
			if (450 <= e.getY() && e.getY() <= 480) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
	}
	
	
}
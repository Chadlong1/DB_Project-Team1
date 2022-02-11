package listeners;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import GUI.GUI2;
import SEARCHINFO.SEARCHTOOLS;
import busan.Restaurant;

public class SecondPanelListListener implements ListSelectionListener {
	private GUI2 frame;
	private JList<String> searchingList;

	public SecondPanelListListener(GUI2 frame) {
		super();
		this.frame = frame;
		this.searchingList = frame.getSearchingList();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		for (int i = 0; i < SearchActionListener.getListSize(); i++) {
			if (searchingList.getSelectedIndex() == i) {
				String selectedItemStr = searchingList.getSelectedValue();
				Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
				frame.setRestTitle(tempRest.getTitle());
				frame.setRestRprsntvMenu(tempRest.getMenu());
				frame.setRestADDR(tempRest.getAddr());
				frame.setRestCntctTEL(tempRest.getTel());
				frame.setRestUsageTime(tempRest.getTime());
				frame.setRestItemCntnts(tempRest.getComment());
				frame.setThumbL(tempRest.getThumb());
			}
		}
//		System.out.println(searchingList.getMaxSelectionIndex());

	}

}

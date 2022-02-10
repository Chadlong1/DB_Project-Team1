package listeners;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import GUI.GUI2;
import SEARCHINFO.SEARCHTOOLS;

public class SearchActionListener implements ActionListener {
	private GUI2 frame;
	private JList<String> searchingList;
	

	public SearchActionListener(GUI2 frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String loca = frame.selectedItemFromZone();
		String type = frame.selectedItemFromFood();
		List<String> list = SEARCHTOOLS.searchDB(loca, type);
		int size = list.size();
		String[] arr = list.toArray(new String[size]);
		
		searchingList = frame.getSearchingList();					
		searchingList.setModel(new AbstractListModel<String>() {
			String[] values = arr;
			
			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});
		frame.setSearchingList(searchingList);
		
		Container c = frame.getContentPane();
		CardLayout card = frame.getCard();
		card.show(c, "SecondScreen");

	}
}

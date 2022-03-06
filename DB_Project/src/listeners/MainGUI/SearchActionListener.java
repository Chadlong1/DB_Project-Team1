package listeners.MainGUI;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import GUI.MainGUI;
import SEARCHINFO.SEARCHTOOLS;

public class SearchActionListener implements ActionListener {
	private MainGUI frame;
	private JList<String> searchingList;
	
	public SearchActionListener(MainGUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String loca = frame.selectedItemFromZone();
		String type = frame.selectedItemFromFood();
		String rating = frame.selectedItemFromRating();
		List<String> list = SEARCHTOOLS.searchDB(loca, type, rating);
		String[] arr = list.toArray(new String[list.size()]);
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

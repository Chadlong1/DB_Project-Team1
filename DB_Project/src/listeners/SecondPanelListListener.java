package listeners;

import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import GUI.GUI2;
import ReviewAndRatings.ReviewDB.ReviewRepository;
import SEARCHINFO.SEARCHTOOLS;
import busan.Restaurant;
import maps.Geocoding;
import maps.GoogleStaticMaps;

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
		for (int i = 0; i <= searchingList.getLastVisibleIndex(); i++) {
			if (!e.getValueIsAdjusting()) {
				if (searchingList.getSelectedIndex() == i) {
					
					String selectedItemStr = searchingList.getSelectedValue();
					Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
					ArrayList<String> geoList = Geocoding.getGeocoding(tempRest.getAddr());

					frame.setRestTitle(tempRest.getTitle());
					frame.setRestRprsntvMenu(tempRest.getMenu());
					frame.setRestADDR(tempRest.getAddr());
					frame.setRestCntctTEL(tempRest.getTel());
					frame.setRestUsageTime(tempRest.getTime());
					frame.setRestItemCntnts2(tempRest.getComment());
					frame.setThumbL(tempRest.getThumb());
					frame.setRating(String.valueOf(ReviewRepository.viewRatig(i + 1)));
					frame.setStaitcMap(GoogleStaticMaps.getStaticMapURL(geoList.get(0), geoList.get(1)));
				}
			}
//		System.out.println(searchingList.getMaxSelectionIndex());
		}
	}

}

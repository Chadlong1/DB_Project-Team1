package listeners.MainGUI;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import GUI.MainGUI;
import ReviewAndRatings.ReviewDB.ReviewRepository;
import SEARCHINFO.SEARCHTOOLS;
import busan.Restaurant;
import maps.Geocoding;
import maps.GoogleStaticMaps;

public class SecondPanelListListener implements ListSelectionListener {
	private JButton btnReviewDialogPopUp;
	private MainGUI frame;
	private JList<String> searchingList;

	public SecondPanelListListener(MainGUI frame, JButton btnReviewDialogPopUp) {
		super();
		this.frame = frame;
		this.searchingList = frame.getSearchingList();
		this.btnReviewDialogPopUp = frame.btnReviewDialogPopUp;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		for (int i = 0; i <= searchingList.getLastVisibleIndex(); i++) {
			if (!e.getValueIsAdjusting()) {
				if (searchingList.getSelectedIndex() == i) {
					ImageIcon loading = new ImageIcon("ajax-loading.gif");
					frame.setLoadingLabel(loading);
					Thread t = new Thread(new Runnable() {
						@Override
						public void run() {
							// 시간이 오래 걸리는 작업 추가 << 이 작업은 스윙이 맡으면 화면이 멈춤.
							// t라는 쓰레드가 이 작업을 할 거임.
							String selectedItemStr = searchingList.getSelectedValue();
							Restaurant tempRest = SEARCHTOOLS.searchRestaurant(selectedItemStr);
							Integer searchIdNum = SEARCHTOOLS.searchIdNum(tempRest.getTitle());
							ArrayList<String> geoList = Geocoding.getGeocoding(tempRest.getAddr());

							frame.setRestTitle(tempRest.getTitle());
							frame.setRestRprsntvMenu(tempRest.getMenu());
							frame.setRestADDR(tempRest.getAddr());
							frame.setRestCntctTEL(tempRest.getTel());
							frame.setRestUsageTime(tempRest.getTime());
							frame.setRestItemCntnts2(tempRest.getComment());
							frame.setThumbL(tempRest.getThumb());
							if (ReviewRepository.viewRating(searchIdNum) == 0) {
								frame.setRating("");
							} else {
								frame.setRating((String) String.valueOf(ReviewRepository.viewRating(searchIdNum)));
							}
							btnReviewDialogPopUp.setVisible(true);
							frame.setStaitcMap(GoogleStaticMaps.getStaticMapURL(geoList.get(0), geoList.get(1)));
							// 작업이 끝나면 스윙보고 끝났다고 보고하면 됨.
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									ImageIcon empty = new ImageIcon("");
									frame.setLoadingLabel(empty);
								}
							});
						}
					});
					t.start(); // 쓰레드 t보고 일 시작하라고 명령
					frame.setSelectBpmId(searchingList.getSelectedIndex() + 1);
				}
			}
		}
	}
}

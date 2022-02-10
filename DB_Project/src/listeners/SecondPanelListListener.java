//package listeners;
//
//import java.io.IOException;
//import java.net.URL;
//
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//
//import GUI.GUI2;
//import teacher.MyFrame;
//
//public class SecondPanelListListener implements ListSelectionListener{
//	private MyFrame frame;
//	
//	public SecondPanelListListener(GUI2 frame) {
//		super();
//		this.frame = frame;
//	}
//	
//		@Override
//		public void valueChanged(ListSelectionEvent e) {
//			if (searchingList.getSelectedIndex() == 0) {
//				restTitle.setText("만호갈미 샤브샤브");
//				rprsntvMenu = "만호갈미 샤브샤브";
//				restRprsntvMenu.setText("• 주요 메뉴 : " + rprsntvMenu);
//				aDDR = "강서구 르노삼성대로 602";
//				restADDR.setText("• 주소지 : " + aDDR);
//				cntctTEL = "051-271-4389";
//				restCntctTEL.setText("• 영업시간 : " + usageTime);
//				usageTime = "12:00p.m. ~ 21:30p.m.";
//				restUsageTime.setText("• 연락처 : " + cntctTEL);
//				itemCntnts = "샤브샤브, 수육, 구이 등 다양한 방식으로 갈미조개를 요리하는 갈미조개 전문 식당. 국물이 맛있기로 유명한 이 곳은 갈미샤브샤브와 갈미수육이 대표메뉴이다.";
//				restItemCntnts.setText("• 소개 : " + itemCntnts);
//				try {
//					url = new URL("https://www.visitbusan.net/uploadImgs/files/cntnts/20191216135832825_thumbL");
//					image = ImageIO.read(url);
//					thumbL.setIcon(new ImageIcon(image));
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//
//			} else if (searchingList.getSelectedIndex() == 1) {
//				restTitle.setText("민물가든");
//				rprsntvMenu = "묵은지붕어조림, 붕어찜";
//				restRprsntvMenu.setText("• 주요 메뉴 : " + rprsntvMenu);
//				aDDR = "강서구 둔치중앙길5(봉림동)";
//				restADDR.setText("• 주소지 : " + aDDR);
//				cntctTEL = "051-971-8428";
//				restCntctTEL.setText("• 영업시간 : " + usageTime);
//				usageTime = "11:00p.m. ~ 21:00p.m.";
//				restUsageTime.setText("• 연락처 : " + cntctTEL);
//				itemCntnts = "30년간 운영해온 생선찜전문점으로, 전통방식인 나무통을 사용하여 조리하는 것이 특징이다. 20가지 이상의 재료로 만든 양념을 사용하는 이 곳은 묵은지 붕어조림과 붕어찜이 대표메뉴이다.";
//				restItemCntnts.setText("• 소개 : " + itemCntnts);
//				try {
//					url = new URL("https://www.visitbusan.net/uploadImgs/files/cntnts/20191217101816206_thumbL");
//					image = ImageIO.read(url);
//					thumbL.setIcon(new ImageIcon(image));
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//
//		}
//
//	
//	}
//}

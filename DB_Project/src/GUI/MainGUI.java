package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

   class Frame1 extends JFrame {
      Frame1() {
    	  setTitle("돼동여지도");
    	  
    	  
    	  Container con = getContentPane();
    	  con.setLayout(new BorderLayout());
    	  con.add(new JPanel(), BorderLayout.CENTER);
    	  con.add(new JPanel(), BorderLayout.NORTH);
    	  con.add(new JPanel(), BorderLayout.SOUTH);
    	  con.add(new JPanel(), BorderLayout.EAST);
    	  con.add(new JPanel(), BorderLayout.WEST);
    	  
    	  con.setLayout(new FlowLayout(0,0,50));
    	  JLabel north = new JLabel("돼동여지도");
    	  con.add(north,"돼동여지도");
    	  con.setForeground(Color.blue);
    	  con.setFont(new Font("Consolas", Font.BOLD,100));
    	  
    	  JLabel north2 = new JLabel("ㅁㅇㄹ");
    	  con.add(north2,"adf");
    	  String[] zone = {"부산 전체", "부산진구", "사상구", "북구", "남구", "서구", "중구", "동구",
    			  "강서구", "수영구", "동래구", "연제구", "해운대구", "영도구",
    			  "금정구", "사하구"};
    	  
    	  String[] food = {"음식종류", "한식", "중식", "양식", "일식", "분식", "패스트푸드"};
    	  
    	  
    	  String[] time = {"영업시간", "am 10 ~", "am 11 ~", "pm 12~",
    			  "pm 1 ~", "pm 2 ~", "pm 3 ~", "pm 4 ~"};
    	  
    	  
    	  JComboBox<String> cb;
    	  JComboBox<String> cb1;
    	  JComboBox<String> cb2;
    	  
//    	  ComboBoxEx(){
//    		  
//    	  }
    	  
    	  
    	  Toolkit toolkit = Toolkit.getDefaultToolkit();
    	  Image busanImage = toolkit.getImage("부산.png");
    	  Image scaled = busanImage.getScaledInstance(800,500,Image.SCALE_DEFAULT);
    	  
    	  JLabel busanImage1 = new JLabel(new ImageIcon(scaled));
    	  add(busanImage1, "Center");
    	  //add(map, "NORTH");
    	  
         setSize(800, 800);

         setVisible(true);

         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         
         
        		 
        		 
         }

	private void ComboBoxEx() {
		// TODO Auto-generated method stub
		
	}

         
      }
      

   
public class MainGUI {
   public static void main (String[] args) {
     Frame1 frame = new Frame1(); 
        
     }
}
	

		

	



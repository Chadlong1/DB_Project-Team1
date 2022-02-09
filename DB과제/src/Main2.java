import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

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
         
     
         JPanel pnlNorth = new JPanel();
         JLabel north = new JLabel("");
         
         //con.add(north,"돼동여지도");
         //con.setForeground(Color.blue);

         
         JLabel north2 = new JLabel("돼동여지도");
         add(north2,"돼동여지도");
         
         north2.setFont(new Font("궁서체", Font.PLAIN, 50));
         pnlNorth.setBorder(new LineBorder(Color.BLACK));
         pnlNorth.add(north);
        
         String[] zone = {" 부산 전체 ", " 부산진구 ", " 사상구 ", " 북구 ", " 남구 ", " 서구 ", " 중구 ", " 동구 ",
               " 강서구 ", " 수영구 ", " 동래구 ", " 연제구 ", " 해운대구 ", " 영도구 ",
               " 금정구 ", " 사하구 "};
         
         String[] food = {" 음식종류 ", " 한식 ", " 중식 ", " 양식 ", " 일식 ", " 분식 ", " 패스트푸드 "};
         
         
         String[] time = {" 영업시간 ", " am 10 ~ ", " am 11 ~ ", " pm 12~ ",
               " pm 1 ~ ", " pm 2 ~ ", " pm 3 ~ ", " pm 4 ~ "};
         
         
         JComboBox<String> cb;
         JComboBox<String> cb1;
         JComboBox<String> cb2;
         
         JComboBox strCombo = new JComboBox(zone);
         JComboBox nameCombo = new JComboBox(food);
         JComboBox timeCombo = new JComboBox(time);
         
         JButton b = new JButton("검색");
         b.setBounds(50,100,90,20);
         
         cb = new JComboBox<String>(zone);
         cb.setBounds(50,100,90,20);
         
         cb1 = new JComboBox<String>(food);
         cb1.setBounds(50,100,90,20);
         
         cb2 = new JComboBox<String>(time);
         cb2.setBounds(50,100,90,20);
         
         con.add(cb);
         con.add(cb1);
         con.add(cb2);
         
         con.add(b);
         con.add(north,b);
         
         b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String data = " 선택 : " + cb.getItemAt(cb.getSelectedIndex()) +
                     cb1.getItemAt(cb1.getSelectedIndex()) +
                     cb2.getItemAt(cb2.getSelectedIndex());
               north.setText(data);
                            
         }
      });
         
         
         Toolkit toolkit = Toolkit.getDefaultToolkit();
         Image busanImage = toolkit.getImage("지도.png");
         Image scaled = busanImage.getScaledInstance(800, 500,Image.SCALE_DEFAULT);
         
         JLabel busanImage1 = new JLabel(new ImageIcon(scaled));
         add(busanImage1, "Center");
         
         setSize(800, 800);

         setVisible(true);

         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
         }

   private void ComboBoxEx() {
   }
   }

   
public class Main2 {
   public static void main (String[] args) {
     Frame1 frame = new Frame1(); 
        
     }
}
   

      

   

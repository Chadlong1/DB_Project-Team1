import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class ComboBoxEx extends JFrame{
	
    String[] zone = {" 부산 전체 " ," 부산진구 ", " 사상구 ", " 북구 ", " 남구 ", " 서구 ", " 중구 ", " 동구 " ,
    					" 강서구 ", " 수영구 ", " 동래구 ", " 연제구 ", " 해운대구 ", " 영도구 ", " 금정구 ", " 사하구 "};
    				
   
    String[] food = {" 음식종류 ", " 한식 ", " 중식 ", " 양식 ", " 일식 ", " 분식 ", " 패스트푸드 " };
    
    String[] time = {" 영업시간 " , " am 10 ~ ", " am 11 ~ ", " pm 12 ~ ", " pm 1 ~ ", " pm 2 ~ ", " pm 3 ~ ", " pm 4 ~ "};
    
    JComboBox<String> cb;
    
    JComboBox<String> cb1;
    
    JComboBox<String> cb2;
    
    ComboBoxEx(){
    	
    	JLabel label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setSize(400, 100);
		
        this.setTitle("콤보박스 만들기 예제");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        
        JComboBox strCombo= new JComboBox(zone);
    
        JComboBox nameCombo = new JComboBox(food);
        
        JComboBox timeCombo = new JComboBox(time);
        
        JButton b = new JButton("검색");
		b.setBounds(200, 100, 75, 20);
		
		cb = new JComboBox<String>(zone);
		cb.setBounds(50, 100, 90, 20);
		
		cb1 = new JComboBox<String>(food);
		cb1.setBounds(50, 100, 90, 20);

		cb2 = new JComboBox<String>(time);
		cb2.setBounds(50, 100, 90, 20);
		
		add(cb);
		add(cb1);
		add(cb2);
		
		add(b);
		add(label);
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = " 선택 : " + cb.getItemAt(cb.getSelectedIndex())  + 
						cb1.getItemAt(cb1.getSelectedIndex()) + cb2.getItemAt(cb2.getSelectedIndex());
				label.setText(data);
			}
		});
	
        this.setLocationRelativeTo(null);
        this.setSize(500,500);
        this.setVisible(true);
    }
}
public class Main {
    public static void main(String[] args) {
        new ComboBoxEx();
    }
}


import javax.swing.*;
import java.awt.event.*;

public class JComboBoxTest extends JFrame {
	String languages[] = { "부산진구", "북구", "사하구", "사상구", "금정구", "강서구",
			             "금정구", "남구", "동구", "중구", "서구","해운대구", "수영구"  };
	
	
	JComboBox<String> cb;
	
	

	JComboBoxTest() {
		super("테스트");

		JLabel label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setSize(400, 100);


		JButton b = new JButton("결과");
		b.setBounds(200, 100, 75, 20);
		

		cb = new JComboBox<String>(languages);
		cb.setBounds(50, 100, 90, 20);
		
		add(cb);
	
		add(label);
		
		add(b);
		setLayout(null);
		setSize(350, 350);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = " 선택 지역:" + cb.getItemAt(cb.getSelectedIndex());
				label.setText(data);
			}
		});
	}
	


	public static void main(String[] args) {
		new JComboBoxTest();
	}
}

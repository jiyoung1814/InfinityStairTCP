package Client;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class RankingFrame extends JPanel{
	JScrollPane scr;
	JList list;
	DefaultListModel model;
	
	public RankingFrame() {

		setLayout(null);
		setBackground(new Color(255,220,136));
		setSize(300, 450);
		setLocation(50, 50);
		
		model = new DefaultListModel();
		list = new JList(model);
		list.setEnabled(false);
		list.setBackground(new Color(255,220,136));
		list.setFont(new Font("pixopedia",Font.BOLD,20));
		
		scr = new JScrollPane(list){
			@Override
			public void setBorder(Border border) {
				
			}
		};
		scr.setSize(280,430);
		scr.setLocation(10, 10);
		this.add(scr);
	}
	
	public void insertlist(String s) {
		model.addElement(s);
	}
	


}

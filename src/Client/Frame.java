package Client;

import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Frame extends JFrame implements ActionListener{
	Container c;
	ImagePanel jp;
	JLabel character;
	JLabel title,background, nickname;
	JButton jb1,jb2,jb3;
	JTextField jt;
	Frame2 f2;
	RankingFrame rf;
	int isRanking = -1; //-1: 안보임, 1: 보임
	static String user;
	static dbConnection db;

	public Frame() throws SQLException {
		c = getContentPane();
		c.setLayout(null);
		
		db = new dbConnection();
		
		jp = new ImagePanel();
		rf = new RankingFrame();
			
		ImageIcon ii_title = new ImageIcon("title.png");
		title = new JLabel(ii_title);
		title.setLocation(50, 50);
		title.setSize(300, 150);
		
		
		
		nickname = new JLabel("NICNAME");
		nickname.setSize(100, 50);
		nickname.setLocation(50, 225);
		nickname.setFont(new Font("pixopedia",Font.BOLD,15));
		nickname.setHorizontalAlignment(JLabel.CENTER);
		nickname.setOpaque(false);
		
		jt = new JTextField(15);
		jt.setOpaque(false);
		jt.setSize(200, 50);
		jt.setLocation(150, 225);
		jt.setFont(new Font("pixopedia",Font.BOLD,20));

		
		ImageIcon ii = new ImageIcon("boxer1.png");
		Image img = ii.getImage().getScaledInstance(70, 150, Image.SCALE_SMOOTH);
		ii.setImage(img);
		character = new JLabel(ii);
		character.setSize(70, 150);
		character.setLocation(150, 350);
		
		ImageIcon ii_bt = new ImageIcon("button1.png");
		img = ii_bt.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		ii_bt.setImage(img);
		jb1 = new JButton(ii_bt);
		jb1.setBorderPainted(false);
		jb1.setContentAreaFilled(false);
		jb1.setLocation(50, 550);
		jb1.setSize(75,75);
		jb1.setBackground(new Color(255,210,123));
		jb1.setBorderPainted(false);
		jb1.addActionListener(this);
		
		ii_bt = new ImageIcon("button2.png");
		img = ii_bt.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		ii_bt.setImage(img);
		jb2 = new JButton(ii_bt);
		jb2.setBorderPainted(false);
		jb2.setContentAreaFilled(false);
		jb2.setLocation(150, 550);
		jb2.setSize(75,75);
		jb2.addActionListener(this);
		
		ii_bt = new ImageIcon("button3.png");
		img = ii_bt.getImage().getScaledInstance(100, 75, Image.SCALE_SMOOTH);
		ii_bt.setImage(img);
		jb3 = new JButton(ii_bt);
		jb3.setBorderPainted(false);
		jb3.setContentAreaFilled(false);
		jb3.setLocation(250, 550);
		jb3.setSize(100,75);
		jb3.addActionListener(this);
		
		jp.add(nickname);
		jp.add(jt);
		jp.add(character);
		jp.add(title);
		jp.add(character);
		jp.add(jb1);
		jp.add(jb2);
		jp.add(jb3);
		c.add(jp);
		
		setTitle("무한의 계단");
		setSize(416,689);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1) {
			try {
				
					isRanking *= -1;
					if(isRanking==1) {
						jp.remove(jt);
						jp.remove(nickname);
						jp.remove(character);
						jp.remove(title);
						String r= "";
						jp.add(rf);
						try {
							rf.model.removeAllElements();
							db.selectRanking();
							for(int i=0;i<db.r.size();i++) {
								rf.insertlist(String.format("%3d %s", (i+1),db.r.get(i)));
							}
						} catch (SQLException e1) {e1.printStackTrace();}
					}
					else {
						jp.add(jt);
						jp.add(nickname);
						jp.add(character);
						jp.add(title);
						jp.remove(rf);
					}
					jp.updateUI();
					
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==jb2) {
			jp.setBackgroundImage();
		}
		else if(e.getSource()==jb3) {
			if(jt.getText().equals("")) user = "unknown";
			else user = jt.getText();
			jp.removeAll();
			try {
				try {
					f2 = new Frame2(jp);
				} catch (IOException e1) {e1.printStackTrace();}} catch (SQLException e1) {e1.printStackTrace();}

			
		}
		
	}


}

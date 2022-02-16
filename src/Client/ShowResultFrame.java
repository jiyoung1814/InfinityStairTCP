package Client;

import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;

public class ShowResultFrame implements ActionListener{
	JButton jb1,jb2,jb3;
	JLabel gameover,jl_score, score, jl_best, best;
	ImagePanel jp;
	Frame2 f2;
	JButton jb;
	RankingFrame rf;
	int isRanking = -1; //-1: 안보임, 1: 보임
	int mine,other; //내 점수, 다른 클라이언트 점수
	
	
	
	public ShowResultFrame(ImagePanel jp,Frame2 f2) throws SQLException{
		mine = 0;
		other = 0;
		this.jp = jp;
		this.f2 = f2;
		setWinner();
	}
	public void setWinner() throws SQLException{
		mine = Frame2.score;
		other = Character2Thread.num;
		result();
	}
	
	public void result() throws SQLException {
		
//		Frame.db.insert(Frame.user, Frame2.score);
		rf = new RankingFrame();
		showResult();
		
		ImageIcon ii_bt = new ImageIcon("button1.png");
		Image img = ii_bt.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
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
		
		
		jp.add(jb1);
		jp.add(jb2);
		jp.add(jb3);
		jp.updateUI();
	}
	
	public void showResult() throws SQLException {
		
		gameover = new JLabel();
		if(mine>other) {
			gameover.setText("WIN");
			gameover.setForeground(new Color(255,255,85));	
		}
		else if(mine == other) {
			ImageIcon ii = new ImageIcon("gameover.png");
			Image img = ii.getImage().getScaledInstance(300, 50, Image.SCALE_SMOOTH);
			ii.setImage(img);
			gameover.setIcon(ii);
		}
		else {
			gameover.setText("LOSE");
			gameover.setForeground(new Color(170,52,52));	
		}
		gameover.setSize(300,300);
		gameover.setLocation(50,-50);
		gameover.setFont(new Font("pixopedia",Font.BOLD,70));
		gameover.setHorizontalAlignment(JLabel.CENTER);
		
		ImageIcon ii = new ImageIcon("score.png");
		Image img = ii.getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH);
		ii.setImage(img);
		jl_score = new JLabel(ii);
		jl_score.setSize(150, 50);
		jl_score.setLocation(125,175);
		
		score = new JLabel(Integer.toString(mine));
		score.setHorizontalAlignment(JLabel.CENTER);
		score.setForeground(new Color(176,56,9));
		score.setFont(new Font("pixopedia",Font.BOLD,45));
		score.setBorder(BorderFactory.createLineBorder(new Color(88,23,2)));
		score.setSize(100, 50);
		score.setLocation(150,250);
		
		
		ii = new ImageIcon("player2.png");
		img = ii.getImage().getScaledInstance(150, 30, Image.SCALE_SMOOTH);
		ii.setImage(img);
		jl_best = new JLabel(ii);
		jl_best.setSize(150, 30);
		jl_best.setLocation(125,350);
		jl_best.setBorder(null);
		
		best = new JLabel(Integer.toString(other));
		best.setHorizontalAlignment(JLabel.CENTER);
		best.setForeground(new Color(176,56,9));
		best.setFont(new Font("pixopedia",Font.BOLD,20));
		best.setBorder(BorderFactory.createLineBorder(new Color(88,23,2)));
		best.setSize(100, 25);
		best.setLocation(150,400);
		
		jp.add(gameover);
		jp.add(jl_score);
		jp.add(score);
		jp.add(jl_best);
		jp.add(best);
		jp.updateUI();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1) {
			isRanking *= -1;
			if(isRanking==1) {
				jp.remove(gameover);
				jp.remove(jl_score);
				jp.remove(score);
				jp.remove(jl_best);
				jp.remove(best);
				String r= "";
				jp.add(rf);
				try {
					rf.model.removeAllElements();
					Frame.db.selectRanking();
					for(int i=0;i<Frame.db.r.size();i++) {
						rf.insertlist(String.format("%3d %s", (i+1),Frame.db.r.get(i)));
					}
				} catch (SQLException e1) {e1.printStackTrace();}
			}
			
			else {
				try {
					showResult();
				} catch (SQLException e1) {e1.printStackTrace();}
			}
			
			jp.updateUI();
		}
		else if(e.getSource()==jb2) {
			jp.setBackgroundImage();
		}
		else if(e.getSource()==jb3) {
			jp.removeAll();
			jp.repaint();

			Frame2.isRunning = true;
			Frame2.isKeyBordPressed= false;
			Frame2.direc = 1;
			Frame2.TimerStart = true;
			Frame2.score =0;
			Frame2.num = 0;
			Frame2.isEnd = false;
			CharacterThread.isFalling = false;
			ImagePanel.backY = -(2640-650);
			
			f2.initGame();

		}
	}
	
}

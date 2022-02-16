package Client;

import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;


public class Frame2 implements KeyListener{
	ImagePanel jp;
	JButton up, change;
	JLabel jl_score;
	JProgressBar pgb;
	static int num; //밟을 계단
	static int score; //점수
	static int direc = 1; //-1:왼쪽 , 1: 오른쪽
	static boolean TimerStart = true; //타이머 초기화
	static boolean isRunning = false; //게임 초기화 설정
	static boolean isKeyBordPressed;
	static boolean isStart; //버튼 눌렀을 때 시작
	static boolean isEnd; //캐릭터 떨어지면 결과 창
	static final int maxStair = 500;
	static StairArray sa;
	Character character;
	Character character2;
	CharacterThread ct;
	Timer t;
	Client client;
	
	public Frame2(ImagePanel jp) throws SQLException, UnknownHostException, IOException {
		this.jp = jp;
		sa = new StairArray();
		
		character = new Character("boxer1.png", jp,sa, this);
		character2 = new Character("boxer1.png");
		
		initGame();
		
		client = new Client(jp, character2);
		client.start();
		jp.addKeyListener(this);
	}
	
	public void initGame() {
		
		sa.removeAll(sa);
		character.changeImage("boxer1.png", 70, 150);
		character.setX(150);
		character.setY(350);
		character.setLocation(character.getX(), character.getY());
		ct = new CharacterThread(jp,character,this);
		ct.start();
//		new Thread((Runnable)character).start();
		
		
		ImageIcon ii_bt = new ImageIcon("up.png");
		Image img = ii_bt.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ii_bt.setImage(img);
		up = new JButton(ii_bt);
		up.setBorderPainted(false);
		up.setContentAreaFilled(false);
		up.setLocation(300, 570);
		up.setSize(50,50);
//		up.addActionListener(this);
		
		ii_bt = new ImageIcon("change.png");
		img = ii_bt.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ii_bt.setImage(img);
		change = new JButton(ii_bt);
		change.setBorderPainted(false);
		change.setContentAreaFilled(false);
		change.setLocation(50, 570);
		change.setSize(50,50);
//		change.addActionListener(this);
		
		pgb = new JProgressBar(JProgressBar.HORIZONTAL,0,5000);
		pgb.setValue(5000);
		pgb.setSize(300, 30);
		pgb.setLocation(50, 520);
		pgb.setBackground(new Color(90,70,57));
		pgb.setForeground(new Color(200,71,32));
		pgb.setBorderPainted(false);
		
		jl_score = new JLabel();
		jl_score.setHorizontalAlignment(JLabel.CENTER);
		jl_score.setFont(new Font("pixopedia",Font.BOLD,50));
		jl_score.setText(Integer.toString(score));
//		jl_score.setForeground(new Color(200,71,32));
		jl_score.setLocation(100, 570);
		jl_score.setSize(200, 50);
		
		jp.add(character);
		jp.add(up);
		jp.add(change);
		jp.add(pgb);
		jp.add(jl_score);

		jp.requestFocus();
		
	}
	
	public void judge() {
		
		if(sa.get(num).getX()==100 && character.getX()!=150) {//계단 있음 + 처음 우 클릭시
			score++;
			num++;// 다음 밟을 계단
			jl_score.setText(Integer.toString(score));
			jp.MoveBackground();//배경 움직이기
			jp.updateUI();
			try {
				client.clicked(direc, ct.foot, num);
			} catch (IOException e1) {e1.printStackTrace();} //캐릭터의 방향과 어느 발인지 밟아야 하는 계단에 대한 정보를 서버로 보냄
		}
		else {//계단 없음
			ct.isFalling = true;
			try {
				client.fail();
			} catch (IOException e1) {e1.printStackTrace();}
		}
	}
	

	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		if(isRunning) {
			if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_LEFT) {
				if(TimerStart) {
					t = new Timer(jp,pgb, client);
					t.start();
					TimerStart = false;
				}
				else {
					isKeyBordPressed = true; //키보드가 눌리면 시간 초기화
					if (e.getKeyCode()==KeyEvent.VK_LEFT) direc *= -1; //오른쪽 키보드 눌렀을 시 방향 전환}
					
					if(num>0) { //처음 계단(0번재 계단)밟을 때를 제외하고 계단 움직임
						sa.movingStair();
					}
					ct.isMoving = true;
					judge();
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}

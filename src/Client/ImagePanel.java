package Client;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import Client.Character;
import Client.Timer;

public class ImagePanel extends JPanel {
//	private Image img;
	int imageNumber; 
	String[] imgPath = {"background1.png", "background2.png"};
	static boolean moveBackground;
	static int backY = -(2640-650);

	JButton up, change;
	JLabel jl_score;
	JProgressBar pgb;
	ImageIcon ii;
	Image img;
	
//	Image buffImg;
//	Graphics buffG;

	 
	public ImagePanel() {
		imageNumber = 0;
		setBackgroundImage();
		
	    setLayout(null);
	    setLocation(0, 0);
	    setSize(400, 650);  
	}
	
	public void MoveBackground() {
		backY+= 10;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img,0,backY,this);
	}
	
	
	public void setBackgroundImage() {
		img = new ImageIcon(imgPath[imageNumber]).getImage().getScaledInstance(400, 2640, Image.SCALE_SMOOTH);
		if(imageNumber==1) {
			backY = -(2640-650);
			imageNumber= -1;
		}
		imageNumber++;
	}
	
//	@Override
//    public void paint(Graphics g) {
//        buffImg = createImage(getWidth(),getHeight()); // 버퍼링용 이미지 ( 도화지 )
//        buffG = buffImg.getGraphics(); // 버퍼링용 이미지에 그래픽 객체를 얻어야 그릴 수 있다고 한다. ( 붓? )
//        update(g);
//    }
//
//    @Override
//    public void update(Graphics g) {
//        buffG.clearRect(0, 0, 854, 480); // 백지화
//        buffG.drawImage(flight,xpos,ypos, this); // 유저 비행기 그리기.
//        g.drawImage(buffImg,0,0,this); // 화면g애 버퍼(buffG)에 그려진 이미지(buffImg)옮김. ( 도화지에 이미지를 출력 )
//        repaint();
//    }


}

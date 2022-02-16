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
//        buffImg = createImage(getWidth(),getHeight()); // ���۸��� �̹��� ( ��ȭ�� )
//        buffG = buffImg.getGraphics(); // ���۸��� �̹����� �׷��� ��ü�� ���� �׸� �� �ִٰ� �Ѵ�. ( ��? )
//        update(g);
//    }
//
//    @Override
//    public void update(Graphics g) {
//        buffG.clearRect(0, 0, 854, 480); // ����ȭ
//        buffG.drawImage(flight,xpos,ypos, this); // ���� ����� �׸���.
//        g.drawImage(buffImg,0,0,this); // ȭ��g�� ����(buffG)�� �׷��� �̹���(buffImg)�ű�. ( ��ȭ���� �̹����� ��� )
//        repaint();
//    }


}

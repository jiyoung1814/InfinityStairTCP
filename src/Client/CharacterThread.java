package Client;

import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CharacterThread extends Thread{
	ImagePanel jp;
	static int foot = 1; //-1: 왼발, 1: 오른발
	static boolean isMoving;
	static boolean isFalling;
	Character character;
	Frame2 f2;
	ShowResultFrame rf;
	
	public CharacterThread(ImagePanel jp, Character character, Frame2 f2) {
		this.jp = jp;
		this.character = character;
		this.f2 = f2;
		foot = 1;
		isMoving = false;
		isFalling = false;
	}
	
	public void moveCharacter() throws InterruptedException{
		foot *=-1;
		if(Frame2.direc==1) {//왼쪽 방향
			if(foot==-1) {//왼발
				character.changeImage("boxer2.png", 82, 150);
				character.setXY(90, 500-character.getHeight());
			}
			else {//오른발
				character.changeImage("boxer3.png", 111, 150);
				character.setXY(200-character.getWidth(), 500-character.getHeight());
			}
			
			Thread.sleep(250);
			
			character.changeImage("boxer1.png", 82, 150);
			character.setXY(100-15, 500-character.getHeight()-30);
		}
		else if(Frame2.direc== -1) {//오른쪽 방향
			System.out.println(Frame2.num);
			if(Frame2.num==0) { //처음 오른쪽 방향 키보드
				character.changeImage("boxer2_r.png", 82, 150);
				character.setXY(100+50,500-character.getHeight());
				
				Thread.sleep(250);
				character.changeImage("boxer1_r.png", 82, 150);
				character.setXY(character.getX()+50, character.getY()-30);
//				System.out.println(character.getX());
//				isFalling = true;
			}
			else {
				if(foot==-1) {//오른발
					character.changeImage("boxer2_r.png", 82, 150);
					character.setXY(150-character.getWidth(), 500-character.getHeight());
						
				}
				else {//왼발
					character.changeImage("boxer3_r.png", 111, 150);
					character.setXY(60, 500-character.getHeight());
				}
				
				Thread.sleep(250);
				
				character.changeImage("boxer1_r.png", 82, 150);
				character.setXY(150+20-character.getWidth(), character.getY()-30);
			}
			
		}
				
	}
	
	public void fallingCharacter() throws InterruptedException{
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {e1.printStackTrace();}
		
		
			
		if(character.getX()==85) //왼쪽방향
			character.changeImage("boxer4.png", 88, 150);
		
		else 
			character.changeImage("boxer4_r.png", 88, 150);	
	
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {e.printStackTrace();}	
		
		while(true) {
			character.setY(character.getY()+10);
			character.setLocation(character.getX(), character.getY());
			jp.updateUI();
			
			if(character.getY()>650) {
				jp.remove(character);
				jp.updateUI();
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {e.printStackTrace();}	
		}
	}
	
	
	
	@Override
	public void run() {
		while(true) {
			System.out.print("");
			if(isMoving) {
				try {
					moveCharacter();
					jp.updateUI();
				} catch (InterruptedException e) {e.printStackTrace();}
				isMoving = false;
			}
			
			if(isFalling){
				try {
					fallingCharacter();
				} catch (InterruptedException e1) {e1.printStackTrace();}
				jp.removeAll();
				try {
					rf = new ShowResultFrame(jp, f2);
				} catch (SQLException e) {e.printStackTrace();}
				break;
			}
			
		}
		
	}

}

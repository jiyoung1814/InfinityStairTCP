package Client;

import java.sql.SQLException;

public class Character2Thread extends Thread{
	
	ImagePanel jp;
//	int foot = 1; //-1: 왼발, 1: 오른발
	boolean subisMoving;
	static boolean subisFalling;
	Character character2;
	Frame2 f2;
	int direc, foot;
	static int num;
	
	public Character2Thread(ImagePanel jp, Character character2) {
		num = 0;
		this.jp = jp;
		this.character2 = character2;
		this.f2 = f2;
	}
	
	public void recivedMoving(int direc, int foot, int num) throws InterruptedException {
		this.direc = direc;
		this.foot = foot;
		this.num = num;
		moveCharacter();
	}
	
	public void moveCharacter() throws InterruptedException{
		if(direc==1) {//왼쪽 방향
			if(foot==-1) {//왼발
				character2.changeImage("boxer2.png", 82, 150);
				character2.setXY(Frame2.sa.get(num-1).getX(), Frame2.sa.get(num-1).getY()-120);
			}
			else {//오른발
				character2.changeImage("boxer3.png", 111, 150);
				character2.setXY(Frame2.sa.get(num-1).getX()+60-character2.getWidth(), Frame2.sa.get(num-1).getY()-120);
			}
			
			Thread.sleep(250);
			character2.changeImage("boxer1.png", 82, 150);
			character2.setXY(Frame2.sa.get(num-1).getX()-15, Frame2.sa.get(num-1).getY()-120-30);
		}
		else if(direc == -1) {//오른쪽 방향
			
			if(num==0) { //처음 오른쪽 방향 키보드
				character2.changeImage("boxer2_r.png", 82, 150);
				character2.setXY(100+50,500-character2.getHeight());
				
				Thread.sleep(250);
				
				character2.changeImage("boxer1_r.png", 82, 150);
				character2.setXY(character2.getX()+50, character2.getY()-30);
				subisFalling = true;
			}
			else {
				if(foot==-1) {//오른발
					character2.changeImage("boxer2_r.png", 82, 150);
					character2.setXY(Frame2.sa.get(num-1).getX()+30-character2.getWidth(), Frame2.sa.get(num-1).getY()-120);
						
				}
				else {//왼발
					character2.changeImage("boxer3_r.png", 111, 150);
					character2.setXY(Frame2.sa.get(num-1).getX()-50, Frame2.sa.get(num-1).getY()-120);
				}
				Thread.sleep(250);
				character2.changeImage("boxer1_r.png", 82, 150);
				character2.setXY(Frame2.sa.get(num-1).getX()-15, Frame2.sa.get(num-1).getY()-120-30);
			}
			
		}
		subisMoving = true;
	}
	
	public void fallingCharacter() throws InterruptedException{
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e1) {e1.printStackTrace();}
			
		if(direc==1) { //왼쪽방향
			character2.changeImage("boxer4.png", 88, 150);
		}
		else {
			character2.changeImage("boxer4_r.png", 88, 150);	
		}
	
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {e.printStackTrace();}	
		
		while(true) {
			character2.setY(character2.getY()+10);
			character2.setLocation(character2.getX(), character2.getY());
			jp.updateUI();
			
			if(character2.getY()>650) {
				jp.remove(character2);
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
			if(num == 0) { //서브 캐릭터가 시작도 안했을 때
				character2.setXY(Frame2.sa.get(0).getX()+60,Frame2.sa.get(0).getY()-120);
				character2.setLocation(character2.getX(), character2.getY());
				jp.updateUI();
			}
			else {//계단 움직이면 서브 케릭터 위치 변경
				if(StairArray.isStairMoving) { 
				character2.setXY(Frame2.sa.get(num-1).getX()-15, Frame2.sa.get(num-1).getY()-120-30);
				StairArray.isStairMoving = false;
				}
			}
			
			System.out.print("");
			if(subisMoving) {
				jp.updateUI();
				subisMoving = false;
			}
			
			if(subisFalling){
				try {
					fallingCharacter();
				} catch (InterruptedException e1) {e1.printStackTrace();}
				jp.removeAll();
				try {
					new ShowResultFrame(jp, f2);
				} catch (SQLException e) {e.printStackTrace();
				break;
				}
			
			}
		
		}
	}

}

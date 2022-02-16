package Client;

import java.util.ArrayList;


public class StairArray extends ArrayList<Stair>{
	Stair s;
	static boolean isStairMoving;
	
	public StairArray() {
	}
	
	public void movingStair() {
		for(int i=0;i<Frame2.maxStair;i++) { //계단 이동
			this.get(i).setX(this.get(i).getX()+Frame2.direc*50);
			this.get(i).setY(this.get(i).getY()+30);
			isStairMoving = true;
		}
		
	}

}

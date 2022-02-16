package Server;

import java.util.ArrayList;
import java.util.Random;


public class StairArray extends ArrayList<Stair>{
	Stair s;
	Random r = new Random(); //0:哭率, 1:坷弗率
	static int number;
	
	public StairArray() {
		number=0;
	}
	
	public void makeStair() {
		s = new Stair("rock.png");
		if(number==0) {
			s.setX(100);
			s.setY(470);
		}
		else {
			int t = r.nextInt(2);
			
			while(true) {
				
				if(t==0) {
					s.setX(this.get(number-1).getX()-50); //哭率 拌窜 积己
					if(s.getX()<50) {
						t=1;
						continue;
					}
					else break;
				}
				else {
					s.setX(this.get(number-1).getX()+50);
					if(s.getX()>350) {
						t=0;
						continue;
					}
					else break;
				}
			}
			
			s.setY(this.get(number-1).getY()-30);
		}
		
		s.setLocation(s.getX(),s.getY());
		
		add(s);
		
//		jp.add(this.get(number));
//		jp.repaint();
		
		System.out.println("cnt: "+number);
		System.out.println(s.getX()+","+s.getY());
		number++;
		System.out.println(number);
		
	}
	
	
	

}

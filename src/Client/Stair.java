package Client;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Stair extends JLabel{
	int x, y;
	
	public Stair(int x, int y) {
		ImageIcon ii = new ImageIcon("rock.png");
		Image img = ii.getImage().getScaledInstance(50, 30, Image.SCALE_SMOOTH);
		ii.setImage(img);
		setIcon(ii);
		setSize(50, 30);
		this.x = x;
		this.y =y;
		setLocation(x,y);
	}
	
	
	public void setX(int x) {
		this.x =x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y =y;
	}
	
	public int getY() {
		return y;
	}


}

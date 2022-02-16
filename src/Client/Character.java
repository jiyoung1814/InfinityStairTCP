package Client;

import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class Character extends JLabel{
	int x, y;
	Frame2 f2;
	
	ImagePanel jp;
	ArrayList<Stair> sa;
	
	public Character(String path) {
		ImageIcon ii = new ImageIcon(path);
		setIcon(ii);
		setSize(70, 150);
	}
	
	public Character(String path, ImagePanel jp,ArrayList<Stair> sa, Frame2 f2) {
		this.jp = jp;
		this.sa = sa;
		this.f2 = f2;
		ImageIcon ii = new ImageIcon(path);
		setIcon(ii);
		setSize(70, 150);
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
	
	public void setXY(int x, int y) {
		this.x =x;
		this.y =y;
	}
	
	public void changeImage(String path,int w, int h) {
		setVisible(false);
		ImageIcon ii = new ImageIcon(path);
		setIcon(ii);
		setSize(w, h);
		setLocation(x,y);
		setVisible(true);
	}
}

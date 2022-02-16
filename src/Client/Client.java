package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread{
	static final int port = 9999;
	static final String host = "210.102.142.20";
	BufferedReader br;
	BufferedWriter bw;
	Socket s;
	Character character2;
	Character2Thread c2t;
	ImagePanel jp;
	String str="";
	Stair stair;
	
	
	public Client(ImagePanel jp, Character character2) throws UnknownHostException, IOException {
		// TODO Auto-generated constructor stub
		s = new Socket(host,port);
		System.out.println("연결 완료");
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		this.jp = jp;
		this.character2 = character2;
		c2t = new Character2Thread(jp, character2);
	}

	public void run() {
		while(true) {//서버에서 계단 생성 후 계단 위치 받아서 sa에 저장 후 jp에 부착
			if(Frame2.sa.size()>=Frame2.maxStair) { //계단 생성 완료
				character2.setXY(Frame2.sa.get(0).getX()+60,Frame2.sa.get(0).getY()-120);
				jp.add(character2);
				jp.updateUI();
				c2t.start();
				Frame2.isRunning = true;//키보드 눌림 OK
				break;
			}
			try {
				str = br.readLine();
			} catch (IOException e) {e.printStackTrace();}
			
			String[] location_s = str.split(" ");
			stair = new Stair(Integer.parseInt(location_s[0]),Integer.parseInt(location_s[1]));
			Frame2.sa.add(stair);
			jp.add(stair);
			jp.updateUI();
		}
		while(true) {
			try {
				str = br.readLine();
			} catch (IOException e) {e.printStackTrace();}
			
			if(str.equals("falling")) {
				c2t.subisFalling = true;
			}
			else {
				String[] recived = str.split(" ");
				System.out.println("방향: "+ recived[0]);
				try {
					c2t.recivedMoving(Integer.parseInt(recived[0]),Integer.parseInt(recived[1]),Integer.parseInt(recived[2]));
				} catch (NumberFormatException | InterruptedException e) {e.printStackTrace();}
			}
		}
		
	}

	public void clicked(int direc, int foot, int num) throws IOException {
		bw.write(direc+" "+foot+" "+num+"\n");
		bw.flush();
	}
	
	public void fail() throws IOException {
		bw.write("falling\n");
		bw.flush();
	}
}

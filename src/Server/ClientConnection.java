package Server;

import java.io.*;
import java.net.*;

public class ClientConnection extends Thread{
	BufferedReader br;
	BufferedWriter bw;
	Socket s;
	ClientConnection[] c;
	int cnt;
	dbConnection db;
	StairArray sa;
	String str ="";
	
	public ClientConnection(Socket s, int cnt, ClientConnection[] c, StairArray sa, dbConnection db) throws IOException {
//		this.s = s;
		this.cnt = cnt;
		this.c = c;
		this.db =db;
		this.sa = sa;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
	}
	
	public void run() {
		for(int i=0;i<sa.number;i++) { //������ ó�� ��� ����
			try {
				bw.write(sa.get(i).getX()+" "+sa.get(i).getY()+"\n");
				bw.flush();
			} catch (IOException e) {e.printStackTrace();}
		}
		while(true) {
			try {
				str = br.readLine();
			} catch (IOException e) {e.printStackTrace();}
			
			for(int i=0;i<c.length;i++) {
				if(i!=cnt) {//�ڽ� �����ϰ� ������ Ŭ���̾�Ʈ���� ���� ���� �ٽ� ������
					try {
						c[i].bw.write(str+"\n");
						c[i].bw.flush();
					} catch (IOException e) {e.printStackTrace();}
					
				}
			}
		}
		
		
	}
}

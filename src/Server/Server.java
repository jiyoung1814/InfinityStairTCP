package Server;

import java.io.*;
import java.net.*;
import java.sql.*;
import javax.swing.*;

public class Server extends Thread{
	ServerSocket ss;
	Socket s;
	static int cnt;
	static final int max = 2;
	final int maxStair = 500;
//	static boolean isReady; //�θ��� Ŭ���̾�Ʈ ����
	ClientConnection[] connection = new ClientConnection[max];
	dbConnection db;
	
	StairArray sa;
	
	public Server(int port) throws IOException, SQLException {
		ss  = new ServerSocket(port);
		System.out.println(port);
//		db = new dbConnection();
		sa = new StairArray();
	}
	
	public void run() {
		while(cnt<max) {
			try {//�θ��� Ŭ���̾�Ʈ ����
				s = ss.accept();
				connection[cnt] = new ClientConnection(s, cnt, connection, sa, db);
			} catch (IOException e) {e.printStackTrace();}
			cnt++;
		}
		
		for(int i=0;i<maxStair;i++) { //ó�� 500���� ��� ����
			sa.makeStair();
		}
//		isReady = true;
		for(int i=0;i<cnt;i++) {
			connection[i].start();
		}
		
	}


}

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
//	static boolean isReady; //두명의 클라이언트 접속
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
			try {//두명의 클라이언트 접속
				s = ss.accept();
				connection[cnt] = new ClientConnection(s, cnt, connection, sa, db);
			} catch (IOException e) {e.printStackTrace();}
			cnt++;
		}
		
		for(int i=0;i<maxStair;i++) { //처음 500개의 계단 생성
			sa.makeStair();
		}
//		isReady = true;
		for(int i=0;i<cnt;i++) {
			connection[i].start();
		}
		
	}


}

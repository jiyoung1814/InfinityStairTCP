package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class dbConnection {
	String url, id, pw;
	Connection c;
	Statement stmt;
	ResultSet result;
	ArrayList<String> r = new ArrayList<>();
	
	public dbConnection() throws SQLException{
		url = "jdbc:mysql://localhost:3306/game?serverTimezone=UTC"; //�� ��ǻ�Ϳ� ������ java �����ͺ��̽��� �����ϱ� ���� url
		id = "root";
		pw = "1424";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //JDBC ����̹��� �ε� -> JDBC ����̹� ������ ����̹� �������̽��� ����� Ŭ������ �������� �ε��� �� �ڵ����� JDBC ����̹� �ν��Ͻ��� �����Ǿ� �غ�
			c = DriverManager.getConnection(url,id,pw);//url�� id, �н������ �����ͺ��̽��� ����
			System.out.println("db���� ����");
		}catch(ClassNotFoundException e) {
			System.out.println("JDBC ����̹� �ε� ����");
		}catch(SQLException e) {
			System.out.println("db���� ����");
		}
		stmt = c.createStatement(); //statement ��ü ��ȯ(�ڹ����α׷��� DB������ SQL query���� �����ϰ�, DB�� ó���� ����� �ٽ� �ڹ����α׷������� ������ �� �ֵ��� ���´�)
		
//		stmt.executeLargeUpdate("use game"); //Game database�� ����
	}
	
	
	

}

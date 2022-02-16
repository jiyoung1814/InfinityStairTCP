package Client;

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
	
	public void insert(String name, int score) throws SQLException {
		int num =0;
		String s ="insert into score(user, score, datetime) value('" + name + "'," + score + ",now());";
		stmt.executeUpdate(s);
		stmt.executeUpdate("delete n1 from score n1, score n2 where n1.num > n2.num and n1.user = n2.user and n1.score = n2.score;"); //�г��Ӱ� ������ ���� �� ���� �� ����
	}
	
	public void selectRanking() throws SQLException {
		r.removeAll(r);
		result = stmt.executeQuery("SELECT *  from score order by score desc;");
		while(result.next()) {
			r.add(String.format("%-10s %-5d %s",result.getString(2),result.getInt(3),result.getString(4)));
//			r.add(String.format("%-10s", result.getString(2))+" "+String.format("%-5d", result.getInt(3))+" "+String.format("%s", result.getString(4)));
		}
	}
	
	public int BestScore(String user) throws SQLException {
		int max = 0;
		result = stmt.executeQuery("select max(score) as score from score where user= '"+user+"';");
		while(result.next()) {
			max = result.getInt(1);
		}
		System.out.println(max);
		return max;
	}
	

}

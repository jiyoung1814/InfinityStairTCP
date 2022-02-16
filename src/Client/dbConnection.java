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
		url = "jdbc:mysql://localhost:3306/game?serverTimezone=UTC"; //내 컴퓨터에 저장한 java 데이터베이스와 연결하기 위한 url
		id = "root";
		pw = "1424";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //JDBC 드라이버를 로드 -> JDBC 드라이버 파일의 드라이버 인터페이스를 상속한 클래스가 동적으로 로딩될 때 자동으로 JDBC 드라이버 인스턴스가 생성되어 준비
			c = DriverManager.getConnection(url,id,pw);//url과 id, 패스워드로 데이터베이스와 연결
			System.out.println("db연결 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
		}catch(SQLException e) {
			System.out.println("db연결 오류");
		}
		stmt = c.createStatement(); //statement 객체 반환(자바프로그램이 DB쪽으로 SQL query문을 전송하고, DB가 처리된 결과를 다시 자바프로그램쪽으로 전달할 수 있도록 돕는다)
		
//		stmt.executeLargeUpdate("use game"); //Game database에 접속
	}
	
	public void insert(String name, int score) throws SQLException {
		int num =0;
		String s ="insert into score(user, score, datetime) value('" + name + "'," + score + ",now());";
		stmt.executeUpdate(s);
		stmt.executeUpdate("delete n1 from score n1, score n2 where n1.num > n2.num and n1.user = n2.user and n1.score = n2.score;"); //닉네임과 점수가 같을 시 이전 행 제거
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

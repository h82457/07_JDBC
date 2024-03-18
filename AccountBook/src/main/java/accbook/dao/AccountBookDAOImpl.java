package accbook.dao;

import static accbook.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import accbook.dto.AccountBook;


public class AccountBookDAOImpl implements AccountBookDAO{

	// JDBC 객체 참조 변수 선언
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// Properties 객체 참조 변수 선언
	private Properties prop;
	
	// 기본 생성자로 객체 생성시 Properties 객체 생성 + xml 파일 내용 읽어오기
	public AccountBookDAOImpl() {
		
		try {
			prop = new Properties();
			String path = AccountBookDAOImpl.class.getResource("/accbook/sql/sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(path));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 조회  
	@Override
	public List<AccountBook> selectAll(Connection conn) throws SQLException {
		
		List<AccountBook> accList = new ArrayList<AccountBook>();
		
		try {
			String sql = prop.getProperty("selectIncome");
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "s1");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int balance = rs.getInt("BALANCE");
				String inTitle = rs.getString("IN_TITLE");
				int inAmount = rs.getInt("IN_AMOUNT");
				String inDate = rs.getString("IN_DATE");
				
				AccountBook acc = new AccountBook(balance, inTitle, inAmount, inDate);
				accList.add(acc);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return accList;
	}	
	
	
	
}

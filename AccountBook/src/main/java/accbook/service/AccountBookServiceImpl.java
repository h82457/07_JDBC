package accbook.service;

import static accbook.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import accbook.dao.AccountBookDAO;
import accbook.dao.AccountBookDAOImpl;
import accbook.dto.AccountBook;

public class AccountBookServiceImpl implements AccountBookService{

	// DAO 객체 생성
	private AccountBookDAO dao;

	public AccountBookServiceImpl() {
		dao = new AccountBookDAOImpl();
	}

	// 조회 
	@Override
	public List<AccountBook> selectAll() throws SQLException {
		
		Connection conn = getConnection();
		List<AccountBook> accList = dao.selectAll(conn);
		close(conn);
		
		return accList;
	}
}

package accbook.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import accbook.dto.AccountBook;

public interface AccountBookDAO {

	List<AccountBook> selectAll(Connection conn) throws SQLException;

}

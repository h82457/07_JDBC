package accbook.service;

import java.sql.SQLException;
import java.util.List;

import accbook.dto.AccountBook;

public interface AccountBookService {

	List<AccountBook> selectAll() throws SQLException;

}

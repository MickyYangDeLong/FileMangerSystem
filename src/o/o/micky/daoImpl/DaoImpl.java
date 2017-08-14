package o.o.micky.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DaoImpl {
	Connection getConnection();

	void closeConnection(Connection conn);

	ResultSet select(String sql) throws SQLException;

	void updateDeleteInsert(String sql);
}

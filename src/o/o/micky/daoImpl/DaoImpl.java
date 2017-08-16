package o.o.micky.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import o.o.micky.dao.pojo.DbConnAndRs;

public interface DaoImpl {
	Connection getConnection();

	DbConnAndRs select(Connection conn, String sql) throws SQLException;

	PreparedStatement updateDeleteInsert(Connection conn, String sql);

	void closeAll(DbConnAndRs dbConnAndRs);
	void closeConnAndPst(PreparedStatement pst, Connection connection);
}

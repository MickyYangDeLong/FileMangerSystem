package o.o.micky.dao.pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbConnAndRs {
	private ResultSet rs;
	private PreparedStatement pStatement;
	private Connection connection;

	public DbConnAndRs() {
	}

	public DbConnAndRs(ResultSet rs, PreparedStatement pStatement,
			Connection connection) {
		setpStatement(pStatement);
		setRs(rs);
		setConnection(connection);
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public PreparedStatement getpStatement() {
		return pStatement;
	}

	public void setpStatement(PreparedStatement pStatement) {
		this.pStatement = pStatement;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}

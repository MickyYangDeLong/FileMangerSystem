package o.o.micky.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import o.o.micky.daoImpl.DaoImpl;

public class Dao implements DaoImpl {

	@Override
	public Connection getConnection() {
		Connection conn = null;
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/pureservlet");
			dataSource.setUser("root");
			dataSource.setPassword("123");
			dataSource.setMaxPoolSize(100);
			dataSource.setMinPoolSize(50);
			dataSource.setInitialPoolSize(50);
			dataSource.setMaxStatements(180);
			conn = dataSource.getConnection();
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	@Override
	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ResultSet select(String sql) throws SQLException {
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}/* finally {
			closeConnection(connection);
		}*/
		return rs;
	}

	@Override
	public void updateDeleteInsert(String sql) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

	}

}

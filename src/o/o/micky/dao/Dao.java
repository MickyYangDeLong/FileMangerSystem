package o.o.micky.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import o.o.micky.dao.pojo.DbConnAndRs;
import o.o.micky.daoImpl.DaoImpl;

public class Dao implements DaoImpl {

	/*
	 * public Connection getConnection() { Connection conn = null;
	 * ComboPooledDataSource dataSource = new ComboPooledDataSource(); try {
	 * dataSource.setDriverClass("com.mysql.jdbc.Driver");
	 * dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/pureservlet");
	 * dataSource.setUser("root"); dataSource.setPassword("123");
	 * dataSource.setMaxPoolSize(100); dataSource.setMinPoolSize(50);
	 * dataSource.setInitialPoolSize(50); dataSource.setMaxStatements(180); conn
	 * = dataSource.getConnection(); } catch (PropertyVetoException e) {
	 * e.printStackTrace(); } catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * return conn; }
	 */
	public Connection getConnection() { // ������ݿ����ӵķ���
		Connection conn = null; // �������ݿ���������
		try {
			/*
			 * Context initial=new InitialContext(); //��������� DataSource
			 * ds=(DataSource)initial.lookup("java:comp/env/rlzy");
			 * conn=ds.getConnection();
			 */// ͨ������Դ������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/pureservlet?useUnicode=true&characterEncoding=GBK",
							"root", "123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // �쳣�Ĳ��������
		catch (SQLException e) {
			e.printStackTrace();
		} // �쳣�Ĳ��������
		return conn; // �������ݿ�����
	}

	@Override
	public void closeAll(DbConnAndRs dbConnAndRs) {
		try {
			if (dbConnAndRs.getRs() != null) {
				dbConnAndRs.getRs().close();
			}
			closeConnAndPst(dbConnAndRs.getpStatement(),
					dbConnAndRs.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	@Override
	public void closeConnAndPst(PreparedStatement pst, Connection connection) {
		try {
			if (pst != null) {
				pst.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public DbConnAndRs select(Connection conn, String sql) throws SQLException {
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Connection connection = conn;
		try {
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new DbConnAndRs(rs, preparedStatement, connection);
	}

	@Override
	public PreparedStatement updateDeleteInsert(Connection connection,
			String sql) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}
}

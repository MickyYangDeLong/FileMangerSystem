package o.o.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import o.o.micky.dao.DaoFactory;
import o.o.micky.dao.pojo.DbConnAndRs;
import o.o.micky.daoImpl.DaoImpl;
import o.o.pojo.User;

public class LoginManager {

	public static User loginCheck(String userName, String passWord)
			throws SQLException {
		User user = new User();
		DaoImpl queryUser = DaoFactory.getDao();
		ResultSet rs = null;
		String querySql = "select * from user where userName='" + userName
				+ "'";
		Connection connection = null;
		DbConnAndRs dbConnAndRs = null;
		try {
			connection = queryUser.getConnection();
			dbConnAndRs = queryUser.select(connection, querySql);
			rs = dbConnAndRs.getRs();
			if (rs == null) {
				return user;
			}
			while (rs.next()) {
				user.setPassWord(rs.getString("passWord"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			queryUser.closeAll(dbConnAndRs);
		}
		return user;
	}

}

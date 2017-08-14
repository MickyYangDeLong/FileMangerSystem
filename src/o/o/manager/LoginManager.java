package o.o.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import o.o.micky.dao.Dao;
import o.o.micky.dao.DaoFactory;
import o.o.pojo.User;

public class LoginManager {

	public static User loginCheck(String userName, String passWord)
			throws SQLException {
		User user = new User();
		Dao queryUser = DaoFactory.getDao();
		ResultSet rs = null;
		String querySql = "select * from user where userName='" + userName
				+ "'";
		try {
			rs = queryUser.select(querySql);
			if (rs == null) {
				return user;
			}
			while (rs.next()) {
				user.setPassWord(rs.getString("passWord"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}

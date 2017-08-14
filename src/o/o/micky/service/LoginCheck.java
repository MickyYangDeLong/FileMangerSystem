package o.o.micky.service;

import java.sql.SQLException;

import o.o.manager.LoginManager;
import o.o.micky.check.CheckUtil;
import o.o.micky.constans.LoginConstants;
import o.o.micky.serviceImpl.LoginCheckImp;
import o.o.pojo.LoginCheckResult;
import o.o.pojo.User;

public class LoginCheck implements LoginCheckImp {
	private LoginCheckResult result = new LoginCheckResult();

	@Override
	public synchronized LoginCheckResult loginCheck(String userName,
			String passWord) {
		if (!CheckUtil.checkInputString(userName)
				|| !CheckUtil.checkInputString(passWord)) {
			result.setCodeAndMessage(
					"message:invalid input,please input again",
					LoginConstants.LOGIN_CHECK_FAIL);
			return result;
		}
		checkDbLoginInfo(userName, passWord);
		return result;
	}

	private void checkDbLoginInfo(String userName, String passWord) {
		User user = null;
		try {
			user = LoginManager.loginCheck(userName, passWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user == null || user.getPassWord() == null) {
			result.setCodeAndMessage("the" + userName
					+ "is not exists,please input a right one or register it!",
					LoginConstants.LOGIN_CHECK_FAIL);
		}
		if (passWord.equals(user.getPassWord())) {
			result.setCodeAndMessage("", LoginConstants.LOGIN_CHECK_SUCCESS);
		} else {
			result.setCodeAndMessage(
					"password is wrong,input the right password",
					LoginConstants.LOGIN_CHECK_FAIL);
		}
	}

}

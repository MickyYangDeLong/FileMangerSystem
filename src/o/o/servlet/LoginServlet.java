package o.o.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import o.o.micky.constans.LoginConstants;
import o.o.micky.service.LoginCheck;
import o.o.micky.serviceImpl.LoginCheckImp;
import o.o.pojo.LoginCheckResult;

public class LoginServlet extends BaseServlet {
	private String userName;
	private String passWord;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private LoginCheckImp loginCheck;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		login(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		login(req, res);
	}

	public void login(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		loginCheck = new LoginCheck();
		request = req;
		response = res;
		userName = request.getParameter("username");
		passWord = request.getParameter("password");
		LoginCheckResult result = loginCheck.loginCheck(userName, passWord);
		setMessage(result.getCheckMessage());
		if (result.getCheckResultCode() == LoginConstants.LOGIN_CHECK_SUCCESS) {
			// request.getSession().setAttribute("tip", "login success!");
			request.getRequestDispatcher("welcome.jsp").forward(request,
					response);
		} else {
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}

	}

	public void setMessage(String message) {
		request.setAttribute("message", message);
	}

}

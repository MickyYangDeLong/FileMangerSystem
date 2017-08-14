package o.o.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	/**
	 * һ��Servlet���Դ���������
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("method");// ��ȡ������
		if (name == null || name.isEmpty()) {
			throw new RuntimeException("û�д���method����,�����������õķ���");
		}
		// Class<? extends BaseServlet> c = );// ��õ�ǰ���Class����
		Method method = null;
		try {
			// ���Method����
			method = this.getClass().getMethod(name, HttpServletRequest.class,
					HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("û���ҵ�" + name + "����������÷����Ƿ����");
		}

		try {
			method.invoke(this, req, resp);// ������÷���
		} catch (Exception e) {
			System.out.println("����õķ���" + name + ",�ڲ��������쳣");
			throw new RuntimeException(e);
		}

	}
}

package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLogoutSevlet
 */
//로그아웃 페이지 매핑
@WebServlet("/admin/logout")
public class AdminLogoutSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션에 저장되있는 id와 role정보를 지움 => 로그인정보가 사라짐 => 로그아웃
		HttpSession session = request.getSession();
		  session.removeAttribute("id");
		  session.removeAttribute("role");
		  request.getRequestDispatcher("/WEB-INF/views/admin/admin-login.jsp").forward(request, response); // 로그아웃하면 다시 관리자로그인 페이지로 이동
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

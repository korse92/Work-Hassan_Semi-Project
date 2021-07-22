package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.model.service.AdminService;
import admin.model.vo.Admin;
import common.util.MvcUtils;
//import member.model.vo.Member;

/**
 * 주소이동은 doGet 보여주는건 doPost (boardUpdate할때 비슷하게 강의)
 * 관리자  jsp필요
 * 
 * Servlet implementation class AdminLoginServlet
 * 
 * http://localhost:9090/hassan/front/login.jsp  여기서 관리자 페이지 들어가야함
 */
@WebServlet(urlPatterns={"/admin/login"})
public class AdminLoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private AdminService adminService = new AdminService();
       
   //doGet방식으로 jsp랑 연결 admin/login
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  HttpSession session = request.getSession();
	  if(session.getAttribute("id") == null){
	      request.getRequestDispatcher("/WEB-INF/views/admin/admin-login.jsp").forward(request, response);
	  }
	  else {
		  response.sendRedirect("memberList");
	  }
      //나중에 "WEB-INF/views/admin/admin-login.jsp"로 나중에 바꿔서 연결 front는 일단 test하는거
   }
   

   /**
    * post작성 로그인 (admin-login.jsp 의 form에서 post요청했는지 확인. 로그인 강의 1분 14초)
    * post방식은 무조건 form메소드가 Post일때 submit하면  들어온다
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //1. 인코딩처리(한글 깨짐 방지) 필수
      request.setCharacterEncoding("utf-8");
      HttpSession session = request.getSession();
      //2. 사용자입력값 처리
      String adminId = request.getParameter("adminId");      
      String adminPassword = request.getParameter("adminPassword");
      
      //3. 업무로직 : 사용자입력 아이디/비번이 DB에 저장된 아이디/비번과 일치 여부 판단
      //아이디로 admin객체를 조회
      //admin객체가 존재할경우
      //      a.비밀번호가 일치하는경우(로그인성공)
            
      //  로그인이 실패(b,c)
      //      b.비밀번호가 일치하지 않는경우
      //- c.admin객체가 존재하지 않을경우: 아이디 오류
      Admin admin = adminService.selectOne(adminId);
      
      System.out.println(admin);
      //로그인성공
      if(admin != null && adminPassword.equals(admin.getAdminPassword())) { // admin이 null이 아니고 입력패스워드가 db의 패스워드와 일치할 경우
    	 //세션에 id와 role을 저장해둠 => 로그아웃하기 전까지는 세션이 계속 유지되도록 하기 위함
         session.setAttribute("id", adminId);
         session.setAttribute("role", admin.getAdminRole());
         response.sendRedirect("memberList"); // 로그인 성공 후 회원목록조회 페이지로 이동
      }
      //로그인실패 : 아이디 존재X, 비번이 틀린경우
      else {
    	  PrintWriter out = response.getWriter();
    	  response.setContentType("text/html; charset=utf-8");
    	  out.println("<script language='javascript'>alert('Incorrect ID or Password. Try again.'); location.href='login'; </script>"); // 로그인에 실패하였다는 창을 띄우고 다시 로그인페이지로 이동
    	  out.flush();
      }
      
      //세션관련메소드(세션처리도 해줘야한다, 쿠키는 필요없다)
      
      
   }

}
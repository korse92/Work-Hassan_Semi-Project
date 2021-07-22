package admin.controller;

import java.io.IOException;
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
 * 관리자 생성
 * Servlet implementation class AdminLoginServlet
 * 
 * 
 */

//관리자 생성 페이지 매핑
@WebServlet(urlPatterns={"/admin/createAdmin"})
public class CreateAdminServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private AdminService adminService = new AdminService();
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.getRequestDispatcher("/WEB-INF/views/admin/createAdmin.jsp").forward(request, response);
   }
   

   /**
    * post작성 로그인 (admin-login.jsp 의 form에서 post요청했는지 확인. 로그인 강의 1분 14초)
    * post방식은 무조건 form메소드가 Post일때 submit하면  들어온다
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 //1. 인코딩처리(한글 깨짐 방지) 필수
      request.setCharacterEncoding("utf-8");
      //2. 사용자입력값 처리
      String adminId = request.getParameter("adminId");
      String adminEmail = request.getParameter("adminEmail");
      String adminPassword = request.getParameter("adminPassword");
      String repeatPassword = request.getParameter("repeatPassword");
      
      // admin객체를 입력값으로 세팅
      Admin admin = new Admin();
      admin.setAdminId(adminId);
      admin.setAdminEmail(adminEmail);
      admin.setAdminPassword(adminPassword);
      
      int result = adminService.insertOne(admin); // 관리자 정보 삽입
      
      if(result > 0) System.out.println("관리자 계정 생성 성공!");
      else System.out.println("관리자 계정 생성 실패!");
      
      response.sendRedirect("memberList"); // 삽입 후 로그인페이지로 이동
      
	      
	   }

}
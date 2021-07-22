package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.model.service.AdminService;
import admin.model.service.AdminMemberService;
import admin.model.vo.Admin;
import admin.model.vo.Member;
import common.util.MvcUtils;
//import member.model.vo.Member;


//회원목록 조회 페이지 매핑
@WebServlet(urlPatterns={"/admin/memberList"})
public class AdminMemberListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private AdminMemberService adminmemberService = new AdminMemberService();
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("utf-8");
	      
      List<Member> memberlist = adminmemberService.allMember();
      
      request.setAttribute("memberlist", memberlist);
      request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp").forward(request, response);
   }
   

   /**
    * post작성 로그인 (admin-login.jsp 의 form에서 post요청했는지 확인. 로그인 강의 1분 14초)
    * post방식은 무조건 form메소드가 Post일때 submit하면  들어온다
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String id = request.getParameter("memberId");
	   adminmemberService.deleteMember(id);

       response.sendRedirect("memberList");
	 
	      
   }
	      

}
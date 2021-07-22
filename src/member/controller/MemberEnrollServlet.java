package member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.MvcFileRenamePolicy;
import common.util.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String saveDirectory = getServletContext().getRealPath("/upload/profile"); 
		
		int maxPostSize = 10 * 1024 * 1024;
		String encoding = "utf-8";
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		MultipartRequest multipartReq = 
				new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		
		String memberId = multipartReq.getParameter("memberId");
		String memberPwd = MvcUtils.getEncryptedPassword(multipartReq.getParameter("password"));
		String memberName = multipartReq.getParameter("memberName");
		String birthday = multipartReq.getParameter("birthDay");
		String gender = multipartReq.getParameter("gender");
		String email = multipartReq.getParameter("email");
		String phone = multipartReq.getParameter("phone");
		String memberStatus = multipartReq.getParameter("memberStatus");
		String originalProfile = multipartReq.getOriginalFileName("upFile");
		String renamedProfile = multipartReq.getFilesystemName("upFile");
		
		Date birthDay_ = null;
		if(birthday != null && !"".equals(birthday))
			birthDay_ = Date.valueOf(birthday);
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPwd(memberPwd);
		member.setMemberName(memberName);
		member.setBirthday(birthDay_);
		member.setGender(gender);
		member.setEmail(email);
		member.setPhone(phone);
		member.setMemberStatus("A");
		member.setOriginalProfile(originalProfile);
		member.setRenamedProfile(renamedProfile);
			
		int result = memberService.memberInsert(member);
		
		System.out.println(member.toString());
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "회원가입 성공");
			//request.setAttribute("loc", request.getContextPath());			
			
			//RequestDispatcher reqDispatcher = request.getRequestDispatcher("/index.jsp");
			//reqDispatcher.forward(request, response);
		}else {				
			request.getSession().setAttribute("msg", "회원가입 실패");
			
			//RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp");
			//reqDispatcher.forward(request, response);
		}
		
		response.sendRedirect(request.getContextPath());
	}

}

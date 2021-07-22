package member.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.MvcFileRenamePolicy;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdate
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String saveDirectory = getServletContext().getRealPath("/upload/profile"); 
		
		int maxPostSize = 10 * 1024 * 1024;
		String encoding = "utf-8";
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		MultipartRequest multipartReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		
		//2. 전송값
		String memberPwd = multipartReq.getParameter("password");
		String memberName = multipartReq.getParameter("memberName");
		String birthday = multipartReq.getParameter("birthDay");
		String gender = multipartReq.getParameter("gender");		
		String email = multipartReq.getParameter("email");
		String phone = multipartReq.getParameter("phone");
		
		String originalProfile = multipartReq.getOriginalFileName("upFile");
		String renamedProfile = multipartReq.getFilesystemName("upFile");
		//파일삭제
		String delFile = multipartReq.getParameter("delFile");
		//기존첨부파일정보
		String oldOriginalProfile = multipartReq.getParameter("oldOriginalProfile");
		String oldRenamedProfile = multipartReq.getParameter("oldRenamedProfile");

		String memberId = multipartReq.getParameter("memberId");
		
		//기존파일이 있는 경우
		if(oldOriginalProfile != null) {
			//1.업로드한 파일 가져오기 | 2.기존파일제거
			File upFile = multipartReq.getFile("upFile");
			if(upFile != null || delFile != null) {
				//새파일 업로드 또는 기존파일 제거하는 경우
				File realDelFile = new File(saveDirectory, oldRenamedProfile);
				boolean bool = realDelFile.delete();
				System.out.println(delFile + " : " + (bool ? "기존 파일 삭제 성공!" : "기존 파일 삭제 실패!"));
			}
			else {
				//3.기존파일정보를 유지하는 경우
				originalProfile = oldOriginalProfile;
				renamedProfile= oldRenamedProfile;
			}
		}
		
		
		Date birthDay_ = null;
		if(birthday != null && !"".equals(birthday))
			birthDay_ = Date.valueOf(birthday);
		
		Member member = new Member();
		member.setMemberPwd(memberPwd);
		member.setMemberName(memberName);
		member.setBirthday(birthDay_);
		member.setGender(gender);
		member.setEmail(email);
		member.setPhone(phone);
		member.setOriginalProfile(originalProfile);
		member.setRenamedProfile(renamedProfile);
		member.setMemberId(memberId);
		
		int result = memberService.memberUpdate(member);
		
		String msg = null;
		String loc = request.getContextPath() + "/member/memberView?memberId=" + member.getMemberId();
		HttpSession session = request.getSession();
		if(result>0){
			msg = "성공적으로 회원정보를 수정했습니다.";
			
			//세션의 memberLoggedIn객체도 최신화
			session.setAttribute("memberLoggedIn", memberService.selectId(memberId));
		}
		else {
			msg = "회원정보수정에 실패했습니다.";	
		}
		
		session.setAttribute("msg", msg);
		
		
		response.sendRedirect(loc);
		
		
	}

}

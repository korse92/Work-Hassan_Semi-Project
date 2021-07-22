package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class CheckIdDuplicateServlet
 */
@WebServlet("/member/checkIdDuplicate")
public class CheckIdDuplicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. encoding
		request.setCharacterEncoding("utf-8");
		
		//2. 사용자 입력값
        String memberId = request.getParameter("memberId");
        System.out.println(memberId + " - memberId@servlet");
        
        //3. 비지니스 로직 : db를통해 중복값체크(이 아이디를 쓸수 있는지 아닌지 판단)
        Member member = memberService.selectId(memberId);
        //member가 null이면 아이디 사용 가능 null이 아닌면 사용 불가능
        boolean available = (member == null);
		
		//4. 처리결과 view단 전달(forwarding)
		request.setAttribute("available", available);
		//setAttribute(String name, Object value);이름이 name인 속성의 값을 value로 지정합니다.
		request.getRequestDispatcher("/WEB-INF/views/member/checkIdDuplicate.jsp")
		       .forward(request, response); //servlet이 jsp에 위임해주는게 forwarding
		
		
	}

}

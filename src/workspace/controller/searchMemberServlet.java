package workspace.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.manager.MemberManager;
import member.model.vo.Member;

/**
 * Servlet implementation class searchMemberServlet
 */
@WebServlet("/wrks/searchMember")
public class searchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 처리
		String searchId = request.getParameter("searchId");
		//System.out.println("searchId@searchMemberServlet = " + searchId);		
		
		//2. 업무로직
		List<Member> list = new ArrayList<Member>();
		
		MemberManager manager = MemberManager.getInstance();		
		List<Member> members = manager.getMembers();
		//System.out.println("members@searchMemberServlet = " + members);
		
		for(Member m : members) {
			if(m.getMemberId().contains(searchId)) {
				list.add(m);
			}
		}
		
		//System.out.println("list@searchMemberServlet = " + list);
		
		//3.응답에 작성
		response.setContentType("application/json; charset=utf-8");		
		new Gson().toJson(list, response.getWriter());

	}

}

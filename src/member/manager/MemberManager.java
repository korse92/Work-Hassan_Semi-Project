package member.manager;

import java.util.ArrayList;
import java.util.List;

import member.model.service.MemberService;
import member.model.vo.Member;

public class MemberManager {
	//단하나의 객체를 관리하기위한 static 필드
	private static MemberManager instance;
	private MemberService memberService = new MemberService();		
	
	private List<Member> members;	
	
	private MemberManager() {
		members = memberService.selectMembers();
	}
	
	public List<Member> getMembers(){
		return this.members;
	}	
	
	public static MemberManager getInstance() {
		if(instance == null)
			instance = new MemberManager();
		return instance;
	}

}

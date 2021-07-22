package admin.model.service;

import java.util.List;

import admin.model.dao.AdminMemberDao;
import admin.model.vo.*;

public class AdminMemberService {
//서비스는?여기부터
//멤버dao 이부분 변수도 헷갈리니 바꾸자
   private AdminMemberDao adminmemberDao = new AdminMemberDao();
   
   public List<Member> allMember() {
      return adminmemberDao.allMember();
   }
   
   public void deleteMember(String id) {
	   adminmemberDao.deleteMember(id);
   }
   
}
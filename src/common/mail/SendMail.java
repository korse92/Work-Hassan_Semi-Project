package common.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import workspace.model.vo.Workspace;

public class SendMail {
	
	//mail 서버 설정
	String host = "smtp.gmail.com";
	String user = "user@mail.com";
	String password = "비번바꿔야함";
	Properties props = new Properties();	
	
	public SendMail() {
		// SMTP 서버 정보를 설정한다.
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");		
	}
	
	public void sendWrksInviteMail(InternetAddress[] toEmails, Workspace workspace) {
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		// email 전송
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(user, "핫산"));
			
			msg.setRecipients(Message.RecipientType.TO, toEmails);
			
			String mailTitle = "[Work, Hassan!] " + workspace.getWorkspaceName() + " 워크스페이스에 초대합니다!";
			String mailContent = "초대링크 : http://localhost:9090/hassan/wrks/invite?workspaceInviteLink=" + workspace.getWorkspaceInviteLink();

			// 메일 제목
			msg.setSubject(mailTitle, "utf-8");
			// 메일 내용
			msg.setText(mailContent, "utf-8");			

			Transport.send(msg);
			System.out.println("워크스페이스 초대 이메일 전송 완료!");

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
	

}

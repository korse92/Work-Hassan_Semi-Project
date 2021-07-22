package chat.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import chat.model.service.ChatService;
import chat.model.vo.Chat;

// WebSocket의 호스트 주소 설정
@ServerEndpoint("/websocket")
public class ChatWebSocket {
	
	ChatService chatService = new ChatService();
	
	/**
	 * 컬렉션 프레임워크의 대부분의 클래스들은 싱글 스레드 환경에서 사용할 수 있도록 설계 되어서,
	 * 여러 스레드가 동시에 컬렉션에 접근한다면, 의도치않게 요소가 변경될 수 있는 불안전한 상태가 됨.
	 * 
	 * Vector와 Hashtable은 동기화된(synchronized) 메소드로 구성되어 있기 때문에
	 * 멀티스레드 환경에서 안전하게 요소 처리 가능.
	 * 
	 * ArrayList, HashSet, HashMap은 동기화된 메소드로
	 * 구성되어 있지 않아 멀티 스레드 환경에서 안전하지 않음.
	 * 
	 * 싱글 스레드 환경으로 설계된 컬렉션을 멀티스레드환경에서 안전하게 사용하기 위해서
	 * Collections.synchronizedSet() 메소드 사용.
	 */
	//클라이언트들의 세션을 담을 Set객체
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	//클라이언트들의 세션을 담을 Map객체 <K:세션객체, V:클라이언트의 채널ID>
	//private static Map<Session, Integer> clients = Collections.synchronizedMap(new HashMap<Session, Integer>());
	
	// WebSocket으로 브라우저가 접속하면 요청되는 함수
	@OnOpen
	public void OnOpen(Session session) {
		// 클라이언트가 연결되면 연결된 클라이언트의 세션을 Set에 저장
		System.out.println(session);
		clients.add(session);
	}

	// WebSocket으로 메시지가 오면 요청되는 함수
	@OnMessage
	public void OnMessage(String message, Session session) throws IOException {
		
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();
		
		//자바객체로 변환할때 예외확인해서 Chat타입이 아니면 채널id값 던져주는거라 판단해 세션과 채널 id 저장		
		Chat chat = gson.fromJson(message, Chat.class);
		
		//System.out.println("chat@ChatWebSocket = " + chat);		

		String profileImgName = chat.getRefFileRenamedName();
		chat.setRefFileRenamedName(null);
		int result = chatService.insertChat(chat);
		
		synchronized (clients) {
			for (Session client : clients) {
				if(result > 0) {
					chat.setRefFileRenamedName(profileImgName);
					String jsonStr = gson.toJson(chat);
					client.getBasicRemote().sendText(jsonStr);					
				} else {
					chat.setChatContent("에러로 인해 데이터베이스에 저장되지 않았습니다.\n고객센터에 문의 부탁드립니다.");
					String jsonStr = gson.toJson(chat);
					client.getBasicRemote().sendText(jsonStr);
				}
				//if (!client.equals(session)) {
				//}
				
			}
		}
		
	}

	// WebSocket과 브라우저가 접속이 끊기면 요청되는 함수
	@OnClose
	public void OnClose(Session session) {
		// 클라이언트의 연결이 종료되면, 해당 클라이언트의 세션을 Set에서 삭제
		clients.remove(session);
	}

	// WebSocket과 브라우저 간에 통신 에러가 발생하면 요청되는 함수.
	@OnError
	public void OnError(Throwable t) {
		// 콘솔에 에러를 표시한다.
		t.printStackTrace();
	}
}
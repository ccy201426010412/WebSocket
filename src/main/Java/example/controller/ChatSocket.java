package example.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint("/chatSocket")
public class ChatSocket {

	private  static  Set<ChatSocket>  sockets=new HashSet<ChatSocket>();
	
	private  static  List<String>   names=new ArrayList<String>();
	private  Session  session;
	private String username;
	
	@OnOpen
	public  void open(Session  session) throws UnsupportedEncodingException {
			this.session=session;
			sockets.add(this);
			String  queryString = session.getQueryString();
			String newName=new String(queryString.substring(queryString.indexOf("=")+1).getBytes(),"UTF-8");
			this.username = newName;
			names.add(this.username);

			Message   message=new Message();
			message.setUser("");
			message.setMessage(this.username+"进入聊天室！！");
			message.setAllUser(names);
			
			broadcast(sockets, message.toString() );
			
	}
	@OnMessage
	public  void receive(Session  session,String msg ){
		Message  message=new Message();
		message.setMessage(msg);
		message.setUser(this.username);
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss");
		String nowStr = now.format(formatter2);
		message.setDate(nowStr);
		System.out.println(nowStr);
		broadcast(sockets, message.toString());
	}
	
	@OnClose
	public  void close(Session session){
		sockets.remove(this);
		names.remove(this.username);
		
		Message   message=new Message();
		message.setMessage(this.username+"退出聊天室！！");
		message.setAllUser(names);
		
		broadcast(sockets, message.toString());
	}
	
	public void broadcast(Set<ChatSocket>  ss ,String msg ){
		
		for (Iterator iterator = ss.iterator(); iterator.hasNext();) {
			ChatSocket chatSocket = (ChatSocket) iterator.next();
			try {
				chatSocket.session.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}

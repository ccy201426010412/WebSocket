package example.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class Message {
	private static List<String> allUser;
	private  String  message;
	private String  user;
	private String  date;

	@Override
	public String toString() {
		JSONObject res = new JSONObject();
		res.append("message",message);
		res.append("date",date);
		res.append("user",user);
		res.append("allUser",new JSONArray(allUser) );
		return res.toString();
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<String> getAllUser() {
		return allUser;
	}

	public void setAllUser(List<String> allUser) {
		this.allUser = allUser;
	}
	
	
}

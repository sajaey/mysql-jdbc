package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessageObjects;

public class Project {
	public String InsertMessage(Connection connection,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String message = null;
		try{
			message = request.getParameter("message");
			PreparedStatement ps = connection.prepareStatement("INSERT INTO messages(message) VALUES(?)");
			ps.setString(1, message);
			int rs = ps.executeUpdate();
			if(rs > 0) {
				return message;
			}
			else {
				return null;
			}
		}
		catch(Exception e) {
			throw e;
		}
	}

public ArrayList GetMessages(Connection connection,HttpServletRequest request, HttpServletResponse response) throws Exception{
	ArrayList messageData = new ArrayList();
	try {
		PreparedStatement ps = connection.prepareStatement("SELECT msg_id,message FROM messages ORDER BY msg_id DESC");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			MessageObjects messageObject = new MessageObjects();
			messageObject.setMsg_id(rs.getString("msg_id"));
			messageObject.setMessage(rs.getString("message"));
			messageData.add(messageObject);
		}
		return messageData;
	}
	catch(Exception e) {
		throw e;
	}
}

public String DeleteMessage(Connection connection,HttpServletRequest request, HttpServletResponse response) throws Exception{
	String msg_id = null;
	try{
		msg_id = request.getParameter("msg_id");
		System.out.println("Message id"+msg_id);
		PreparedStatement ps = connection.prepareStatement("DELETE FROM messages WHERE msg_id = ?");
		ps.setString(1, msg_id);
		int rs = ps.executeUpdate();
		System.out.println("Execute update"+rs);
		if(rs > 0) {
			return "Successfully deleted"+ msg_id + "from database";
		}else {
			return null;
		}
	}
	catch(Exception e) {
		throw e;
	}
	}
}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import dao.Project;

public class ProjectManager {
	public String InsertMessage(Connection connection,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String message = null;
		try{
			message = request.getParameter("message");
				if(message != null && message != "" && message.length() > 0) {
					Project project = new Project();
					message = project.InsertMessage(connection, request, response);
				}
			}
			catch(Exception e) {
				throw e;
			}
			return message;
			}
	
	public ArrayList GetMessages(Connection connection,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ArrayList messages = null;
		try {
			Project project = new Project();
			messages = project.GetMessages(connection, request, response);
			
		}
		catch(Exception e) {
			throw e;
		}
		return messages;
	}
	
	public String DeleteMessage(Connection connection,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String msg_id = null;
		try {
			msg_id = request.getParameter("msg_id");
			if(msg_id != null && msg_id != "" && msg_id.length() > 0) {
				Project project = new Project();
				msg_id = project.DeleteMessage(connection, request, response);
			}
		}
		catch(Exception e) {
			throw e;
		}
		return msg_id;
	}
}

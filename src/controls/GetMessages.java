package controls;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.ArrayList;
import java.io.PrintWriter;
import com.google.gson.Gson;

import dao.MessageObjects;
import dao.Database;
import model.ProjectManager;

/**
 * Servlet implementation class GetMessages
 */
@WebServlet("/GetMessages")
public class GetMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMessages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Database database = new Database();
			ProjectManager projectManager = new ProjectManager();
			ArrayList messagesData = null;
			Connection connection = database.Get_Connection();
			messagesData = projectManager.GetMessages(connection, request, response);
			Gson gson = new Gson();
			
			String messages = gson.toJson(messagesData);
			System.out.print("helo"+"{\"Messages\":"+messages+"}");
			out.println("{\"Messages\":"+messages+"}");
		}
		catch(Exception e) {
			out.println("error" + e.getMessage());
		}
		finally {
			out.close();
		}
	}
}

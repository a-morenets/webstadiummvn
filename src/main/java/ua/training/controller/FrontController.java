package ua.training.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.training.controller.commands.Command;
import ua.training.controller.commands.GetCities;
import ua.training.controller.commands.GetCity;
import ua.training.controller.commands.GetTeams;
import ua.training.controller.commands.Login;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/rest/*")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Map<String , Command> commands = new HashMap<>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(){
   
    	commands.put("GET:/city", new GetCities() );
    	commands.put("GET:/city/", new GetCity() );
    	commands.put("GET:/team" , new GetTeams() );
    	commands.put("GET:/login",  new Login());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		processRequest(request , response);
	}
	
	void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod().toUpperCase();
		String path = request.getRequestURI();
		path = path.replaceAll(".*/rest", "").replaceAll("\\d+", "");
		String key = method+":"+path;
		Command command = commands.getOrDefault(key, (req , resp)->"/index.jsp" ); 
		String viewPage = command.execute(request, response);
		request.getRequestDispatcher(viewPage)
		       .forward(request, response);
		/*try(Writer out =  response.getWriter() ){
			out.write(method+":"+path);
		}*/
	}

}

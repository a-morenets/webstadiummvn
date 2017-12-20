package ua.training.controller;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import ua.training.model.dao.jdbc.JdbcCityDao;



/**
 * Servlet implementation class DbTestServlet
 */
public class DbTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/footbal")
	private DataSource dataSource;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try(Writer out = response.getWriter();
			Connection connection = dataSource.getConnection()){
			JdbcCityDao cityDao = new JdbcCityDao();
			cityDao.setConnection(connection);
			out.write(cityDao.findAll().toString());
			out.write("ok");
		}catch(SQLException ex){
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

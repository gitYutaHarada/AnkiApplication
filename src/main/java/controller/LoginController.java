package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import data_access_object.CreateUserDAO;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginController() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		
        CreateUserDAO create_dao = new CreateUserDAO();
        
        if(create_dao.isLogin(name, pass)) {
        	request.setAttribute("name", name);
        	request.setAttribute("pass", pass);
        	
    		RequestDispatcher requestdispatcher = request.getRequestDispatcher("myPage.jsp");
    		requestdispatcher.forward(request, response);
        }else {
        	String miss = "miss";
        	request.setAttribute("miss", miss);

        	RequestDispatcher requestdispatcher = request.getRequestDispatcher("login.jsp");
    		requestdispatcher.forward(request, response);
        }

		
		
		
	}

}

package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.FileOfData;
import bean.UserBean;

/**
 * Servlet implementation class AnkiTime
 */
@WebServlet("/AnkiTime")
public class AnkiTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnkiTime() {
        super();
        // TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		UserBean userbean = (UserBean) session.getAttribute("userbean");
		FileOfData fileofdata = (FileOfData) session.getAttribute("fileofdata");
		
		session.setAttribute("userbean", userbean);
		session.setAttribute("fileofdata", fileofdata);
		RequestDispatcher requestdipatcher = request.getRequestDispatcher("AnkiTime.jsp");
		requestdipatcher.forward(request, response);
		
	}

}

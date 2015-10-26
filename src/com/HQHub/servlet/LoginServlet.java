package com.HQHub.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import com.HQHub.DAO.UserDAO;
import com.HQHub.pojo.User;
import com.alibaba.fastjson.JSON;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username;
	private String phone;
	private String password;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();
		
		String type = request.getParameter("type");
		switch (type) {	

		case"login":
						
			phone = request.getParameter("phone");
			password = request.getParameter("password");
			
			User user = UserDAO.loginValidate(phone, password);

		    out.println(JSON.toJSON(user));
			break;
			
		case "register":
			
			username = request.getParameter("username");
			password = request.getParameter("password");
			phone = request.getParameter("phone");

			boolean accept = !UserDAO.registerValidate(phone);
			if (accept) {
				User u = new User(username, password, phone);
				boolean flag = UserDAO.Add(u);
				if (flag) {
					out.println("success");
				} else {
					out.println("fail");
				}
			} else {
				out.println("already");
			}
			break;
		
		}
				
		out.flush();
		out.close();
		
	}

}

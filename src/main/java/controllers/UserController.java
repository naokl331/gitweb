package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("user", new User());
		
		getServletContext().getRequestDispatcher("/jsp/userList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("param");
		System.out.println("押されたボタンのパラメーター" + param);
		
		//処理の分岐
		if(param.equals("1")) {
			search(request,response);
		}else if(param.equals("2")) {
			create(request, response);
		}
		
	}
	
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("検索ボタンが押されたときの動き");
		
		User user = new User();
		
		user.setId(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		
		//検索処理
		user.search();
		System.out.println(user.getClient().size());
		
		request.setAttribute("user", user);
		
		//画面再表示
		getServletContext().getRequestDispatcher("/jsp/userList.jsp").forward(request, response);
		
	}
	
	protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("新規作成ボタンが押されたときの動き");
	}

}

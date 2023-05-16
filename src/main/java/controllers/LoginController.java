package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Login;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Login login;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		login = new Login();
		request.setAttribute("login",login);
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Loginクラスのインスタンス生成
		login = new Login();
		
		login.setId(request.getParameter("id").toString()) ;
		login.setPass(request.getParameter("pass").toString()) ;
		
		if(!login.check()) {
			System.out.println("ログイン失敗");
			request.setAttribute("login", login );
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		//modelが取得したログインユーザー名をsessionに記憶する
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(0);
		session.setAttribute("name", login.getName());
		
		//ログイン後の処理
		//getServletContext().getRequestDispatcher("/menu").forward(request, response); //本来はviewを呼ぶもの
		response.sendRedirect("/git/menu");	//MenuControllerのdoGetへいく redirectはブラウザから受け取ったリクエストをそのまま別のサーブレットに受け渡す
		
	}

}

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
		
		//requestの日本語文字化け対策
		request.setCharacterEncoding("UTF-8");
		String param = request.getParameter("param");
		//System.out.println("押されたボタンのパラメーター" + param);
		
		//処理の分岐
		if(param.equals("1")) {
			search(request,response);
		}else if(param.equals("2")) {
			create(request, response);
		}else if(param.equals("3")) {	//新規登録の登録ボタン
			register(request, response);
		}else if(param.equals("4")) {	//登録確認画面の登録ボタン
			insert(request, response);
		}else if(param.equals("5")) {	//登録確認画面のキャンセルボタン
			createAgain(request, response);
		}else if(param.equals("6")) {
			
		}
		
	}
	
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("検索");
		User user = new User();
		user.setId(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		
		user.search();		//検索処理
		
		request.setAttribute("user", user);
		
		getServletContext().getRequestDispatcher("/jsp/userList.jsp").forward(request, response);
	}
	
	protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("ユーザマスタ一覧->新規作成ボタン押下");
		User user = new User();
		this.dispUserEdit4New(request, response, user);
	}
	
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("ユーザ登録画面->登録ボタン押下");
		User user = new User();
		
		//modelに画面入力値を持たせる
		user.setId(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		user.setPass(request.getParameter("pass"));
		user.setPass2(request.getParameter("pass2"));

		//新規登録チェック
		if(!user.check()) { 
			this.dispUserEdit4New(request, response, user);
			return;
		}
		
		//チェック完了後 登録確認画面を出したい
		request.setAttribute("user", user);
		getServletContext().getRequestDispatcher("/jsp/userEditConfirm.jsp").forward(request, response);
		
	}
	
	protected void createAgain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("ユーザー登録確認画面->キャンセルボタン押下");
		User user = new User();
		
		//modelに画面入力値を持たせる
		user.setId(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		user.setPass(request.getParameter("pass"));
		
		this.dispUserEdit4New(request, response, user);
	}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("ユーザーデータDBに格納");
		User user = new User();
		user.setId(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		user.setPass(request.getParameter("pass"));
		
		user.insert();		//DBにユーザー情報格納
		
		getServletContext().getRequestDispatcher("/jsp/userRegistComplete.jsp").forward(request, response);
	}
	
	protected void dispUserEdit4New(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
		user.setHeadline("ユーザ新規登録");
		user.setParam("3");
		
		request.setAttribute("user", user);
		
		getServletContext().getRequestDispatcher("/jsp/userEdit.jsp").forward(request, response);
	}
	
}

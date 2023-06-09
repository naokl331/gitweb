package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		
		//session内に、user4searchがあるかチェック
		if(session.getAttribute("user4search") == null) {
			request.setAttribute("user", new User());
		}else {
			request.setAttribute("user", (User)session.getAttribute("user4search"));
		}
		
		getServletContext().getRequestDispatcher("/jsp/userList.jsp").forward(request, response);
		session.removeAttribute("user4search");
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
			search(request,response);				// disp	userList
		}else if(param.equals("2")) {
			create(request, response);				//	disp	userEdit(new.ver)
		}else if(param.equals("3")) {
			register(request, response);			//	disp	userEditConfirm(after new)
		}else if(param.equals("4")) {
			insert(request, response);				// disp	userRegistComplete
		}else if(param.equals("5")) {
			createAgain(request, response);	// disp	userEdit(again)
		}else if(param.equals("6")) {
			edit(request, response);					//	disp	userEdit(edit.ver)
		}else if(param.equals("7")) {
			editCheck(request, response);		//	disp	userEditConfirm(afeter edit)
		}else if(param.equals("8")) {
			update(request, response);			//	disp	userRegistComplete
		}else if(param.equals("9")) {
			createAgain(request, response);	// disp	userEdit(edit.ver)
		}
		
	}
	
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setId(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		
		HttpSession session = request.getSession();
		session.setAttribute("user4search",user);
		
		user.search();		//検索処理
		request.setAttribute("user", user);
		getServletContext().getRequestDispatcher("/jsp/userList.jsp").forward(request, response);
	}
	
	protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		dispUserEdit4New(request, response, user);
	}
	
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		user.setParam("4");
		user.setCancel("5");
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
		
		if(request.getParameter("param").equals("5")) {
			dispUserEdit4New(request, response, user);
		}else {
			dispUserEdit4Edit(request, response, user);
		}
	}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("ユーザーデータDBに格納");
		User user = new User();
		user.setId(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		user.setPass(request.getParameter("pass"));
		
		user.insert();		//DBにユーザー情報格納
		
		request.setAttribute("dispFlg", "1");	//1 = 新規登録の完了画面
		getServletContext().getRequestDispatcher("/jsp/userRegistComplete.jsp").forward(request, response);
	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//modelのインスタンスを作る
		User user = new User();
		
		//編集ボタン押下->押した行のデータの"id"をuser(model)に記憶させる
		user.setId(request.getParameter("id"));
		
		//取得したidをキーに対象となるユーザーに情報を取得する
		user.getUser();
		
		dispUserEdit4Edit(request, response, user);
		}
	
	protected void editCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//modelのインスタンスを作る
		User user = new User();
		
		//画面の入力値をmodelに保持
		user.setId(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		user.setPass(request.getParameter("pass"));
		user.setPass2(request.getParameter("pass2"));
		
		//パスワード(確認用)が入力されていた場合の関連チェック
		if(!user.check4Edit()) {
			dispUserEdit4Edit(request, response, user);
			return;
		}
		
		//チェック完了後 登録確認画面を出したい
		user.setParam("8");
		user.setCancel("9");
		request.setAttribute("user", user);
		getServletContext().getRequestDispatcher("/jsp/userEditConfirm.jsp").forward(request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setId(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		user.setPass(request.getParameter("pass"));
		user.setPass2(request.getParameter("pass2"));
		
		user.update();
		
		request.setAttribute("dispFlg", "2");	//2 = 編集登録の完了画面
		getServletContext().getRequestDispatcher("/jsp/userRegistComplete.jsp").forward(request, response);
	}
	
	protected void dispUserEdit4New(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
		user.setHeadline("ユーザ新規登録");
		user.setParam("3");
		
		request.setAttribute("user", user);
		
		getServletContext().getRequestDispatcher("/jsp/userEdit.jsp").forward(request, response);
	}
	
	protected void dispUserEdit4Edit(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
		user.setReadonly("readonly");
		user.setHeadline("ユーザ編集");
		user.setParam("7");
		request.setAttribute("user", user);	
		getServletContext().getRequestDispatcher("/jsp/userEdit.jsp").forward(request, response);
	}
}

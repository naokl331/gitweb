package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Customer;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/customer")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/jsp/customerList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("server");
		response.setCharacterEncoding("UTF-8");
		
		//リクエストに乗ってきたデータをModelに格納する
		
		/*//Model Instance 
		Customer customer = new Customer();
		//リクエスト内のデータをmodelに記憶する
		customer.setTokuisaki(request.getParameter("tokuisaki"));	//jsから飛んできたデータを取得
		customer.setHurigana(request.getParameter("hurigana"));
		
		System.out.println(customer.getTokuisaki());
		System.out.println(customer.getHurigana());
		System.out.println(request.getParameter("param"));*/
		
		//処理の分岐
		String param = request.getParameter("param");
		
		if(param.equals("1")) {
			getPages(request,response);
		}else if(param.equals("2")) {
			getList(request, response);
		}
		
	}
	
	/*
	 * 　検索項目に従った検索結果を表示するために何ページ必要か計算し、JSに返す
	 * 　1ページに何件表示するかをあらかじめ決めておく（25件に設定）
	 */
	
	protected void getPages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Modelのインスタンスを作成
		Customer customer = new Customer();
		
		//JSから送られたデータをmodelに記憶する
		customer.setTokuisaki(request.getParameter("tokuisaki"));
		customer.setHurigana(request.getParameter("hurigana"));
		
		//ページ取得用メソッドの呼び出し
		response.getWriter().append(Integer.toString(customer.getPageCount()));
		
	}

	protected void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Modelのインスタンスを作成
		Customer customer = new Customer();
		
		//JSから送られたデータをmodelに記憶する
		customer.setTokuisaki(request.getParameter("tokuisaki"));
		customer.setHurigana(request.getParameter("hurigana"));
		customer.setPage(Integer.parseInt(request.getParameter("page")));
		//データ取得
		
	}
	
}

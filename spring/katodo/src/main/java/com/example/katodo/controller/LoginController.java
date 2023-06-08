package com.example.katodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.katodo.data.LoginData;
import com.example.katodo.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	//[@Autowired]使いたい場所で勝手にインスタンス化してくれる
	@Autowired LoginService ls;
	@Autowired HttpSession session;

	//------------------------------------------------
	//			  ログイン画面の初期表示
	//------------------------------------------------
	@GetMapping("/")	//"/"]はlocalhost:8080の後ろに何もないということ
	public String index(@ModelAttribute("ld") LoginData ld, Model model) {
		return "login";
	}
	
	//------------------------------------------------
	//				ログインボタン押下
	//------------------------------------------------
	@PostMapping("/login")	//"login"というpostrequestを受け取る
	public String login(@ModelAttribute("ld") LoginData ld, Model model) {	//@ModelAtrribute("名前") htmlで使う名前を設定できる
		
		if(!ls.check(ld)) {
			return "login";
 		}
		
		//持ち回り情報(name / year)をsessionに記憶
		session.setMaxInactiveInterval(0);
		session.setAttribute("name", ld.getName());
		session.setAttribute("year", ld.getYear());
		
		return "redirect:menu";
	}

}

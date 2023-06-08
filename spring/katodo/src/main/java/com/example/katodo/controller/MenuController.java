package com.example.katodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.katodo.data.MenuData;
import com.example.katodo.repositry.MenuRepository;


@Controller
public class MenuController {

	@Autowired
	MenuRepository mr;
	
	
	//------------------------------------------------
	//			  メニュー画面の初期表示
	//------------------------------------------------
	@GetMapping("/menu")
	public String index(@ModelAttribute("md") MenuData md , Model model) {
		mr.getMenuList(md);
		//System.out.println(model.toString());
		return "menu";
	}
	
	
	//------------------------------------------------
	//			  		ログアウト
	//------------------------------------------------
	@GetMapping("/logout")
	public String logout() {
		
		return "redirect:/";
	}
	
}

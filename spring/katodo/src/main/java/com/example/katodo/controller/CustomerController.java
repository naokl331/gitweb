package com.example.katodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.katodo.data.CustomerData;
import com.example.katodo.repositry.CustomerRepository;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerRepository cr;
	
	//------------------------------------------------
	//			  得意先画面の初期表示
	//------------------------------------------------
	@GetMapping("/customer")
	public String index() {
		return "customerList";	//htmlを投げる
	}
	
	
	//------------------------------------------------
	//			  得意先リストの表示(挿入)
	//------------------------------------------------
	@PostMapping("/getList4Customer")
	public String getList(@ModelAttribute("cd") CustomerData cd , Model model) {

		cr.getCustomerList(cd);
		
		return "customerTable";
	}
	
	
	//------------------------------------------------
	//			  得意先編集画面表示
	//------------------------------------------------
	@PostMapping("/editCustomer")
	public String editCustomer(@ModelAttribute("cd") CustomerData cd , Model model) {
		
		cr.getEditData(cd);
		
		return "editCustomer";
	}
	
	
	//------------------------------------------------
	//			  得意先新規登録画面表示
	//------------------------------------------------
	@PostMapping("/createNewCustomer")
	public String createNewCustomer(@ModelAttribute("cd") CustomerData cd , Model model) {
		
		//得意先Noの最大値+1をcdにセットしてHTMLに送らないとだめ
		cr.getCreateNo(cd);
		
		return "createNewCustomer";
	}
	
}

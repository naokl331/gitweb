package com.example.katodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.katodo.data.LoginData;
import com.example.katodo.repositry.LoginRepository;

@Service
public class LoginService {

	@Autowired
	LoginRepository lr;
	
	public boolean check(LoginData ld) {
		
		//必須チェック
		if(ld.getId().equals("") || ld.getPass().equals("")) {
			ld.setMsg("IDとパスワードは必須入力です。");
			return false;
		}
		//型チェック
		if(ld.getPass().length() < 6) {
			ld.setMsg("パスワードは６文字以上です。");
			return false;
		}
		
		//存在チェック
		if(!lr.existsCheck(ld)) {
			ld.setMsg("IDまたはパスワードに間違いがあります。");
			return false;
		}
		
		//年度取得
		lr.getYear(ld);
		return true;
	}
	
	
}

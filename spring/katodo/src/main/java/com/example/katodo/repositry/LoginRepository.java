package com.example.katodo.repositry;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.katodo.data.LoginData;

@Repository
public class LoginRepository {

	@Autowired
	JdbcTemplate jt;
	
	//存在チェック用メソッド
	public boolean existsCheck(LoginData ld) {
		String sql = "select name from m_user where id = ? and pass = ?";
		
		try {
			Map<String,Object> map = jt.queryForMap(sql,ld.getId(), ld.getPass());
			ld.setName(map.get("name").toString());
		}catch(Exception e){
			e.printStackTrace();	
			return false;
		}
		return true;
	}
	
	//年度取得
	public void getYear(LoginData ld) {
		Map<String,Object> map = jt.queryForMap("select nendo from saiban");
		ld.setYear(map.get("nendo").toString());
	}
}

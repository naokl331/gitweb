package models;

import java.sql.SQLException;

public class Login extends GetConnection {

	//フィールド
	private String id = "";
	private String pass = "";
	private String msg = "";
	private String name = "";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean check() {
		
		//必須チェック
		if(id.isEmpty() &&  pass.isEmpty())
		{
			msg = "IDとパスワードが未入力です";
			return false;
		}else if(id.isEmpty()) {
			msg = "IDが未入力です";
			return false;
		}else if(pass.isEmpty()){
			msg = "パスワードが未入力です";
			return false;
		}
		
		//型チェック
		if(pass.length() < 6) {
			msg = "パスワードが６文字未満です。";
			return false;
		}
		
		//存在チェック
		open();	//コネクションの実装
		try {
			//SQL
			String sql = "select name from m_user where id = ? and pass = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pass);
			
			rs = ps.executeQuery();
			
			if(!rs.next())
			{
				msg = "idとpassが一致しません。";
				close();
				return false;
			}
			
			name = rs.getString("name");
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		close();
		return true;
	}
	
	
	
}

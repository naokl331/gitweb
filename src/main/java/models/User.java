package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends GetConnection {

	//Field
	private String id = "";
	private String name = "";
	private List<Map<String, Object>> client = new ArrayList<Map<String,Object>>();
	private String pass="";
	private String pass2="";
	private String msg="";
	private String readonly ="";
	private String headline = "";
	private String param = "";
	
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getReadonly() {
		return readonly;
	}
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Map<String, Object>> getClient() {
		return client;
	}
	public void setClient(List<Map<String, Object>> client) {
		this.client = client;
	}
	
	public String getPass2() {
		return pass2;
	}
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
	
	
	
	//検索ボタン押下の機能用メソッド
	public void search() {
		open();
		
		int ptn = 0;
		
		//SQLの動的生成
		StringBuilder sql = new StringBuilder();
		
		sql.append("select id, name from m_user ");
		
		//id
		if(!id.equals("")) {
			sql.append("where id = ? ");
			ptn = 1;
		}
		
		//name
		if(!name.equals("")) {
			if(ptn == 1) {
				sql.append("and name like ?");
				ptn = 2;
			}else {
				sql.append("where name like ?");
				ptn = 3;
			}
		}
		
		try {
			ps = con.prepareStatement(sql.toString());
			
			switch(ptn) {
			case 1:
				ps.setString(1, id);
				break;
			case 2:
				ps.setString(1, id);
				ps.setString(2, "%" + name + "%");
				break;
			case 3:
				ps.setString(1, "%" + name + "%");
				break;
			}
			
			rs = ps.executeQuery();
			
			client = new ArrayList<Map<String,Object>>();
			
			while(rs.next()) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", rs.getString("id"));
				map.put("name", rs.getString("name"));		//selectで二つのカラムを取ってきているので二行で１データ分
				client.add(map);
			}
		
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
			close();
	}
	
	//登録チェック用メソッド
	public boolean check() {
		//型チェック
		if(pass.length() < 6) {
			msg = "パスワードが６文字以上です。";
			return false;
		}
		
		//関連チェック
		if(!pass.equals(pass2)) {
			msg = "確認用パスワードが違います。";
			return false;
		}
		
		//存在チェック
		open();	//connection Instance
			
		try {
			//SQL
			String sql = "select count(*) cnt from m_user where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			rs.next();
		
			if(rs.getInt("cnt") == 1)
			{
				msg = "このIDは既に使われています。";
				close();
				return false;
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
			
		close();
		return true;
	}
	
	//DBにユーザーを登録
	public void insert() {
		open();
		
		try {
			String sql = "insert into m_user values (?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
	        ps.setString(2, pass);
	        ps.setString(3, name);
			ps.execute();

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
			close();
	}
	
}

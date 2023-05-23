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
	private String param = "";		//登録ボタン用のparam
	private String cancel = "";		//キャンセルボタン用のparam
	
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
	
	public String getCancel() {
		return cancel;
	}
	public void setCancel(String cancel) {
		this.cancel = cancel;
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
	
	//ユーザ情報取得用メソッド
	public void getUser() {
		
		//DBに接続する
		open();
		
		//idをキーにその他の情報を取得する //完全なSQL文にすることでSQLインジェクションから守ることができる
		String sql = "select * from m_user where id = '" + id + "'"; // [']["] + id + ["][']["]
		//String sql = "select * from m_user where id = ?"; //プレースホルダーを使ってもいい　分かりやすい
		try {
			ps = con.prepareStatement(sql);
			//ps.setString(1,id);
			rs = ps.executeQuery();
			rs.next();
			
			name = rs.getString("name");
			pass = rs.getString("pass");
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		close();
	}
	
	//編集登録時のデータチェック
	public boolean check4Edit() {
		if(pass.length() < 6) {
			msg = "6文字以上必要です。";
			return false;
		}
		
		//passの値を変えてもpass2に何も入力しなければDB更新はかからないが確認画面へ遷移できるので少し気持ち悪い
		if(pass.length() >= 6) {
			
		}
		
		//パスワード(確認用)が入力されていた時の関連チェック
		if(pass2.length() > 0) {
			if(!pass.equals(pass2)) {
				msg = "パスワードとパスワード(確認用)が一致しません。";
				return false;
			}
		}
		return true;
	}
	
	//update用メソッド
	public void update() {
		String sql;
		open();
		//pass2にデータを持っているかどうかで、SQLを分ける -> パスワードを変更するということ
		if(!pass2.equals("")) {
			sql = "update m_user set name = ?, pass = ? where id = ?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, pass);
				ps.setString(3, id);
				ps.execute();
				
				
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}else {
			//パスワードの変更なし
			sql = "update m_user set name = ? where id = ?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, id);
				ps.execute();
				
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
		}
		close();
	}
	
	
}

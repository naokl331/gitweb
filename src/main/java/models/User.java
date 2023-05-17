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
				sql.append("and name = ?");
				ptn = 2;
			}else {
				sql.append("where name = ?");
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
				ps.setString(2, name);
				break;
			case 3:
				ps.setString(1, name);
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
	
	//新規作成ボタン押下の機能用メソッド
	public void create() {
		
	}
	
	//編集ボタン押下の機能用メソッド
	public void edit() {
		
	}
	
	
}

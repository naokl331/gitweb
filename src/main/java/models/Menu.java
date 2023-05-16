package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu extends GetConnection {

	//Field
	private String nendo;
	private List<Map<String,Object>> mastamente;
	private List<Map<String,Object>> business;
	private List<Map<String,Object>> system;

	public String getNendo() {
		return nendo;
	}

	public void setNendo(String nendo) {
		this.nendo = nendo;
	}
	
	public List<Map<String,Object>> getMastamente() {
		return mastamente;
	}

	public void setMastamente(List<Map<String,Object>> mastamente) {
		this.mastamente = mastamente;
	}

	public List<Map<String,Object>> getBusiness() {
		return business;
	}

	public void setBusiness(List<Map<String,Object>> business) {
		this.business = business;
	}

	public List<Map<String,Object>> getSystem() {
		return system;
	}

	public void setSystem(List<Map<String,Object>> system) {
		this.system = system;
	}
	
	
	//年度取得用のメソッド
	public void getNendoDB() {
		open(); //コネクションインスタンス作成
		
		//SQLの作成 ->DBからデータを抽出
		String sql = "select nendo from saiban";
		
		try {
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			rs.next();
			
			nendo = rs.getString("nendo").toString();
			
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		close();
		
	}

	//メニューデータ取得用メソッド
	public void getMenu() {
		open();
		
		
		mastamente = new ArrayList<Map<String,Object>>();	//マスタメンテ用
		business = new ArrayList<Map<String,Object>>();			//業務用
		system = new ArrayList<Map<String,Object>>();				//システム用
		
		try {
			String sql = "select menu_name, url from m_menu where category = '1' order by hyouji_jun";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> map= new HashMap<String,Object>();
				map.put("menu_name", rs.getString("menu_name"));
				map.put("url", rs.getString("url"));		//selectで二つのカラムを取ってきているので二行で１データ分
				mastamente.add(map);
			}
			sql = "select menu_name, url from m_menu where category = '2' order by hyouji_jun";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> map= new HashMap<String,Object>();
				map.put("menu_name", rs.getString("menu_name"));
				map.put("url", rs.getString("url"));		//selectで二つのカラムを取ってきているので二行で１データ分
				business.add(map);
			}
			sql = "select menu_name, url from m_menu where category = '3' order by hyouji_jun";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> map= new HashMap<String,Object>();
				map.put("menu_name", rs.getString("menu_name"));
				map.put("url", rs.getString("url"));		//selectで二つのカラムを取ってきているので二行で１データ分
				system.add(map);
			}
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		close();
		
	}
	
	
	
	
}

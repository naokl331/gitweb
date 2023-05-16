package models;

import java.sql.SQLException;

public class Menu extends GetConnection {

	//Field
	private String nendo;

	public String getNendo() {
		return nendo;
	}

	public void setNendo(String nendo) {
		this.nendo = nendo;
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
	
}

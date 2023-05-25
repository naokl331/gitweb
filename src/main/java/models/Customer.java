package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer extends GetConnection {
	//DB用
	private String tokuisaki_no = "";
	private String tokuisaki_name = "";
	private String hurigana = "";
	private String yubin_bng = "";
	private String address1 = "";
	private String address2 = "";
	private String tanto = "";
	private String shiyo_flg = "";
	
	//画面用
	private List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	private String tokuisaki = "";
	private int page = 0;			//List作成用ページ	現在paginationでどこのページを見ているか保持
	
	public String getTokuisaki_no() {
		return tokuisaki_no;
	}
	public void setTokuisaki_no(String tokuisaki_no) {
		this.tokuisaki_no = tokuisaki_no;
	}
	public String getTokuisaki_name() {
		return tokuisaki_name;
	}
	public void setTokuisaki_name(String tokuisaki_name) {
		this.tokuisaki_name = tokuisaki_name;
	}
	public String getHurigana() {
		return hurigana;
	}
	public void setHurigana(String hurigana) {
		this.hurigana = hurigana;
	}
	public String getYubin_bng() {
		return yubin_bng;
	}
	public void setYubin_bng(String yubin_bng) {
		this.yubin_bng = yubin_bng;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getTanto() {
		return tanto;
	}
	public void setTanto(String tanto) {
		this.tanto = tanto;
	}
	public String getShiyo_flg() {
		return shiyo_flg;
	}
	public void setShiyo_flg(String shiyo_flg) {
		this.shiyo_flg = shiyo_flg;
	}
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	public String getTokuisaki() {
		return tokuisaki;
	}
	public void setTokuisaki(String tokuisaki) {
		this.tokuisaki = tokuisaki;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	
	// ページ取得用メソッド
	// 返り値：ページ数 (int型)
	// 引数：なし
	public int getPageCount() {
		int page_cnt = 0;
		//DBへのConnectionの確立
		open();
		
		//SQL
		StringBuilder sql = new StringBuilder();
		StringBuilder where = new StringBuilder();
		where.append("");
		//基本となるSQL
		sql.append("select count(*) cnt from m02_tokuisaki ");
		//検索条件
		//得意先
			int ptn_flg = 0;
			if(tokuisaki.length() > 0) {
				try {
					Integer.parseInt(tokuisaki);		//失敗するとcatchへ行く
					//orとandがある場合、andが先に実行されてしまうルールがあるので、()をつけてorを先に実行するようにしている
					where.append("where (tokuisaki_no = ? or tokuisaki_name like ?) ");
					ptn_flg = 1;
				}catch(Exception e) {
					where.append("where tokuisaki_name like ? ");
					ptn_flg = 2;
				}
			}
			
		//フリガナ
		if(hurigana.length() > 0) {
			if(ptn_flg == 0) {				//フリガナonly
				where.append("where hurigana like ?");
				ptn_flg = 3;
			}else if(ptn_flg == 1) {	//得意先
				where.append("and hurigana like ?");
				ptn_flg = 4;
			}else if(ptn_flg == 2) {
				where.append("and hurigana like ?");
				ptn_flg = 5;
			}
		}
		
		//PrepareStatement
		try {
			ps = con.prepareStatement(sql.toString() + where.toString());
			//パラメータ
			switch(ptn_flg) {
				case 1:		//"select count(*) cnt from m02_tokuisaki where (tokuisaki_no = ? or tokuisaki_name like ?)"
					ps.setInt(1, Integer.parseInt(tokuisaki));
					ps.setString(2, "%" + tokuisaki + "%" );
					break;
				case 2:		//"select count(*) cnt from m02_tokuisaki where tokuisaki_name like ?"
					ps.setString(1, "%" + tokuisaki + "%");
					break;
				case 3:		//"select count(*) cnt from m02_tokuisaki where hurigana like ?"
					ps.setString(1, "%" + hurigana + "%");
					break;
				case 4:		//"select count(*) cnt from m02_tokuisaki where (tokuisaki_no = ? or tokuisaki_name like ?) and hurigana like ?"
					ps.setInt(1, Integer.parseInt(tokuisaki));
					ps.setString(2, "%" + tokuisaki + "%");
					ps.setString(3, "%" + hurigana + "%");
					break;
				case 5:		//"select count(*) cnt from m02_tokuisaki where tokuisaki_name like ? and hurigana like ?"
					ps.setString(1, "%" + tokuisaki + "%");
					ps.setString(2, "%" + hurigana + "%");
					break;
			}
			
			//実行
			rs = ps.executeQuery();
			rs.next();
			//行数の取得
			int record = rs.getInt("cnt");
			//ページ数の割り出し
			/*page_cnt = record / 25;
			if(record % 25 > 0) {
				page_cnt++;
			}*/
			double temp = (double)record / 25;
			page_cnt  = (int)Math.ceil(temp);		//Math.ceil(x) xの以上の最小の整数値を返す
			//System.out.println(temp);
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		close();
		
		return page_cnt;
	}

	public void getTable() {
		//DBへのConnectionの確立
		open();
		
		//SQL
		StringBuilder sql = new StringBuilder();
		StringBuilder where = new StringBuilder();
		where.append("");
		//基本となるSQL
		sql.append("select tokuisaki_no,tokuisaki_name,hurigana,shiyo_flg from m02_tokuisaki ");
		//検索条件
		//得意先
			int ptn_flg = 0;
			if(tokuisaki.length() > 0) {
				try {
					Integer.parseInt(tokuisaki);		//失敗するとcatchへ行く
					//orとandがある場合、andが先に実行されてしまうルールがあるので、()をつけてorを先に実行するようにしている
					where.append("where (tokuisaki_no = ? or tokuisaki_name like ?) ");
					ptn_flg = 1;
				}catch(Exception e) {
					where.append("where tokuisaki_name like ? ");
					ptn_flg = 2;
				}
			}
			
		//フリガナ
		if(hurigana.length() > 0) {
			if(ptn_flg == 0) {				//フリガナonly
				where.append("where hurigana like ?");
				ptn_flg = 3;
			}else if(ptn_flg == 1) {	//得意先
				where.append("and hurigana like ?");
				ptn_flg = 4;
			}else if(ptn_flg == 2) {
				where.append("and hurigana like ?");
				ptn_flg = 5;
			}
		}
		//SQL本体にwhere句を追加
		sql.append(where.toString());
		
		//Limitとoffsetを追加
		sql.append(" limit 25 offset ?");
		
		//ps
		try {
			ps = con.prepareStatement(sql.toString());
			
			//offset値に値を計算
			int offset = (page - 1) * 25;
			
			//パラメータ
			switch(ptn_flg) {
				case 1:		//"select count(*) cnt from m02_tokuisaki where (tokuisaki_no = ? or tokuisaki_name like ?)"
					ps.setInt(1, Integer.parseInt(tokuisaki));
					ps.setString(2, "%" + tokuisaki + "%" );
					ps.setInt(3, offset);
					break;
				case 2:		//"select count(*) cnt from m02_tokuisaki where tokuisaki_name like ?"
					ps.setString(1, "%" + tokuisaki + "%");
					ps.setInt(2, offset);
					break;
				case 3:		//"select count(*) cnt from m02_tokuisaki where hurigana like ?"
					ps.setString(1, "%" + hurigana + "%");
					ps.setInt(2, offset);
					break;
				case 4:		//"select count(*) cnt from m02_tokuisaki where (tokuisaki_no = ? or tokuisaki_name like ?) and hurigana like ?"
					ps.setInt(1, Integer.parseInt(tokuisaki));
					ps.setString(2, "%" + tokuisaki + "%");
					ps.setString(3, "%" + hurigana + "%");
					ps.setInt(4, offset);
					break;
				case 5:		//"select count(*) cnt from m02_tokuisaki where tokuisaki_name like ? and hurigana like ?"
					ps.setString(1, "%" + tokuisaki + "%");
					ps.setString(2, "%" + hurigana + "%");
					ps.setInt(3, offset);
					break;
				default:
					ps.setInt(1, offset);
			}
	
			//実行
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("tokuisaki_no", rs.getInt("tokuisaki_no"));
				map.put("tokuisaki_name", rs.getString("tokuisaki_name"));
				map.put("hurigana", rs.getString("hurigana"));
				map.put("shiyo_flg", rs.getInt("shiyo_flg"));
				list.add(map);
				}
			
		
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		close();
	}
	
}

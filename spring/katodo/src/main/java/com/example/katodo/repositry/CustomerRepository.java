package com.example.katodo.repositry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.katodo.data.CustomerData;

@Repository
public class CustomerRepository {

	@Autowired
	JdbcTemplate jt;
	
	//--------------------------------------------
	//	得意先一覧ページネーション用ページ数
	//--------------------------------------------
	public int getPageCount(CustomerData cd) {
		//SQLの動的生成と、パラメータのセット
		StringBuilder sql = new StringBuilder();
		StringBuilder where = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		
		//基本的なSQL
		sql.append("select count(*) cnt from m02_tokuisaki ");
		
		
		//得意先名
		if(!cd.getTokuisaki_name().equals("")) {
			where.append("where tokuisaki_name like ? ");
			param.add("%" + cd.getTokuisaki_name() + "%");
		}
		
		//ﾌﾘｶﾞﾅ
		if(!cd.getHurigana().equals("")) {
			if(where.length() == 0) {
				where.append("where hurigana like ? ");
			}else {
				where.append("and hurigana like ? ");
			}
			param.add("%" + cd.getHurigana() + "%");
		}
		
		//担当
		if(!cd.getTanto().equals("")) {
			if(where.length() == 0) {
				where.append("where tanto like ?");
			}else {
				where.append("and tanto like ?");
			}
			param.add("%" + cd.getTanto() + "%");
		}
		
		//SQLにWhere句を追加
		sql.append(where.toString());
		
		//実行
		Map<String,Object> map = jt.queryForMap(sql.toString(),param.toArray());
		int rec = Integer.parseInt(map.get("cnt").toString());
		int pageCnt = rec / 25;
		if(rec % 25 > 0) pageCnt++;
		
		return pageCnt;
		
	}
	
	
	//--------------------------------------------
	//		得意先一覧リストデータ取得
	//--------------------------------------------
	public void getCustomerList(CustomerData cd) {
		
		StringBuilder sql = new StringBuilder();
		StringBuilder where = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		
		//try {
		
			//共通SQL
			sql.append("select tokuisaki_no,tokuisaki_name,hurigana,tanto, ");
			
			sql.append("case when shiyo_flg = 1 then '可' ");
			sql.append("else '不可' end as shiyo_flg ");
			sql.append("from m02_tokuisaki ");
			
			//検索条件
			
			//得意先
			if(!cd.getTokuisaki_name().equals("")) {
				where.append("where tokuisaki_name like ? ");
				param.add("%" + cd.getTokuisaki_name() + "%");
			}
				
			//フリガナ
			if(!cd.getHurigana().equals("")) {
				if(where.length() == 0) {
					where.append("where hurigana like ? ");
				}else {
					where.append("and hurigana like ? ");
				}
				param.add("%" + cd.getHurigana() + "%");
			}
			
			//担当
			if(!cd.getTanto().equals("")) {
				if(where.length() == 0) {
					where.append("where tanto like ? ");
				}else {
					where.append("and tanto like ? ");
				}
				param.add("%" + cd.getTanto() + "%");
			}
			
			//共通SQLにwhere句を追加
			sql.append(where.toString());
			
			//offset割り出し
			int offset = (cd.getPage() - 1) * 25;
			
			//Limitとoffsetを追加
			sql.append("limit 25 offset ?");
			
			param.add(offset);
			
			//実行
			cd.setList(jt.queryForList(sql.toString(),param.toArray()));
				
		
			/*} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}*/
		
	}
	
	
	//--------------------------------------------
	//	得意先編集用データ取得(from tokuisaki_id)
	//--------------------------------------------
	public void getEditData(CustomerData cd) {
		
		//try {
		
			//共通SQL
			String sql = "select * from m02_tokuisaki where tokuisaki_no = ?";
			
			//実行
			Map<String,Object> map = jt.queryForMap(sql,Integer.parseInt(cd.getTokuisaki_no()) );
			
			//値セット
			cd.setTokuisaki_name(map.get("tokuisaki_name").toString());
			cd.setHurigana(map.get("hurigana").toString());
			cd.setYubin_bng(map.get("yubin_bng").toString());
			cd.setAddress1(map.get("address1").toString());
			cd.setAddress2(map.get("address2").toString());
			cd.setTanto(map.get("tanto").toString());
			cd.setShiyo_flg(map.get("shiyo_flg").toString());
			
			
		/*} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}*/
		
	}
	
	
	//--------------------------------------------
	//				得意先編集登録
	//--------------------------------------------
	public void updateCustomer(CustomerData cd) {
		
		StringBuilder sql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		
		sql.append("update m02_tokuisaki set ");
		
		sql.append("tokuisaki_name = ?,");
		param.add(cd.getTokuisaki_name());
		
		sql.append("hurigana = ?,");
		param.add(cd.getHurigana());
		
		sql.append("yubin_bng = ?,");
		param.add(cd.getYubin_bng());
		
		sql.append("address1 = ?,");
		param.add(cd.getAddress1());
		
		sql.append("address2 = ?,");
		param.add(cd.getAddress2());
		
		sql.append("tanto = ?,");
		param.add(cd.getTanto());
		
		sql.append("shiyo_flg = ? ");
		param.add(Integer.parseInt(cd.getShiyo_flg()));
		
		sql.append("where tokuisaki_no = ?");
		param.add(Integer.parseInt(cd.getTokuisaki_no()));
		
		jt.update(sql.toString(),param.toArray());
	}

	
	//--------------------------------------------
	//			得意先新規登録No取得
	//--------------------------------------------
	public void getCreateNo(CustomerData cd) {
		
		//現在の得意先NoのMax値を取得
		String sql = "select max(tokuisaki_no) max_no from m02_tokuisaki";
		
		int maxNo = jt.queryForObject(sql, Integer.class);
		maxNo++;

		cd.setTokuisaki_no(Integer.toString(maxNo));
		
		
	}
	
	
	//--------------------------------------------
	//			得意先新規登録
	//--------------------------------------------
	public void createNew(CustomerData cd) {
		
		System.out.println("createNew");
		
		StringBuilder sql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		
		//登録用SQL
		sql.append("insert into m02_tokuisaki values(?,?,?,?,?,?,?,?)");
		
		param.add(Integer.parseInt(cd.getTokuisaki_no()));
		param.add(cd.getTokuisaki_name());
		param.add(cd.getHurigana());
		param.add(cd.getYubin_bng());
		param.add(cd.getAddress1());
		param.add(cd.getAddress2());
		param.add(cd.getTanto());
		param.add(Integer.parseInt(cd.getShiyo_flg()));
		
		jt.update(sql.toString(), param.toArray());
		
	}
	

}

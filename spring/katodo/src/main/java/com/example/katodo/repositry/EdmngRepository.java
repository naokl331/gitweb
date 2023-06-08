package com.example.katodo.repositry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.katodo.data.CustomerData;
import com.example.katodo.data.EdmngData;

@Repository
public class EdmngRepository {

	@Autowired
	JdbcTemplate jt;
	
	//--------------------------------------------
	//	受注管理ページネーション用ページ数
	//--------------------------------------------
	public int getPageCount(EdmngData ed) {
		//SQLの動的生成と、パラメータのセット
		StringBuilder sql = new StringBuilder();
		StringBuilder where = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		
		//基本的なSQL
		/*sql.append("select ");
		sql.append("d01.kanri_no,");
		sql.append("m02.tokuisaki_name,");
		sql.append("d01.bukken_name,");
		sql.append("d01.tanto,");
		sql.append("d01.bumon,");
		sql.append("d01.koji_kanryo_date,");
		sql.append("d01.e_date,");
		sql.append("d01.d_date,");
		sql.append("d01.hachu_no ");*/
		sql.append("select count(*) cnt ");
		sql.append("from ");
		sql.append("d01_eddb as d01 ");	//d01_eddbをd01と命名し
		sql.append("inner join m02_tokuisaki as m02 "); //d01とm02と命名したm02_tokuisakiを内部結合したく
		sql.append("on d01.tokuisaki_no = m02.tokuisaki_no "); //それぞれのtokuisaki_noで結合する
		
		//得意先
		if(ed.getTokuisaki_name().length() > 0) {
			where.append("where m02.tokuisaki_name like ? ");
			param.add("%" + ed.getTokuisaki_name() + "%");
		}
		
		//物件
		if(ed.getProperty().length() > 0) {
			where.append("where d01.bukken_name like ? ");
			param.add("%" + ed.getProperty() + "%");
		}
		
		//担当
		if(ed.getTanto().length() > 0) {
			where.append("where d01.tanto like ? ");
			param.add("%" + ed.getTanto() + "%");
		}
		
		//部門
		if(ed.getDept().length() > 0) {
			where.append("where d01.bumon like ? ");
			param.add("%" + ed.getDept() + "%");
		}
		
		//工事完了日(const_bgn)
		if(ed.getConst_bgn().length() > 0) {
			if(where.length() == 0) {
				where.append("where ");
			}else {
				where.append("and ");
			}
			where.append("d01.koji_kanryo_date >= ? ");	//フォームに入力されたdateは2023-06-08みたいな文字列として取得される で文字列ごとで手前からどちらの数字文字列(文字コード)が大きいかを見ていき判定している
			param.add(ed.getConst_bgn());
		}
		
		//工事完了日(const_end)
		if(ed.getConst_end().length() > 0) {
			if(where.length() == 0) {
				where.append("where ");
			}else {
				where.append("and ");
			}
			where.append("d01.koji_kanryo_date <= ? ");	
			param.add(ed.getConst_end());
		}
		
		//見積書作成日(est_bgn)
		if(ed.getEst_bgn().length() > 0) {
			if(where.length() == 0) {
				where.append("where ");
			}else {
				where.append("and ");
			}
			where.append("d01.e_date >= ? ");	
			param.add(ed.getEst_bgn());
		}
		
		//見積書作成日(est_end)
		if(ed.getEst_end().length() > 0) {
			if(where.length() == 0) {
				where.append("where ");
			}else {
				where.append("and ");
			}
			where.append("d01.e_date <= ? ");	
			param.add(ed.getEst_end());
		}

		//納品書作成日(del_bgn)
		if(ed.getDel_bgn().length() > 0) {
			if(where.length() == 0) {
				where.append("where ");
			}else {
				where.append("and ");
			}
			where.append("d01.d_date >= ? ");	
			param.add(ed.getDel_bgn());
		}
		
		//納品書作成日(del_end)
		if(ed.getDel_end().length() > 0) {
			if(where.length() == 0) {
				where.append("where ");
			}else {
				where.append("and ");
			}
			where.append("d01.d_date <= ? ");	
			param.add(ed.getDel_end());
		}
		
		//発注番号(po_num1)
		if(ed.getPo_num1().length() > 0) {
			if(where.length() == 0) {
				where.append("where ");
			}else {
				where.append("and ");
			}
			where.append("d01.hachu_no >= ? ");	
			param.add(ed.getPo_num1());
		}
		
		//発注番号(po_num1)
		if(ed.getPo_num2().length() > 0) {
			if(where.length() == 0) {
				where.append("where ");
			}else {
				where.append("and ");
			}
			where.append("d01.hachu_no <= ? ");	
			param.add(ed.getPo_num2());
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
	//	受注管理リスト表示用データ取得
	//--------------------------------------------
	public void getEdmngList(CustomerData cd) {
		
		StringBuilder sql = new StringBuilder();
		StringBuilder where = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		
		//基本的なSQL
		sql.append("select ");
		sql.append("d01.kanri_no,");
		sql.append("m02.tokuisaki_name,");
		sql.append("d01.bukken_name,");
		sql.append("d01.tanto,");
		sql.append("d01.bumon,");
		sql.append("d01.koji_kanryo_date,");
		sql.append("d01.e_date,");
		sql.append("d01.d_date,");
		sql.append("d01.hachu_no ");
		sql.append("from ");
		sql.append("d01_eddb as d01 ");	//d01_eddbをd01と命名し
		sql.append("inner join m02_tokuisaki as m02 "); //d01とm02と命名したm02_tokuisakiを内部結合したく
		sql.append("on d01.tokuisaki_no = m02.tokuisaki_no "); //それぞれのtokuisaki_noで結合する
		
		
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

	
	}
}

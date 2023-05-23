package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Customer extends GetConnection {
	//フィールド
	private String tokuisaki_no = "";
	private String tokuisaki_name = "";
	private String hurigana = "";
	private String yubin_bng = "";
	private String address1 = "";
	private String address2 = "";
	private String tanto = "";
	private String shiyo_flg = "";
	private List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	
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
	
}

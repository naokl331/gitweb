package com.example.katodo.data;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class CustomerData {
	
	//DB用
	private String tokuisaki_no		= "";
	private String tokuisaki_name	= "";
	private String hurigana			= "";
	private String yubin_bng		= "";
	private String address1			= "";
	private String address2			= "";
	private String tanto			= "";
	private String shiyo_flg		= "";
	
	//画面用
	List<Map<String,Object>> list;
	private int page;
}

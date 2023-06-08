package com.example.katodo.data;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class MenuData {
	private List<Map<String,Object>> ls_mastamente;
	private List<Map<String,Object>> ls_business;
	private List<Map<String,Object>> ls_system;
}

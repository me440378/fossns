package com.fossns.controller;

import com.alibaba.fastjson.JSONObject;
import com.fossns.service.ReportService;
import com.jfinal.core.Controller;

public class ReportController extends Controller{

	private ReportService ReportService;
	public ReportController() {
		ReportService = new ReportService();
	}
	
	public void create() {
		int customer_id = getParaToInt("customer_id");
		int type = getParaToInt("type");
		int target_id = getParaToInt("target_id");
		String content = getPara("content");
		
		JSONObject jo = ReportService.createReport(customer_id,type,target_id,content);
		renderJson(jo);
	}
}

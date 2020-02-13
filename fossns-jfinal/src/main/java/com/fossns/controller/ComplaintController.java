package com.fossns.controller;

import com.alibaba.fastjson.JSONObject;
import com.fossns.service.ComplaintService;
import com.jfinal.core.Controller;

public class ComplaintController extends Controller{

	private ComplaintService ComplaintService;
	public ComplaintController() {
		ComplaintService = new ComplaintService();
	}
	
	public void create() {
		String content = getPara("content");
		int customer_id = getParaToInt("customer_id");
		JSONObject jo = ComplaintService.createComplaint(content,customer_id);
		
		renderJson(jo);
	}
}

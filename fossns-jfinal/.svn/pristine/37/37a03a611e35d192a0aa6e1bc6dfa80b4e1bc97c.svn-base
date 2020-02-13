package com.fossns.controller;

import com.alibaba.fastjson.JSONObject;
import com.fossns.service.DynamicService;
import com.jfinal.core.Controller;

public class DynamicController extends Controller{

	private DynamicService DynamicService;
	public DynamicController() {
		DynamicService = new DynamicService();
	}
	
	public void create() {
		int customer_id = getParaToInt("customer_id");
		String content = getPara("content");
		String image = getPara("image")!=null ? getPara("image") : null;
		
		JSONObject jo = DynamicService.createDynamic(customer_id,content,image);
		renderJson(jo);
		
	}
}

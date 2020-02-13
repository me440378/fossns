package com.fossns.controller;

import com.jfinal.core.Controller;
import com.alibaba.fastjson.JSONObject;
import com.fossns.service.HomeService;

public class HomeController extends Controller{
	
	private HomeService HomeService;
	public HomeController() {
		HomeService = new HomeService();
	}
	
	public void all() {
		JSONObject jo = HomeService.getAll();
		renderJson(jo);
	}
}

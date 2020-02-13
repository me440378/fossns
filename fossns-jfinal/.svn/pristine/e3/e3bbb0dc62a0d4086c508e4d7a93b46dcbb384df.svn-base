package com.fossns.controller;

import com.alibaba.fastjson.JSONObject;
import com.fossns.service.BaseService;
import com.jfinal.core.Controller;

public class BaseController extends Controller{
	
	private BaseService BaseService;
	public BaseController() {
		BaseService = new BaseService();
	}
	/**
	 * index
	 */
	public void index() {
		renderText("hello fossns");
	}
	
	public void login() {
		String username = getPara("username");
		String password = getPara("password");
		JSONObject jo = BaseService.loginCustomer(username,password);
		renderJson(jo);
	}
	
	public void register() {
		String username = getPara("username");
		String password = getPara("password");
		JSONObject jo = BaseService.registerCustomer(username,password);
		renderJson(jo);
	}
}

package com.fossns.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.fossns.service.admin.BaseService;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class BaseController extends Controller{

	private BaseService BaseService;
	public BaseController() {
		BaseService = new BaseService();
	}
	
	@ActionKey("/admin/basic/all")
	public void all() {
		JSONObject jo = BaseService.getAll();
		renderJson(jo);
	}
	
	public void login() {
		String username = getPara("username");
		String password = getPara("password");
		JSONObject jo = BaseService.loginUser(username,password);
		renderJson(jo);
	}
	
}

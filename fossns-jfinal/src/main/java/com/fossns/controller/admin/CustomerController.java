package com.fossns.controller.admin;

import com.jfinal.core.Controller;
import com.alibaba.fastjson.JSONObject;
import com.fossns.service.admin.CustomerService;

public class CustomerController extends Controller{

	private CustomerService CustomerService;
	public CustomerController() {
		CustomerService = new CustomerService();
	}
	
	public void search() {
		int length = getParaToInt("length");
		int offset = getParaToInt("offset");
		String name = (getPara("name") != null) ? getPara("name") : "";
		String nickname = (getPara("nickname") != null) ? getPara("nickname") : "";
		
		JSONObject jo = CustomerService.searchCustomer(length,offset,name,nickname);
		renderJson(jo);
	}
}

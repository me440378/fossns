package com.fossns.controller;

import com.alibaba.fastjson.JSONObject;
import com.fossns.service.CustomerService;
import com.jfinal.core.Controller;

public class CustomerController extends Controller{

	private CustomerService CustomerService;
	public CustomerController() {
		CustomerService = new CustomerService();
	}
	
	public void update() {
		int customer_id = getParaToInt("customer_id");
		String nickname = getPara("nickname");
		
		JSONObject jo = CustomerService.updateCostomer(customer_id,nickname);
		renderJson(jo);
	}
	
	public void changepswd() {
		int customer_id = getParaToInt("customer_id");
		String oldpswd = getPara("oldpswd");
		String newpswd = getPara("newpswd");
		
		JSONObject jo = CustomerService.changePassword(customer_id,oldpswd,newpswd);
		renderJson(jo);
	}
	
	public void select() {
		String content = getPara("content");
		
		JSONObject jo = CustomerService.selectCustomer(content);
		renderJson(jo);
	}
	
	public void get() {
		int customer_id = getParaToInt("customer_id");
		
		JSONObject jo = CustomerService.getCustomer(customer_id);
		renderJson(jo);
	}
}

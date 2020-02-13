package com.fossns.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.fossns.service.admin.JoinGroupService;

public class JoinGroupController extends Controller{

	private JoinGroupService JoinGroupService;
	public JoinGroupController() {
		JoinGroupService = new JoinGroupService();
	}
	
	public void search() {
		int length = getParaToInt("length");
		int offset = getParaToInt("offset");
		String customer_name = (getPara("customer_name") != null) ? getPara("customer_name") : "";
		String group_name = (getPara("group_name") != null) ? getPara("group_name") : "";
		int is_checking = (getParaToInt("is_checking") != null) ? getParaToInt("is_checking") : 2;
		
		JSONObject jo = JoinGroupService.searchJoinGroup(length,offset,customer_name,group_name,is_checking);
		renderJson(jo);
	}
	
	public void check() {
		int apply_id = getParaToInt("apply_id");
		int pass = getParaToInt("pass");
		int customer_id = getParaToInt("customer_id");
		
		JSONObject jo = JoinGroupService.checkJoinGroup(apply_id,pass,customer_id);
		renderJson(jo);
	}
}

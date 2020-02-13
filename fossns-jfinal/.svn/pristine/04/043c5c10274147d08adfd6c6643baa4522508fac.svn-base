package com.fossns.controller.admin;

import com.jfinal.core.Controller;
import com.alibaba.fastjson.JSONObject;
import com.fossns.service.admin.CreateGroupService;

public class CreateGroupController extends Controller{
	
	private CreateGroupService CreateGroupService;
	public CreateGroupController() {
		CreateGroupService = new CreateGroupService();
	}
	
	public void search() {
		int length = getParaToInt("length");
		int offset = getParaToInt("offset");
		String creator_name = (getPara("creator_name") != null) ? getPara("creator_name") : "";
		String group_name = (getPara("group_name") != null) ? getPara("group_name") : "";
		String startCreated_at = (getPara("startCreated_at") != null) ? getPara("startCreated_at") : "";
		String endCreated_at = (getPara("endCreated_at") != null) ? getPara("endCreated_at") : "";
		
		JSONObject jo = CreateGroupService.searchCreateGroup(length,offset,creator_name,group_name,startCreated_at,endCreated_at);
		renderJson(jo);
	}
	
	public void check() {
		int group_id = getParaToInt("group_id");
		int pass = getParaToInt("pass");
		int customer_id = getParaToInt("customer_id");
		
		JSONObject jo = CreateGroupService.checkCreateGroup(group_id,pass,customer_id);
		renderJson(jo);
	}
}

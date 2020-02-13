package com.fossns.controller.admin;

import com.jfinal.core.Controller;
import com.alibaba.fastjson.JSONObject;
import com.fossns.service.admin.GroupService;

public class GroupController extends Controller{
	
	private GroupService GroupService;
	public GroupController() {
		GroupService = new GroupService();
	}
	
	public void search() {
		int length = getParaToInt("length");
		int offset = getParaToInt("offset");
		String group_name = (getPara("group_name") != null) ? getPara("group_name") : "";
		String startCreated_at = (getPara("startCreated_at") != null) ? getPara("startCreated_at") : "";
		String endCreated_at = (getPara("endCreated_at") != null) ? getPara("endCreated_at") : "";
		
		JSONObject jo = GroupService.searchGroup(length,offset,group_name,startCreated_at,endCreated_at);
		renderJson(jo);
	}
}

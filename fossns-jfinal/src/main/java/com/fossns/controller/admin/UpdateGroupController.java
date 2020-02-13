package com.fossns.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.fossns.service.admin.UpdateGroupService;
import com.jfinal.core.Controller;

public class UpdateGroupController extends Controller{

	private UpdateGroupService UpdateGroupService;
	public UpdateGroupController() {
		UpdateGroupService = new UpdateGroupService();
	}
	
	public void search() {
		int length = getParaToInt("length");
		int offset = getParaToInt("offset");
		String creator_name = (getPara("creator_name") != null) ? getPara("creator_name") : "";
		String group_name = (getPara("group_name") != null) ? getPara("group_name") : "";
		String startUpdate_at = (getPara("startUpdate_at") != null) ? getPara("startUpdate_at") : "";
		String endUpdate_at = (getPara("endUpdate_at") != null) ? getPara("endUpdate_at") : "";
		
		JSONObject jo = UpdateGroupService.searchUpdateGroup(length,offset,creator_name,group_name,startUpdate_at,endUpdate_at);
		renderJson(jo);
	}
	
	public void check() {
		int group_id = getParaToInt("group_id");
		int pass = getParaToInt("pass");
		int customer_id = getParaToInt("customer_id");
		
		JSONObject jo = UpdateGroupService.checkUpdateGroup(group_id,pass,customer_id);
		renderJson(jo);
	}
}

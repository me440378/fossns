package com.fossns.controller;

import com.jfinal.core.*;
import com.alibaba.fastjson.JSONObject;
import com.fossns.service.GroupService;

public class GroupController extends Controller{
	
	private GroupService GroupService;
	public GroupController() {
		GroupService = new GroupService();
	}
	
	public void update() {
		int group_id = getParaToInt("group_id");
		String summary_new = getPara("summary_new");
		
		JSONObject jo = GroupService.updateGroup(group_id,summary_new);
		renderJson(jo);
	}
	
	public void join() {
		int customer_id = getParaToInt("customer_id");
		int group_id = getParaToInt("group_id");
		
		JSONObject jo = GroupService.joinGroup(customer_id,group_id);
		renderJson(jo);
	}
	
	public void all() {
		int customer_id = getParaToInt("customer_id");
		int group_id = getParaToInt("group_id");
		
		JSONObject jo = GroupService.getAll(customer_id,group_id);
		renderJson(jo);
	}
	
	@ActionKey("/grouplist/all")
	public void grouplistAll() {
		JSONObject jo = GroupService.getGroupListAll();
		renderJson(jo);
	}
	
	public void create() {
		int customer_id = getParaToInt("customer_id");
		String name = getPara("name");
		String image = getPara("image") != null ? getPara("image"):null;
		String summary = getPara("summary");
		
		JSONObject jo = GroupService.createGroup(customer_id,name,summary,image);
		renderJson(jo);
	}
	
	public void get() {
		int group_id = getParaToInt("group_id");
		
		JSONObject jo = GroupService.getGroup(group_id);
		renderJson(jo);
	}
	
	public void select() {
		String content = getPara("content");
		
		JSONObject jo = GroupService.selectGroup(content);
		renderJson(jo);
	}
}

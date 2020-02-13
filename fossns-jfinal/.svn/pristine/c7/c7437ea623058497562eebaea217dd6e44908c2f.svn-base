package com.fossns.controller;

import com.alibaba.fastjson.JSONObject;
import com.fossns.service.FriendService;
import com.jfinal.core.Controller;

public class FriendController extends Controller{

	private FriendService FriendService;
	public FriendController() {
		FriendService = new FriendService();
	}
	
	public void all() {
		int customer_id = getParaToInt("customer_id");
		
		JSONObject jo = FriendService.getAll(customer_id);
		renderJson(jo);
	}
	
	public void delete() {
		int customer_id = getParaToInt("customer_id");
		int friend_id = getParaToInt("friend_id");
		
		JSONObject jo = FriendService.deleteFriend(customer_id,friend_id);
		renderJson(jo);
	}
	
	public void agree() {
		int customer_id = getParaToInt("customer_id");
		int friend_id = getParaToInt("friend_id");
		int apply_id = getParaToInt("apply_id");
		
		JSONObject jo = FriendService.agreeApply(customer_id,friend_id,apply_id);
		renderJson(jo);
	}
	
	public void reject() {
		int apply_id = getParaToInt("apply_id");
		
		JSONObject jo = FriendService.deleteApply(apply_id);
		renderJson(jo);
	}
	
	public void add() {
		int customer_id = getParaToInt("customer_id");
		int friend_id = getParaToInt("friend_id");
		
		JSONObject jo = FriendService.createApply(customer_id,friend_id);
		renderJson(jo);
	}
}

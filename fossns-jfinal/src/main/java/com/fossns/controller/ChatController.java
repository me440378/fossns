package com.fossns.controller;

import com.jfinal.core.Controller;
import com.alibaba.fastjson.JSONObject;
import com.fossns.service.ChatService;

public class ChatController extends Controller{

	private ChatService ChatService;
	public ChatController() {
		ChatService = new ChatService();
	}
	
	public void all() {
		int customer_id = getParaToInt("customer_id");
		int friend_id = getParaToInt("friend_id");
		
		JSONObject jo = ChatService.getAll(customer_id,friend_id);
		renderJson(jo);
	}
	
	public void create() {
		int sender_id = getParaToInt("sender_id");
		int receiver_id = getParaToInt("receiver_id");
		String content = getPara("content");
		int type = getParaToInt("type");
		
		JSONObject jo = ChatService.createChatDetail(sender_id,receiver_id,content,type);
		renderJson(jo);
	}
}

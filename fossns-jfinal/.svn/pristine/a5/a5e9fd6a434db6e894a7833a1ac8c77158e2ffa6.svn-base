package com.fossns.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.fossns.service.admin.TopicService;

public class TopicController extends Controller{

	private TopicService TopicService;
	public TopicController() {
		TopicService = new TopicService();
	}
	
	public void search() {
		int length = getParaToInt("length");
		int offset = getParaToInt("offset");
		String group_name = (getPara("group_name") != null) ? getPara("group_name") : "";
		String topic_name = (getPara("topic_name") != null) ? getPara("topic_name") : "";
		String creator_name = (getPara("creator_name") != null) ? getPara("creator_name") : "";
		String startCreated_at = (getPara("startCreated_at") != null) ? getPara("startCreated_at") : "";
		String endCreated_at = (getPara("endCreated_at") != null) ? getPara("endCreated_at") : "";
		
		JSONObject jo = TopicService.searchTopic(length,offset,group_name,topic_name,creator_name,startCreated_at,endCreated_at);
		renderJson(jo);
	}
}

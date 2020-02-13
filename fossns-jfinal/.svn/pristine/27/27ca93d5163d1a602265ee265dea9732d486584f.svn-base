package com.fossns.controller;

import com.alibaba.fastjson.JSONObject;
import com.fossns.service.TopicService;
import com.jfinal.core.*;

public class TopicController extends Controller{

	private TopicService TopicService;
	public TopicController() {
		TopicService = new TopicService();
	}
	
	public void create() {
		int customer_id = getParaToInt("customer_id");
		String name = getPara("name");
		int group_id = getParaToInt("group_id");
		
		JSONObject jo = TopicService.createTopic(customer_id,name,group_id);
		renderJson(jo);
	}
	
	public void select() {
		String content = getPara("content");
		
		JSONObject jo = TopicService.selectTopic(content);
		renderJson(jo);
	}
	
	public void all() {
		int topic_id = getParaToInt("topic_id");
		int length = getParaToInt("length");
		int offset = getParaToInt("offset");
		
		JSONObject jo = TopicService.getAll(topic_id,offset,length);
		renderJson(jo);
	}
	
	@ActionKey("/topicdetail/create")
	public void topicdetailCreate() {
		int customer_id = getParaToInt("customer_id");
		int topic_id = getParaToInt("topic_id");
		String content = getPara("content");
		String image = (getPara("image") != null) ? getPara("image") : null;
	
		JSONObject jo = TopicService.createTopicDetail(customer_id,topic_id,content,image);
		renderJson(jo);
	}
	
	@ActionKey("/topiclist/all")
	public void topiclistAll() {
		
		JSONObject jo = TopicService.getTopicListAll();
		renderJson(jo);
	}
}

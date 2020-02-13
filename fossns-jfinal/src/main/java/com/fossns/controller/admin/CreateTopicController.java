package com.fossns.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.fossns.service.admin.CreateTopicService;
import com.jfinal.core.Controller;

public class CreateTopicController extends Controller{

	private CreateTopicService CreateTopicService;
	public CreateTopicController() {
		CreateTopicService = new CreateTopicService();
	}
	
	public void search() {
		int length = getParaToInt("length");
		int offset = getParaToInt("offset");
		String creator_name = (getPara("creator_name") != null) ? getPara("creator_name") : "";
		String topic_name = (getPara("topic_name") != null) ? getPara("topic_name") : "";
		String group_name = (getPara("group_name") != null) ? getPara("group_name") : "";
		String startCreated_at = (getPara("startCreated_at") != null) ? getPara("startCreated_at") : "";
		String endCreated_at = (getPara("endCreated_at") != null) ? getPara("endCreated_at") : "";
		int is_checking = (getParaToInt("is_checking") != null) ? getParaToInt("is_checking") : 2;
		
		JSONObject jo = CreateTopicService.searchCreateTopic(length,offset,topic_name,creator_name,group_name,startCreated_at,endCreated_at,is_checking);
		renderJson(jo);
	}
	
	public void check() {
		int topic_id = getParaToInt("topic_id");
		int pass = getParaToInt("pass");
		int customer_id = getParaToInt("customer_id");
		
		JSONObject jo = CreateTopicService.checkCreateTopic(topic_id,pass,customer_id);
		renderJson(jo);
	}
}

package com.fossns.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.fossns.service.admin.TopicReportService;
import com.jfinal.core.Controller;

public class TopicReportController extends Controller{

	private TopicReportService TopicReportService;
	public TopicReportController() {
		TopicReportService = new TopicReportService();
	}
	
	public void search() {
		int length = getParaToInt("length");
		int offset = getParaToInt("offset");
		String customer_name = (getPara("customer_name") != null) ? getPara("customer_name") : "";
		String topic_name = (getPara("topic_name") != null) ? getPara("topic_name") : "";
		String startCreated_at = (getPara("startCreated_at") != null) ? getPara("startCreated_at") : "";
		String endCreated_at = (getPara("endCreated_at") != null) ? getPara("endCreated_at") : "";
		String content = (getPara("content") != null) ? getPara("content") : "";
		int is_checking = (getParaToInt("is_checking") != null) ? getParaToInt("is_checking") : 2;
		
		JSONObject jo = TopicReportService.searchTopicReport(length,offset,customer_name,topic_name,startCreated_at,endCreated_at,content,is_checking);
		renderJson(jo);
	}
	
	public void check() {
		int report_id = getParaToInt("report_id");
		int customer_id = getParaToInt("customer_id");
		
		JSONObject jo = TopicReportService.checkTopicReport(report_id,customer_id);
		renderJson(jo);
	}
}

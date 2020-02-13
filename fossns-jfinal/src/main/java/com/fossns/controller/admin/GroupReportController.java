package com.fossns.controller.admin;

import com.jfinal.core.Controller;
import com.alibaba.fastjson.JSONObject;
import com.fossns.service.admin.GroupReportService;

public class GroupReportController extends Controller{

	private GroupReportService GroupReportService;
	public GroupReportController() {
		GroupReportService = new GroupReportService();
	}
	
	public void search() {
		int length = getParaToInt("length");
		int offset = getParaToInt("offset");
		String customer_name = (getPara("customer_name") != null) ? getPara("customer_name") : "";
		String group_name = (getPara("group_name") != null) ? getPara("group_name") : "";
		String startCreated_at = (getPara("startCreated_at") != null) ? getPara("startCreated_at") : "";
		String endCreated_at = (getPara("endCreated_at") != null) ? getPara("endCreated_at") : "";
		String content = (getPara("content") != null) ? getPara("content") : "";
		int is_checking = (getParaToInt("is_checking") != null) ? getParaToInt("is_checking") : 2;
		
		JSONObject jo = GroupReportService.searchGroupReport(length,offset,customer_name,group_name,startCreated_at,endCreated_at,content,is_checking);
		renderJson(jo);
	}
	
	public void check() {
		int report_id = getParaToInt("report_id");
		int customer_id = getParaToInt("customer_id");
		
		JSONObject jo = GroupReportService.checkGroupReport(report_id,customer_id);
		renderJson(jo);
	}
}

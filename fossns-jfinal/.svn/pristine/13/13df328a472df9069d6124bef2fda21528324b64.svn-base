package com.fossns.controller.admin;

import com.jfinal.core.Controller;
import com.alibaba.fastjson.JSONObject;
import com.fossns.service.admin.ComplaintService;

public class ComplaintController extends Controller{

	private ComplaintService ComplaintService;
	public ComplaintController() {
		ComplaintService = new ComplaintService();
	}
	
	public void search() {
		int length = getParaToInt("length");
		int offset = getParaToInt("offset");
		String name = (getPara("name") != null) ? getPara("name") : "";
		String nickname = (getPara("nickname") != null) ? getPara("nickname") : "";
		String startCreated_at = (getPara("startCreated_at") != null) ? getPara("startCreated_at") : "";
		String endCreated_at = (getPara("endCreated_at") != null) ? getPara("endCreated_at") : "";
		String content = (getPara("content") != null) ? getPara("content") : "";
		int is_checking = (getParaToInt("is_checking") != null) ? getParaToInt("is_checking") : 2;
		
		JSONObject jo = ComplaintService.searchComplaint(length,offset,name,nickname,startCreated_at,endCreated_at,content,is_checking);
		renderJson(jo);
	}
	
	public void check() {
		int complaint_id = getParaToInt("complaint_id");
		int customer_id = getParaToInt("customer_id");
		
		JSONObject jo = ComplaintService.checkComplaint(complaint_id,customer_id);
		renderJson(jo);
	}
}

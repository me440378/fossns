package com.fossns.service;

import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.JsonHelper;
import com.fossns.model.Reports;

public class ReportService {
	
	private JsonHelper JsonHelper;
	public ReportService() {
		JsonHelper = new JsonHelper();
	}
	public JSONObject createReport(int customer_id, int type, int target_id, String content) {
		JSONObject jo = new JSONObject();
		Reports model = new Reports();
		model.setContent(content);
		model.setCustomerId(customer_id);
		model.setType(type);
		model.setTargetId(target_id);
		model.setIsChecking(true);
		try {
			if(model.save()) {
				jo = JsonHelper.getSimpleReply(0, null);
			} else {
				jo = JsonHelper.getSimpleReply(1, null);
			}
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		return jo;
	}
}

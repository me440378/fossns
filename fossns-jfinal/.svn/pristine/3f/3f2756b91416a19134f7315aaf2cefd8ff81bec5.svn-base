package com.fossns.service;

import com.alibaba.fastjson.JSONObject;
import com.fossns.model.Complaints;
import com.fossns.helper.JsonHelper;

public class ComplaintService {
	
	private JsonHelper JsonHelper;
	public ComplaintService() {
		JsonHelper = new JsonHelper();
	}

	public JSONObject createComplaint(String content, int customer_id) {
		JSONObject jo = new JSONObject();
		Complaints model = new Complaints();
		model.setContent(content);
		model.setCustomerId(customer_id);
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

package com.fossns.service;

import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.JsonHelper;
import com.fossns.model.FriendDynamic;

public class DynamicService {

	private JsonHelper JsonHelper;
	public DynamicService() {
		JsonHelper = new JsonHelper();
	}
	
	public JSONObject createDynamic(int customer_id, String content, String image) {
		JSONObject jo = new JSONObject();
		FriendDynamic model = new FriendDynamic();
		model.setContent(content);
		model.setCustomerId(customer_id);
		model.setImage(image);
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

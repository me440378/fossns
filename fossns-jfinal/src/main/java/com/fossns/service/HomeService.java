package com.fossns.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.*;
import com.fossns.helper.JsonHelper;

public class HomeService {

	private JsonHelper JsonHelper;
	public HomeService() {
		JsonHelper = new JsonHelper();
	}
	public JSONObject getAll() {
		JSONObject jo = new JSONObject();
		List<Record> lrb = null;
		List<Record> lra = null;
		JSONArray bannerList = new JSONArray();
		JSONArray activityList = new JSONArray();
		try {
			lrb = Db.find("select image,title,url from banners where is_public=1");
			lra = Db.find("select name,image,summary from activities where is_public=1");
			bannerList = JsonHelper.getJSONArrayByListRecord(lrb);
			activityList = JsonHelper.getJSONArrayByListRecord(lra);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		
		jo.put("bannerList", bannerList);
		jo.put("activityList", activityList);
		return jo;
	}

}

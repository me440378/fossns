package com.fossns.service.admin;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.JsonHelper;
import com.fossns.model.Banners;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class BannerService {

	private JsonHelper JsonHelper;
	public BannerService() {
		JsonHelper = new JsonHelper();
	}
	
	public JSONObject getAll() {
		JSONObject jo = new JSONObject();
		List<Record> lr = null;
		JSONArray bannerList = new JSONArray();
		try {
			lr = Db.find("select id,title,image,is_public,url from banners");
			bannerList = JsonHelper.getJSONArrayByListRecord(lr);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
			return jo;
		}
		
		jo.put("bannerList", bannerList);
		return jo;
	}

	public JSONObject updateBanner(int id, String title, String image, int is_public, String url) {
		JSONObject jo = new JSONObject();
		Banners model = (Banners)Banners.dao.findById(id);
		model.setTitle(title);
		model.setImage(image);
		model.setIsPublic((is_public==1)?true:false);
		model.setUrl(url);
		try {
			if(model.update()) {
				jo = JsonHelper.getSimpleReply(0, null);
			} else {
				jo = JsonHelper.getSimpleReply(1, null);
			}
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		return jo;
	}

	public JSONObject getBanner(int id) {
		JSONObject jo = new JSONObject();
		Record re = null;
		try {
			re = Db.findFirst("select title,image,is_public,url from banners where id=?",id);
			String title = JsonHelper.getStringByRecord(re, "title");
			String image = JsonHelper.getStringByRecord(re, "image");
			int is_public = JsonHelper.getIntByRecord(re, "is_public");
			String url = JsonHelper.getStringByRecord(re, "url");
			jo.put("title", title);
			jo.put("image", image);
			jo.put("is_public", is_public);
			jo.put("url", url);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
			return jo;
		}
		return jo;
	}

	public JSONObject createBanner(String title, String image, int is_public, String url) {
		JSONObject jo = new JSONObject();
		Banners model = new Banners();
		model.setTitle(title);
		model.setImage(image);
		model.setIsPublic((is_public==1)?true:false);
		model.setUrl(url);
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

	public JSONObject deleteBanner(int id) {
		JSONObject jo = new JSONObject();
		Banners model = (Banners)Banners.dao.findById(id);
		try {
			if(model.deleteById(id)) {
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

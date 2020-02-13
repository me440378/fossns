package com.fossns.controller.admin;

import com.jfinal.core.Controller;
import com.alibaba.fastjson.JSONObject;
import com.fossns.service.admin.BannerService;

public class BannerController extends Controller{

	private BannerService BannerService;
	public BannerController() {
		BannerService = new BannerService();
	}
	
	public void all() {
		JSONObject jo = BannerService.getAll();
		renderJson(jo);
	}
	
	public void update() {
		int id = getParaToInt("id");
		String title = getPara("title");
		String image = getPara("image");
		int is_public = getParaToInt("is_public");
		String url = getPara("url");
		
		JSONObject jo = BannerService.updateBanner(id,title,image,is_public,url);
		renderJson(jo);
	}
	
	public void get() {
		int id = getParaToInt("id");
		JSONObject jo = BannerService.getBanner(id);
		renderJson(jo);
	}
	
	public void create() {
		String title = getPara("title");
		String image = getPara("image");
		int is_public = getParaToInt("is_public");
		String url = getPara("url");
		
		JSONObject jo = BannerService.createBanner(title,image,is_public,url);
		renderJson(jo);
	}
	
	public void delete() {
		int id = getParaToInt("id");
		JSONObject jo = BannerService.deleteBanner(id);
		renderJson(jo);
	}
}

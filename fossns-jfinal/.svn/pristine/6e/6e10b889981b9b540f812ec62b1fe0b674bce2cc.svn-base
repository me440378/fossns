package com.fossns.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.*;
import com.fossns.model.Customers;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class CustomerService {

	private JsonHelper JsonHelper;
	public CustomerService() {
		JsonHelper = new JsonHelper();
	}
	public JSONObject updateCostomer(int customer_id, String nickname) {
		JSONObject jo = new JSONObject();
		Customers model = (Customers)Customers.dao.findById(customer_id);
		model.setNickname(nickname);
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
	public JSONObject changePassword(int customer_id, String oldpswd, String newpswd) {
		JSONObject jo = new JSONObject();
		Customers model = (Customers)Customers.dao.findById(customer_id);
		String pswd = model.getPassword();
		//验证旧密码
		if(pswd.equals(MD5Helper.Encode(oldpswd).toLowerCase())) {
			model.setPassword(MD5Helper.Encode(newpswd).toLowerCase());
		} else {
			jo = JsonHelper.getSimpleReply(1, "旧密码错误");
			return jo;
		}
		
		try {
			if(model.update()) {
				jo = JsonHelper.getSimpleReply(0, "修改密码成功");
			} else {
				jo = JsonHelper.getSimpleReply(1, null);
			}
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		
		return jo;
	}
	public JSONObject selectCustomer(String content) {
		JSONObject jo = new JSONObject();
		List<Record> lr = null;
		JSONArray customerList = new JSONArray();
		try {
			lr = Db.find("select id as customer_id, nickname as customer_nickname,icon as customer_icon from customers where nickname like '%"+content+"%'");
			customerList = JsonHelper.getJSONArrayByListRecord(lr); 
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		
		jo.put("customerList", customerList);
		return jo;
	}
	public JSONObject getCustomer(int customer_id) {
		JSONObject jo = new JSONObject();
		Record re = null;
		try {
			re = Db.findFirst("select name,nickname from customers where id=?",customer_id);
			String name = JsonHelper.getStringByRecord(re, "name");
			String nickname = JsonHelper.getStringByRecord(re, "nickname");
			jo.put("name", name);
			jo.put("nickname", nickname);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		return jo;
	}
}

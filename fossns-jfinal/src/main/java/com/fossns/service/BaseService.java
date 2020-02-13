package com.fossns.service;

import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.JsonHelper;
import com.fossns.helper.MD5Helper;
import com.fossns.model.Customers;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class BaseService {

	private JsonHelper JsonHelper;
	public BaseService() {
		JsonHelper = new JsonHelper();
	}
	public JSONObject loginCustomer(String username, String password) {
		JSONObject jo = new JSONObject();
		Record re = null;
		try {
			re = Db.findFirst("select id,password from customers where name=?",username);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
			return jo;
		}
		
		if(re==null) {
			jo = JsonHelper.getSimpleReply(1, "该用户不存在");
			return jo;
		} else {
			int customer_id;
			String DBpassword = null;
			try {
				customer_id = JsonHelper.getIntByRecord(re, "id");
				DBpassword = JsonHelper.getStringByRecord(re, "password");
			} catch(Exception e) {
				jo = JsonHelper.getSimpleReply(1, e.toString());
				return jo;
			}
			
			if(!DBpassword.equals(MD5Helper.Encode(password).toLowerCase())) {
				jo = JsonHelper.getSimpleReply(1, "密码不正确");
				return jo;
			} else {
				jo = JsonHelper.getSimpleReply(0, "登陆成功");
				jo.put("customer_id", customer_id);
				return jo;
			}
		}
	}
	public JSONObject registerCustomer(String username, String password) {
		JSONObject jo = new JSONObject();
		Record re = null;
		try {
			re = Db.findFirst("select * from customers where name=?",username);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
			return jo;
		}
		
		if(re!=null) {
			jo = JsonHelper.getSimpleReply(1, "该用户名已存在");
			return jo;
		} else {
			Customers model = new Customers();
			model.setName(username);
			model.setPassword(MD5Helper.Encode(password).toLowerCase());
			model.setNickname(username);
			try {
				if(model.save()) {
					jo = JsonHelper.getSimpleReply(0, "注册成功");
					return jo;
				} else {
					jo = JsonHelper.getSimpleReply(1, "注册失败");
					return jo;
				}
			} catch(Exception e) {
				jo = JsonHelper.getSimpleReply(1, e.toString());
				return jo;
			}
			
		}
	}
}

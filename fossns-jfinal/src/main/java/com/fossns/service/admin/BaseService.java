package com.fossns.service.admin;

import com.alibaba.fastjson.*;
import com.fossns.helper.*;
import com.jfinal.plugin.activerecord.*;

public class BaseService {

	private JsonHelper JsonHelper;
	public BaseService() {
		JsonHelper = new JsonHelper();
	}
	
	public JSONObject getAll() {
		JSONObject jo = new JSONObject();
		Record rec = null;
		Record rel = null;
		Record reg = null;
		Record ret = null;
		try {
			rec = Db.findFirst("select count(*) as customerNum from customers");
			rel = Db.findFirst("select count(*) as livelyCustomerNum from (select customer_id from topic_detail where topic_detail.created_at > CURDATE()-7 group by customer_id) as lcn");
			reg = Db.findFirst("select count(*) as groupNum from groups where groups.is_public=1");
			ret = Db.findFirst("select count(*) as topicNum from topics");
			int customerNum = JsonHelper.getIntByRecord(rec, "customerNum");
			int livelyCustomerNum = JsonHelper.getIntByRecord(rel, "livelyCustomerNum");
			int groupNum = JsonHelper.getIntByRecord(reg, "groupNum");
			int topicNum = JsonHelper.getIntByRecord(ret, "topicNum");
			jo.put("customerNum", customerNum);
			jo.put("livelyCustomerNum", livelyCustomerNum);
			jo.put("groupNum", groupNum);
			jo.put("topicNum", topicNum);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
			return jo;
		}
		
		return jo;
	}

	public JSONObject loginUser(String username, String password) {
		JSONObject jo = new JSONObject();
		Record re = null;
		try {
			re = Db.findFirst("select id,password from users where name=?",username);
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
}

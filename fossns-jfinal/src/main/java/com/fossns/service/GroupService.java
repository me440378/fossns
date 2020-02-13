package com.fossns.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fossns.model.GroupCustomer;
import com.fossns.model.Groups;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.fossns.helper.JsonHelper;

public class GroupService {

	private JsonHelper JsonHelper;
	public GroupService() {
		JsonHelper = new JsonHelper();
	}
	
	public JSONObject updateGroup(int group_id, String summary_new) {
		JSONObject jo = new JSONObject();
		Groups model = (Groups)Groups.dao.findById(group_id);
		model.setSummaryNew(summary_new);
		model.setIsChecking(true);
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

	public JSONObject joinGroup(int customer_id, int group_id) {
		JSONObject jo = new JSONObject();
		GroupCustomer model = new GroupCustomer();
		model.setGroupId(group_id);
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

	public JSONObject getAll(int customer_id, int group_id) {
		JSONObject jo = new JSONObject();
		Record reg = null;
		Record reij = null;
		List<Record> lr = null;
		JSONObject group = new JSONObject();
		int is_joined = 0;
		JSONArray topicList = new JSONArray();
		try {
			reg = Db.findFirst("select id,name,summary,creator_id from groups where id=?",group_id);
			int id = JsonHelper.getIntByRecord(reg, "id");
			String name = JsonHelper.getStringByRecord(reg, "name");
			String summary = JsonHelper.getStringByRecord(reg, "summary");
			int creator_id = JsonHelper.getIntByRecord(reg, "creator_id");
			group.put("id", id);
			group.put("name", name);
			group.put("summary", summary);
			group.put("creator_id", creator_id);
			
			reij = Db.findFirst("select * from group_customer where is_checking=0 and customer_id=? and group_id=?",customer_id,group_id);
			is_joined = (reij==null) ? 0 : 1;
			
			lr = Db.find("select name,id,image from topics where is_public=1 and group_id = ?",group_id);
			topicList = JsonHelper.getJSONArrayByListRecord(lr);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		
		jo.put("group", group);
		jo.put("is_joined", is_joined);
		jo.put("topicList", topicList);
		return jo;
	}

	public JSONObject getGroupListAll() {
		JSONObject jo = new JSONObject();
		List<Record> lr = null;
		JSONArray groupList = new JSONArray();
		try {
			lr = Db.find("select groups.id as group_id,name,count(*) as popularity,image,summary from groups,group_customer where groups.id=group_customer.group_id and group_customer.is_checking=0 group by groups.id order by popularity DESC limit 0,20");
			groupList = JsonHelper.getJSONArrayByListRecord(lr);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		
		jo.put("groupList", groupList);
		return jo;
	}

	public JSONObject createGroup(int customer_id, String name, String summary, String image) {
		JSONObject jo = new JSONObject();
		Groups model = new Groups();
		model.setCreatorId(customer_id);
		model.setName(name);
		model.setSummary(summary);
		model.setImage(image);
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

	public JSONObject selectGroup(String content) {
		JSONObject jo = new JSONObject();
		List<Record> lr = null;
		JSONArray groupList = new JSONArray();
		try {
			lr = Db.find("select groups.id as group_id,name,count(*) as popularity,groups.image as image from groups,group_customer where groups.id=group_customer.group_id and groups.is_public=1 and name like '%"+content+"%' group by groups.id");
			groupList = JsonHelper.getJSONArrayByListRecord(lr); 
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		
		jo.put("groupList", groupList);
		return jo;
	}

	public JSONObject getGroup(int group_id) {
		JSONObject jo = new JSONObject();
		Record re = null;
		JSONObject group = new JSONObject();
		try {
			re = Db.findFirst("select name,summary from groups where id=?",group_id);
			String name = JsonHelper.getStringByRecord(re, "name");
			String summary = JsonHelper.getStringByRecord(re, "summary");
			group.put("name", name);
			group.put("summary", summary);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		
		jo.put("group", group);
		return jo;
	}

}

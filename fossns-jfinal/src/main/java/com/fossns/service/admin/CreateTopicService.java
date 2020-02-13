package com.fossns.service.admin;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.JsonHelper;
import com.fossns.helper.MessageHelper;
import com.fossns.model.Topics;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class CreateTopicService {
	
	private JsonHelper JsonHelper;
	private MessageHelper MessageHelper;
	public CreateTopicService() {
		JsonHelper = new JsonHelper();
		MessageHelper = new MessageHelper();
	}
	public JSONObject searchCreateTopic(int length, int offset, String topic_name, String creator_name,
			String group_name, String startCreated_at, String endCreated_at, int is_checking) {
		JSONObject jo = new JSONObject();
		String sql = "select topics.id as topic_id,topics.name as topic_name,DATE_FORMAT(topics.created_at,'%Y-%m-%d %H:%i:%s') as created_at,customers.name as creator_name,topics.creator_id as creator_id,groups.name as group_name,topics.is_checking as is_checking from topics,groups,customers where customers.id=topics.creator_id and groups.id=topics.group_id and topics.is_public=0 and groups.name like '%"+group_name+"%' and topics.name like '%"+topic_name+"%' and customers.name like '%"+creator_name+"%'";
		if(startCreated_at!="") {
			sql+=" and topics.created_at>\'"+startCreated_at+"\'";
		}
		if(endCreated_at!="") {
			sql+=" and topics.created_at<\'"+endCreated_at+"\'";
		}
		if(is_checking!=2) {
			sql+=" and topics.is_checking="+is_checking;
		}
		String tsql = "select count(*) as total from ("+sql+") as t";
		sql+=" order by topics.created_at limit "+offset+","+length;
		List<Record> lr = null;
		Record re = null;
		JSONArray items = new JSONArray();
		JSONObject table = new JSONObject();
		try {
			lr = Db.find(sql);
			re = Db.findFirst(tsql);
			items = JsonHelper.getJSONArrayByListRecord(lr);
			int total = JsonHelper.getIntByRecord(re, "total");
			int pageCount = length;
			int pageIndex = offset /length +1;
			table.put("items", items);
			table.put("total", total);
			table.put("pageCount", pageCount);
			table.put("pageIndex", pageIndex);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
			return jo;
		}
		
		jo.put("table", table);
		return jo;
	}
	public JSONObject checkCreateTopic(int topic_id, int pass, int customer_id) {
		JSONObject jo = new JSONObject();
		Topics model = (Topics)Topics.dao.findById(topic_id);
		
		if(pass==1) {
			model.setIsPublic(true);
			model.setIsChecking(false);
			try {
				if(model.update()) {
					jo = JsonHelper.getSimpleReply(0, null);
					MessageHelper.sendSystemMessage(customer_id, "你的话题已通过审核");
				} else {
					jo = JsonHelper.getSimpleReply(1, null);
				}
			} catch(Exception e) {
				jo = JsonHelper.getSimpleReply(1, e.toString());
			}
		} else {
			model.setIsChecking(false);
			try {
				if(model.update()) {
					jo = JsonHelper.getSimpleReply(0, null);
					MessageHelper.sendSystemMessage(customer_id, "你的话题没有通过审核");
				} else {
					jo = JsonHelper.getSimpleReply(1, null);
				}
			} catch(Exception e) {
				jo = JsonHelper.getSimpleReply(1, e.toString());
			}
		}
		
		return jo;
	}
	
}

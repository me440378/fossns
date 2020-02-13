package com.fossns.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.JsonHelper;
import com.fossns.model.TopicDetail;
import com.fossns.model.Topics;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class TopicService {

	private JsonHelper JsonHelper;
	public TopicService() {
		JsonHelper = new JsonHelper();
	}
	
	public JSONObject createTopic(int customer_id, String name,int group_id) {
		JSONObject jo = new JSONObject();
		Topics model = new Topics();
		model.setName(name);
		model.setCreatorId(customer_id);
		model.setIsChecking(true);
		model.setIsPublic(false);
		model.setGroupId(group_id);
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

	public JSONObject selectTopic(String content) {
		JSONObject jo = new JSONObject();
		List<Record> lr = null;
		JSONArray topicList = new JSONArray();
		try {
			lr = Db.find("select topics.id as topic_id,topics.name as name,count(topic_detail.customer_id) as popularity,customers.nickname as creator_nickname from topics,topic_detail,customers where topics.id=topic_detail.topic_id and topics.creator_id=customers.id and topics.name like '%"+content+"%' group by topic_id");
			topicList = JsonHelper.getJSONArrayByListRecord(lr); 
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		
		jo.put("topicList", topicList);
		return jo;
	}

	public JSONObject getAll(int topic_id,int offset,int length) {
		JSONObject jo = new JSONObject();
		Record reg = null;
		Record reto = null;
		Record reta = null;
		List<Record> lr = null;
		
		JSONObject group = new JSONObject();
		JSONObject topic = new JSONObject();
		JSONObject table = new JSONObject();
		JSONArray detailList = new JSONArray();
		try {
			reg = Db.findFirst("select groups.id as id,groups.name as name,groups.summary as summary from topics,groups where topics.group_id=groups.id and topics.id=?",topic_id);
			reto = Db.findFirst("select name from topics where id=?",topic_id);
			reta = Db.findFirst("select count(*) as total from topic_detail where topic_id=?",topic_id);
			lr = Db.find("select topic_detail.id as id,customers.nickname as customer_nickname,content,DATE_FORMAT(topic_detail.created_at,'%Y-%m-%d %H:%i:%s') as created_at,customers.icon as customer_icon from topic_detail,customers where topic_detail.customer_id=customers.id and topic_detail.topic_id=? limit ?,?",topic_id,offset,length);
			
			int id = JsonHelper.getIntByRecord(reg, "id");
			String gname = JsonHelper.getStringByRecord(reg, "name");
			String summary = JsonHelper.getStringByRecord(reg, "summary");
			String tname = JsonHelper.getStringByRecord(reto, "name");
			int total = JsonHelper.getIntByRecord(reta, "total");
			int pageCount = length;
			int pageIndex = offset /length +1;
			group.put("id", id);
			group.put("name", gname);
			group.put("summary", summary);
			topic.put("name", tname);
			table.put("pageIndex", pageIndex);
			table.put("pageCount", pageCount);
			table.put("total", total);
			detailList = JsonHelper.getJSONArrayByListRecord(lr);
			
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		
		jo.put("group", group);
		jo.put("topic", topic);
		jo.put("table", table);
		jo.put("detailList", detailList);
		return jo;
	}

	public JSONObject createTopicDetail(int customer_id,int topic_id, String content, String image) {
		JSONObject jo = new JSONObject();
		TopicDetail model = new TopicDetail();
		model.setCustomerId(customer_id);
		model.setTopicId(topic_id);
		model.setContent(content);
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

	public JSONObject getTopicListAll() {
		JSONObject jo = new JSONObject();
		List<Record> lrh = null;
		List<Record> lrn = null;
		JSONArray hotTopicList = new JSONArray();
		JSONArray newTopicList = new JSONArray();
		try {
			lrh = Db.find("select groups.name as group_name,groups.id as group_id,topics.name as topic_name,topics.id as topic_id,topics.image as topic_image,count(topic_detail.id) as topic_popularity from topics,groups,topic_detail where topics.group_id=groups.id and topics.id=topic_detail.topic_id group by topics.id order by topic_popularity DESC limit 0,10");
			lrn = Db.find("select groups.name as group_name,groups.id as group_id,topics.name as topic_name,topics.id as topic_id,topics.image as topic_image,DATE_FORMAT(topics.created_at,'%Y-%m-%d %H:%i:%s') as topic_created_at from topics,groups where topics.group_id=groups.id order by topic_created_at DESC limit 0,10");
			hotTopicList = JsonHelper.getJSONArrayByListRecord(lrh);
			newTopicList = JsonHelper.getJSONArrayByListRecord(lrn);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		
		jo.put("hotTopicList", hotTopicList);
		jo.put("newTopicList", newTopicList);
		return jo;
	}

}

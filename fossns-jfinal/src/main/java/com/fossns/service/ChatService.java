package com.fossns.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.JsonHelper;
import com.fossns.model.ChatDetail;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class ChatService {

	private JsonHelper JsonHelper;
	public ChatService() {
		JsonHelper = new JsonHelper();
	}
	public JSONObject getAll(int customer_id, int friend_id) {
		JSONObject jo = new JSONObject();
		List<Record> lrcd = null;
		JSONArray chatDetailList = new JSONArray();
		try {
			lrcd = Db.find("select sender_id, sender.nickname as sender_nickname,receiver_id,receiver.nickname as receiver_nickname,content,DATE_FORMAT(created_at,'%Y-%m-%d %H:%i:%s') as created_at from chat_detail,customers as sender,customers as receiver where sender_id=sender.id and receiver_id=receiver.id and ((sender_id=? and receiver_id=?) or (sender_id=? and receiver_id=?)) order by created_at",customer_id,friend_id,friend_id,customer_id);
			chatDetailList = JsonHelper.getJSONArrayByListRecord(lrcd);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		
		jo.put("chatDetailList", chatDetailList);
		return jo;
	}
	
	public JSONObject createChatDetail(int sender_id, int receiver_id, String content, int type) {
		JSONObject jo = new JSONObject();
		ChatDetail model = new ChatDetail();
		model.setSenderId(sender_id);
		model.setReceiverId(receiver_id);
		model.setContent(content);
		model.setType(type);
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

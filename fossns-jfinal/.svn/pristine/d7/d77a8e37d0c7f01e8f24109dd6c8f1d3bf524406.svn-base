package com.fossns.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.JsonHelper;
import com.fossns.model.FriendApply;
import com.fossns.model.Friends;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class FriendService {

	private JsonHelper JsonHelper;
	public FriendService() {
		JsonHelper = new JsonHelper();
	}
	public JSONObject getAll(int customer_id) {
		JSONObject jo = new JSONObject();
		List<Record> lrd = null;
		List<Record> lrf = null;
		List<Record> lra = null;
		JSONArray dynamicList = new JSONArray();
		JSONArray friendList = new JSONArray();
		JSONArray applyList = new JSONArray();
		try {
			lrd = Db.find("select customers.icon as customer_icon,customers.nickname as customer_nickname,DATE_FORMAT(friend_dynamic.created_at,'%Y-%m-%d %H:%i:%s') as dynamic_created_at,friend_dynamic.content as dynamic_content,friend_dynamic.image as dynamic_image from customers,friend_dynamic,friends where friends.friend_id = customers.id and friend_dynamic.customer_id=friends.friend_id and (friends.customer_id=? or customers.id=?)",customer_id,customer_id);
			lrf = Db.find("select friend_id as customer_id,customers.nickname as customer_nickname,customers.icon as customer_icon from customers,friends where friends.friend_id=customers.id and friends.customer_id=?",customer_id);
			lra = Db.find("select sender_id as customer_id, customers.nickname as customer_nickname,customers.icon as customer_icon,friend_apply.id as apply_id from friend_apply,customers where sender_id=customers.id and receiver_id=?",customer_id);
			dynamicList = JsonHelper.getJSONArrayByListRecord(lrd);
			friendList = JsonHelper.getJSONArrayByListRecord(lrf);
			applyList = JsonHelper.getJSONArrayByListRecord(lra);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		
		jo.put("dynamicList", dynamicList);
		jo.put("friendList", friendList);
		jo.put("applyList", applyList);
		return jo;
	}
	public JSONObject deleteFriend(int customer_id, int friend_id) {
		JSONObject jo = new JSONObject();
		try {
		Db.delete("delete from friends where (customer_id=? and friend_id=?) or (customer_id=? and friend_id=?)",customer_id,friend_id,friend_id,customer_id);
		jo = JsonHelper.getSimpleReply(0, null);
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		return jo;
	}
	public JSONObject agreeApply(int customer_id, int friend_id,int apply_id) {
		JSONObject jo = new JSONObject();
		Friends model1 = new Friends();
		Friends model2 = new Friends();
		model1.setCustomerId(customer_id);
		model1.setFriendId(friend_id);
		model2.setCustomerId(friend_id);
		model2.setFriendId(customer_id);
		try {
			if(model1.save()&&model2.save()) {
				jo = this.deleteApply(apply_id);
			} else {
				jo = JsonHelper.getSimpleReply(1, null);
			}
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		return jo;
	}
	
	public JSONObject deleteApply(int apply_id) {
		JSONObject jo = new JSONObject();
		FriendApply model = (FriendApply)FriendApply.dao.findById(apply_id);
		try {
			if(model.deleteById(apply_id)) {
				jo = JsonHelper.getSimpleReply(0, null);
			} else {
				jo = JsonHelper.getSimpleReply(1, null);
			}
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		return jo;
	}
	public JSONObject createApply(int customer_id, int friend_id) {
		JSONObject jo = new JSONObject();
		FriendApply model = new FriendApply();
		model.setSenderId(customer_id);
		model.setReceiverId(friend_id);
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

package com.fossns.service.admin;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.JsonHelper;
import com.fossns.helper.MessageHelper;
import com.fossns.model.Groups;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class UpdateGroupService {

	private JsonHelper JsonHelper;
	private MessageHelper MessageHelper;
	public UpdateGroupService() {
		JsonHelper = new JsonHelper();
		MessageHelper = new MessageHelper();
	}
	public JSONObject searchUpdateGroup(int length, int offset, String creator_name, String group_name,
			String startUpdate_at, String endUpdate_at) {
		JSONObject jo = new JSONObject();
		String sql = "select groups.id as group_id,groups.name as group_name,groups.summary_new as summary_new,groups.summary as summary,groups.creator_id as creator_id,customers.name as creator_name,DATE_FORMAT(groups.update_at,'%Y-%m-%d %H:%i:%s') as update_at from groups,customers where groups.creator_id=customers.id and groups.is_public=1 and groups.is_checking=1 and customers.name like '%"+creator_name+"%' and groups.name like '%"+group_name+"%'";
		if(startUpdate_at!="") {
			sql+=" and update_at>\'"+startUpdate_at+"\'";
		}
		if(endUpdate_at!="") {
			sql+=" and update_at<\'"+endUpdate_at+"\'";
		}
		String tsql = "select count(*) as total from ("+sql+") as t";
		sql+=" order by created_at limit "+offset+","+length;
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
	public JSONObject checkUpdateGroup(int group_id, int pass, int customer_id) {
		JSONObject jo = new JSONObject();
		Groups model = (Groups)Groups.dao.findById(group_id);
		if(pass==1) {
			String summary_new = model.getSummaryNew();
			model.setSummary(summary_new);
			model.setSummaryNew("");
			model.setUpdateAt(new Date());
			model.setIsChecking(false);
			try {
				if(model.update()) {
					jo = JsonHelper.getSimpleReply(0, null);
					MessageHelper.sendSystemMessage(customer_id, "你对圈子的修改已通过审核");
				} else {
					jo = JsonHelper.getSimpleReply(1, null);
				}
			} catch(Exception e) {
				jo = JsonHelper.getSimpleReply(1, e.toString());
			}
		} else {
			model.setIsChecking(false);
			model.setSummaryNew("");
			try {
				if(model.update()) {
					jo = JsonHelper.getSimpleReply(0, null);
					MessageHelper.sendSystemMessage(customer_id, "你对圈子的修改没有通过审核");
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

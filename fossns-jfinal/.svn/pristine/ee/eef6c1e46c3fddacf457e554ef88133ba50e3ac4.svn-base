package com.fossns.service.admin;

import java.util.List;

import com.alibaba.fastjson.*;
import com.fossns.helper.JsonHelper;
import com.fossns.helper.MessageHelper;
import com.fossns.model.Complaints;
import com.jfinal.plugin.activerecord.*;

public class ComplaintService {

	private JsonHelper JsonHelper;
	private MessageHelper MessageHelper;
	public ComplaintService() {
		JsonHelper = new JsonHelper();
		MessageHelper = new MessageHelper();
	}
	
	public JSONObject searchComplaint(int length, int offset, String name, String nickname, String startCreated_at, String endCreated_at, String content, int is_checking) {
		JSONObject jo = new JSONObject();
		String sql = "select complaints.id as complaint_id,customers.name as name,customers.nickname as nickname,complaints.content as content,DATE_FORMAT(complaints.created_at,'%Y-%m-%d %H:%i:%s') as created_at,complaints.is_checking as is_checking,customers.id as customer_id from customers,complaints where complaints.customer_id=customers.id and name like '%"+name+"%' and nickname like '%"+nickname+"%' and content like '%"+content+"%'";
		if(startCreated_at!="") {
			sql+=" and created_at>\'"+startCreated_at+"\'";
		}
		if(endCreated_at!="") {
			sql+=" and created_at<\'"+endCreated_at+"\'";
		}
		if(is_checking!=2) {
			sql+=" and is_checking="+is_checking;
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

	public JSONObject checkComplaint(int complaint_id, int customer_id) {
		JSONObject jo = new JSONObject();
		Complaints model = (Complaints)Complaints.dao.findById(complaint_id);
		model.setIsChecking(false);
		try {
			if(model.update()) {
				jo = JsonHelper.getSimpleReply(0, null);
				MessageHelper.sendSystemMessage(customer_id, "我已经收到你的投诉了");
			} else {
				jo = JsonHelper.getSimpleReply(1, null);
			}
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		
		return jo;
	}
}

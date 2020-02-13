package com.fossns.service.admin;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.JsonHelper;
import com.fossns.helper.MessageHelper;
import com.fossns.model.GroupCustomer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class JoinGroupService {
	
	private JsonHelper JsonHelper;
	private MessageHelper MessageHelper;
	public JoinGroupService() {
		JsonHelper = new JsonHelper();
		MessageHelper = new MessageHelper();
	}
	public JSONObject searchJoinGroup(int length, int offset, String customer_name, String group_name,
			int is_checking) {
		JSONObject jo = new JSONObject();
		String sql = "select customers.name as customer_name,groups.name as group_name,group_customer.is_checking as is_checking,group_customer.id as apply_id,group_customer.customer_id as customer_id from customers,groups,group_customer where group_customer.group_id=groups.id and group_customer.customer_id=customers.id and customers.name like '%"+customer_name+"%' and groups.name like '%"+group_name+"%'";
		if(is_checking!=2) {
			sql+=" and group_customer.is_checking="+is_checking;
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
	public JSONObject checkJoinGroup(int apply_id, int pass, int customer_id) {
		JSONObject jo = new JSONObject();
		GroupCustomer model = (GroupCustomer)GroupCustomer.dao.findById(apply_id);
		if(pass==1) {
			model.setIsChecking(false);
			try {
				if(model.update()) {
					jo = JsonHelper.getSimpleReply(0, null);
					int group_id = model.getGroupId();
					Record re = Db.findFirst("select name from groups where id=?",group_id);
					String name = JsonHelper.getStringByRecord(re, "name");
					MessageHelper.sendSystemMessage(customer_id, "你已成功加入"+name+"圈子");
				} else {
					jo = JsonHelper.getSimpleReply(1, null);
				}
			} catch(Exception e) {
				jo = JsonHelper.getSimpleReply(1, e.toString());
			}
		} else {
			int group_id = model.getGroupId();
			model.deleteById(apply_id);
			Record re = Db.findFirst("select name from groups where id=?",group_id);
			String name = JsonHelper.getStringByRecord(re, "name");
			MessageHelper.sendSystemMessage(customer_id, "你加入"+name+"圈子失败");
		}
		
		return jo;
	}
	
}

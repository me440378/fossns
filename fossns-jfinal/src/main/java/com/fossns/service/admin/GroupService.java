package com.fossns.service.admin;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.JsonHelper;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class GroupService {

	private JsonHelper JsonHelper;
	public GroupService() {
		JsonHelper = new JsonHelper();
	}
	public JSONObject searchGroup(int length, int offset, String group_name, String startCreated_at, String endCreated_at) {
		JSONObject jo = new JSONObject();
		String sql = "select groups.name as group_name,customers.name as creator_name,DATE_FORMAT(groups.created_at,'%Y-%m-%d %H:%i:%s') as created_at,groups.summary as summary from groups,customers where groups.creator_id=customers.id and is_public=1 and groups.name like '%"+group_name+"%'";
		if(startCreated_at!="") {
			sql+=" and created_at>\'"+startCreated_at+"\'";
		}
		if(endCreated_at!="") {
			sql+=" and created_at<\'"+endCreated_at+"\'";
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
	
}

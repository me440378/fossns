package com.fossns.service.admin;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.JsonHelper;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
public class TopicService {
	
	private JsonHelper JsonHelper;
	public TopicService() {
		JsonHelper = new JsonHelper();
	}
	public JSONObject searchTopic(int length, int offset, String group_name, String topic_name, String creator_name,
			String startCreated_at, String endCreated_at) {
		JSONObject jo = new JSONObject();
		String sql = "select topics.name as topic_name,DATE_FORMAT(topics.created_at,'%Y-%m-%d %H:%i:%s') as created_at,customers.name as creator_name,groups.name as group_name from topics,groups,customers where customers.id=topics.creator_id and groups.id=topics.group_id and topics.is_public=1 and groups.name like '%"+group_name+"%' and topics.name like '%"+topic_name+"%' and customers.name like '%"+creator_name+"%'";
		if(startCreated_at!="") {
			sql+=" and topics.created_at>\'"+startCreated_at+"\'";
		}
		if(endCreated_at!="") {
			sql+=" and topics.created_at<\'"+endCreated_at+"\'";
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
}

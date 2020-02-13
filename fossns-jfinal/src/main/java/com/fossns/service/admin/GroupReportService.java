package com.fossns.service.admin;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.JsonHelper;
import com.fossns.helper.MessageHelper;
import com.fossns.model.Reports;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class GroupReportService {
	
	private JsonHelper JsonHelper;
	private MessageHelper MessageHelper;
	public GroupReportService() {
		JsonHelper = new JsonHelper();
		MessageHelper = new MessageHelper();
	}
	public JSONObject searchGroupReport(int length, int offset, String customer_name, String group_name,
			String startCreated_at, String endCreated_at, String content, int is_checking) {
		JSONObject jo = new JSONObject();
		String sql = "select reports.id as report_id,customers.name as customer_name,reports.content as content,groups.name as group_name,DATE_FORMAT(reports.created_at,'%Y-%m-%d %H:%i:%s') as created_at,reports.is_checking as is_checking,reports.customer_id as customer_id from reports,customers,groups where reports.target_id=groups.id and reports.customer_id=customers.id and reports.type=0 and customers.name like '%"+customer_name+"%' and groups.name like '%"+group_name+"%' and reports.content like '%"+content+"%'";
		if(startCreated_at!="") {
			sql+=" and reports.created_at>\'"+startCreated_at+"\'";
		}
		if(endCreated_at!="") {
			sql+=" and reports.created_at<\'"+endCreated_at+"\'";
		}
		if(is_checking!=2) {
			sql+=" and reports.is_checking="+is_checking;
		}
		String tsql = "select count(*) as total from ("+sql+") as t";
		sql+=" order by reports.created_at limit "+offset+","+length;
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
	public JSONObject checkGroupReport(int report_id, int customer_id) {
		JSONObject jo = new JSONObject();
		Reports model = (Reports)Reports.dao.findById(report_id);
		model.setIsChecking(false);
		try {
			if(model.update()) {
				jo = JsonHelper.getSimpleReply(0, null);
				MessageHelper.sendSystemMessage(customer_id, "我已经收到你的举报了");
			} else {
				jo = JsonHelper.getSimpleReply(1, null);
			}
		} catch(Exception e) {
			jo = JsonHelper.getSimpleReply(1, e.toString());
		}
		
		return jo;
	}

}

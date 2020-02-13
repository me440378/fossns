package com.fossns.service.admin;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fossns.helper.JsonHelper;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class CustomerService {

	private JsonHelper JsonHelper;
	public CustomerService() {
		JsonHelper = new JsonHelper();
	}
	public JSONObject searchCustomer(int length, int offset, String name, String nickname) {
		JSONObject jo = new JSONObject();
		String sql = "select name,nickname from customers where name like '%"+name+"%' and nickname like '%"+nickname+"%'";
		String tsql = "select count(*) as total from ("+sql+") as t";
		sql+=" limit "+offset+","+length;
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

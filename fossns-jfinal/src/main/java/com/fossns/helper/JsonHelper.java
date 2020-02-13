package com.fossns.helper;

import java.util.List;
import com.alibaba.fastjson.*;
import com.jfinal.plugin.activerecord.Record;

public class JsonHelper {
	
	
	public int getIntByRecord(Record re,String key) {
		String str = JSON.toJSONString(re);
		JSONObject jo = JSON.parseObject(str);
		int resultInt = jo.getIntValue(key);
		return resultInt;
	}
	
	public String getStringByRecord(Record re,String key) {
		String str = JSON.toJSONString(re);
		JSONObject jo = JSON.parseObject(str);
		String resultStr = jo.getString(key);
		return resultStr;
	}
	
	public double getDoubleByRecord(Record re,String key) {
		String str = JSON.toJSONString(re);
		JSONObject jo = JSON.parseObject(str);
		double resultDou = jo.getDoubleValue(key);
		return resultDou;
	}
	
	public JSONArray getJSONArrayByListRecord(List<Record> lr) {
		String str = JSON.toJSONString(lr);
		JSONArray ja = JSON.parseArray(str);
		return ja;
	}
	
	public JSONObject getSimpleReply(int error,String message) {
		JSONObject jo = new JSONObject();
		jo.put("error", error);
		if(message!=null) {
			jo.put("message", message);
		}
		return jo;
	}
}

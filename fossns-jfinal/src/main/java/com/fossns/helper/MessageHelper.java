package com.fossns.helper;

import com.fossns.model.ChatDetail;

public class MessageHelper {

	public boolean sendSystemMessage(int customer_id,String message) {
		ChatDetail model = new ChatDetail();
		model.setSenderId(0);
		model.setReceiverId(customer_id);
		model.setContent(message);
		model.setType(0);
		try {
			if(model.save()) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
}

package com.fossns.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseChatDetail<M extends BaseChatDetail<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setSenderId(java.lang.Integer senderId) {
		set("sender_id", senderId);
	}
	
	public java.lang.Integer getSenderId() {
		return getInt("sender_id");
	}

	public void setReceiverId(java.lang.Integer receiverId) {
		set("receiver_id", receiverId);
	}
	
	public java.lang.Integer getReceiverId() {
		return getInt("receiver_id");
	}

	public void setType(java.lang.Integer type) {
		set("type", type);
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}
	
	public java.lang.String getContent() {
		return getStr("content");
	}

	public void setCreatedAt(java.util.Date createdAt) {
		set("created_at", createdAt);
	}
	
	public java.util.Date getCreatedAt() {
		return get("created_at");
	}

}

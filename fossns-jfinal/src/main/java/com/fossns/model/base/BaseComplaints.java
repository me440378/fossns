package com.fossns.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseComplaints<M extends BaseComplaints<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}
	
	public java.lang.String getContent() {
		return getStr("content");
	}

	public void setIsChecking(java.lang.Boolean isChecking) {
		set("is_checking", isChecking);
	}
	
	public java.lang.Boolean getIsChecking() {
		return get("is_checking");
	}

	public void setCustomerId(java.lang.Integer customerId) {
		set("customer_id", customerId);
	}
	
	public java.lang.Integer getCustomerId() {
		return getInt("customer_id");
	}

}

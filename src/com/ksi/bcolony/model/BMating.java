package com.ksi.bcolony.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EP_BCOLONY_BMATING")
public class BMating extends BFormBaseModel {

	private String partnerId;
	private String comments;
	private String matingStrain;
	private String barCode;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getMatingStrain() {
		return matingStrain;
	}

	public void setMatingStrain(String matingStrain) {
		this.matingStrain = matingStrain;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

}

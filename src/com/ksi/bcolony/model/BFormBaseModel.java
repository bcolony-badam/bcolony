package com.ksi.bcolony.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BFormBaseModel extends SiteBaseModel {

	private String bformId;

	public String getBformId() {
		return bformId;
	}

	public void setBformId(String bformId) {
		this.bformId = bformId;
	}

}

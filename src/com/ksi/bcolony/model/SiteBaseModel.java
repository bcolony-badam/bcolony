package com.ksi.bcolony.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SiteBaseModel extends BaseModel {

	@Column(updatable = false)
	private int siteId;

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
}

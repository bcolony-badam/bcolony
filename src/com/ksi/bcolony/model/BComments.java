package com.ksi.bcolony.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EP_BCOLONY_BCOMMENTS")
public class BComments extends BFormBaseModel {

	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

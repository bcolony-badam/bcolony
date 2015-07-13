package com.ksi.bcolony.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EP_BCOLONY_BADVERSE_EFFECTS")
public class BAdverseEffects extends BFormBaseModel {

	private String comment;
	private String adverseEffectType;
	private String attachmentId;

	public String getAdverseEffectType() {
		return adverseEffectType;
	}

	public void setAdverseEffectType(String adverseEffectType) {
		this.adverseEffectType = adverseEffectType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

}

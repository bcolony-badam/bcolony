package com.ksi.bcolony.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EP_BCOLONY_BDISPOSITION_HISTORY")
public class BDispositionHistory extends BFormBaseModel {

	private String dispositionStatus;
	private String studyNo;

	public String getDispositionStatus() {
		return dispositionStatus;
	}

	public void setDispositionStatus(String dispositionStatus) {
		this.dispositionStatus = dispositionStatus;
	}

	public String getStudyNo() {
		return studyNo;
	}

	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}

}

package com.ksi.bcolony.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EP_BCOLONY_CRYOPRESERVATIONS")
public class BCryoPreservations extends BFormBaseModel {

	private String matingId;
	private String embryoStage;
	private String embryoGenoType;
	private String embryoGeneration;
	private int noFrozen;
	private String workingDewarType;
	private String storageDewarType;
	private String dewarId;
	private String cryoPreservationType;
	private String frozenBy;
	private long frozenDate;
	private String strawId;
	private String comments;

	public String getMatingId() {
		return matingId;
	}

	public void setMatingId(String matingId) {
		this.matingId = matingId;
	}

	public String getEmbryoStage() {
		return embryoStage;
	}

	public void setEmbryoStage(String embryoStage) {
		this.embryoStage = embryoStage;
	}

	public String getEmbryoGenoType() {
		return embryoGenoType;
	}

	public void setEmbryoGenoType(String embryoGenoType) {
		this.embryoGenoType = embryoGenoType;
	}

	public String getEmbryoGeneration() {
		return embryoGeneration;
	}

	public void setEmbryoGeneration(String embryoGeneration) {
		this.embryoGeneration = embryoGeneration;
	}

	public int getNoFrozen() {
		return noFrozen;
	}

	public void setNoFrozen(int noFrozen) {
		this.noFrozen = noFrozen;
	}

	public String getWorkingDewarType() {
		return workingDewarType;
	}

	public void setWorkingDewarType(String workingDewarType) {
		this.workingDewarType = workingDewarType;
	}

	public String getStorageDewarType() {
		return storageDewarType;
	}

	public void setStorageDewarType(String storageDewarType) {
		this.storageDewarType = storageDewarType;
	}

	public String getDewarId() {
		return dewarId;
	}

	public void setDewarId(String dewarId) {
		this.dewarId = dewarId;
	}

	public String getCryoPreservationType() {
		return cryoPreservationType;
	}

	public void setCryoPreservationType(String cryoPreservationType) {
		this.cryoPreservationType = cryoPreservationType;
	}

	public String getFrozenBy() {
		return frozenBy;
	}

	public void setFrozenBy(String frozenBy) {
		this.frozenBy = frozenBy;
	}

	public long getFrozenDate() {
		return frozenDate;
	}

	public void setFrozenDate(long frozenDate) {
		this.frozenDate = frozenDate;
	}

	public String getStrawId() {
		return strawId;
	}

	public void setStrawId(String strawId) {
		this.strawId = strawId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}

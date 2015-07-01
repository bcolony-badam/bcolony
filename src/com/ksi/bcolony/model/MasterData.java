package com.ksi.bcolony.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "BCOLONY_MASTER_DATA")
public class MasterData {

	@Id
	@GenericGenerator(name = "seq_id", strategy = "com.ksi.bcolony.util.JavaSequenceGenerator")
	@GeneratedValue(generator = "seq_id")
	private String id;

	@Column(updatable = false)
	private int customerId;

	@Column(updatable = false)
	private String type;

	@Column(updatable = false)
	private String name;

	@Column(updatable = false)
	private int speciesId;

	private String description;

	@Column(updatable = false)
	private String createdBy;

	private String modifiedBy;

	@Column(updatable = false)
	private long createdOn;

	private long modifiedOn;

	@Transient
	private String spName;

	private int deleted;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(int speciesId) {
		this.speciesId = speciesId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public long getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((MasterData) obj).id);
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

}

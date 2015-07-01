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
@Table(name = "BCOLONY_GROUP_MSTR")
public class GroupMstr {
	@Id
	@GenericGenerator(name = "seq_id", strategy = "com.ksi.bcolony.util.JavaSequenceGenerator")
	@GeneratedValue(generator = "seq_id")
	private String id;

	@Column(updatable = false)
	private int customerId;

	@Column(updatable = false)
	private String name;

	@Column(updatable = false)
	private int speciesId;

	private String description;

	@Column(updatable = false)
	private String createdBy;

	private String modifiedBy;

	@Column(updatable = false)
	private Date createdOn;

	private Date modifiedOn;

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

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
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
		return this.id.equals(((GroupMstr) obj).id);
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

}

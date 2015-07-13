package com.ksi.bcolony.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EP_BCOLONY_BFORM")
public class BForm extends SiteBaseModel {

	@Column(updatable = false)
	private String alias;

	@Column(name = "19bref")
	private String _19bref;

	private String group;
	private String coatColor;
	private String genoType;
	private String constructName;
	private String lineNumber;
	private String generation;
	private String backGroundStrain;

	private String animalId;
	private String fanimalId;
	private String manimalId;

	private int culled;
	private Date culledDate;

	private int hided;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String get_19bref() {
		return _19bref;
	}

	public void set_19bref(String _19bref) {
		this._19bref = _19bref;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getCoatColor() {
		return coatColor;
	}

	public void setCoatColor(String coatColor) {
		this.coatColor = coatColor;
	}

	public String getGenoType() {
		return genoType;
	}

	public void setGenoType(String genoType) {
		this.genoType = genoType;
	}

	public String getConstructName() {
		return constructName;
	}

	public void setConstructName(String constructName) {
		this.constructName = constructName;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getGeneration() {
		return generation;
	}

	public void setGeneration(String generation) {
		this.generation = generation;
	}

	public String getBackGroundStrain() {
		return backGroundStrain;
	}

	public void setBackGroundStrain(String backGroundStrain) {
		this.backGroundStrain = backGroundStrain;
	}

	public String getAnimalId() {
		return animalId;
	}

	public void setAnimalId(String animalId) {
		this.animalId = animalId;
	}

	public String getFanimalId() {
		return fanimalId;
	}

	public void setFanimalId(String fanimalId) {
		this.fanimalId = fanimalId;
	}

	public String getManimalId() {
		return manimalId;
	}

	public void setManimalId(String manimalId) {
		this.manimalId = manimalId;
	}

	public int getCulled() {
		return culled;
	}

	public void setCulled(int culled) {
		this.culled = culled;
	}

	public Date getCulledDate() {
		return culledDate;
	}

	public void setCulledDate(Date culledDate) {
		this.culledDate = culledDate;
	}

	public int getHided() {
		return hided;
	}

	public void setHided(int hided) {
		this.hided = hided;
	}

}

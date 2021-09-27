package com.takehome.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "College")
public class CollegeInfo {
	@Id
	@Column(name = "name")
	private String collegeName;
	@Column(name = "in_state")
    private double inStateTuition = 0;
	@Column(name = "out_state")
    private double outOfStateTuition = 0;
	@Column(name = "housing")
    private double housing = 0;
	
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public double getInStateTuition() {
		return inStateTuition;
	}
	public void setInStateTuition(double inStateTuition) {
		this.inStateTuition = inStateTuition;
	}
	public double getOutOfStateTuition() {
		return outOfStateTuition;
	}
	public void setOutOfStateTuition(double outOfStateTuition) {
		this.outOfStateTuition = outOfStateTuition;
	}
	public double getHousing() {
		return housing;
	}
	public void setHousing(double housing) {
		this.housing = housing;
	}
	
	
}

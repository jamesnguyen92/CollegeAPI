package com.takehome.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class CollegeInfoDTO {
	
	private String collegeName;
    private double cost = 0;
    
    public CollegeInfoDTO(CollegeInfo college, boolean housing) {
		this.setCollegeName(college.getCollegeName());
		this.setCost(college.getInStateTuition());
		if (housing) this.setCost(college.getInStateTuition() + college.getHousing());
	}
   
	/*public CollegeInfoDTO(List<CollegeInfo> college, Boolean housing) {
		for(CollegeInfo col : college) {
			CollegeInfoDTO t = new CollegeInfoDTO(col, housing);
		}
	}*/

	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}	
}

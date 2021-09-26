package com.takehome.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "College")
public class CollegeInfo {
	@Id
	@Column(name = "name")
	private String collegeName;
	@Column(name = "inState")
    private Long inStateTuition;
	@Column(name = "outState")
    private Long outOfStateTuition;
	@Column(name = "housing")
    private Long roomAndBoard;
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public Long getInStateTuition() {
		return inStateTuition;
	}
	public void setInStateTuition(Long inStateTuition) {
		this.inStateTuition = inStateTuition;
	}
	public Long getOutOfStateTuition() {
		return outOfStateTuition;
	}
	public void setOutOfStateTuition(Long outOfStateTuition) {
		this.outOfStateTuition = outOfStateTuition;
	}
	public Long getRoomAndBoard() {
		return roomAndBoard;
	}
	public void setRoomAndBoard(Long roomAndBoard) {
		this.roomAndBoard = roomAndBoard;
	}
	
}

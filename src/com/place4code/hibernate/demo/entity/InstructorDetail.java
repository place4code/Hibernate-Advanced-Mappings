package com.place4code.hibernate.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//annotate the class as entity and map to db
@Entity
@Table(name="instructor_detail")
public class InstructorDetail {
	
	//annotate the fields with database column names
	//define fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="youtube_channel")
	private String youtubeChannel;
	
	@Column(name="hobby")
	private String hobby;
	
	//BI-direction
	//look at the instructorDetail property in the Instructor class:
	@OneToOne(mappedBy="instructorDetail", cascade=CascadeType.ALL) 
	private Instructor instructor;

	//create constructor
	public InstructorDetail() {}
	public InstructorDetail(String youtubeChannel, String hobby) {
		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYoutubeChannel() {
		return youtubeChannel;
	}
	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	//generate toString method
	@Override
	public String toString() {
		return "InstructorDetail [id=" + id + ", youtubeChannel=" + youtubeChannel + ", hobby=" + hobby + "]";
	}

}

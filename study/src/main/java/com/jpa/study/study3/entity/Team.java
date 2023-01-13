package com.jpa.study.study3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Team {
	
	@Id
	@Column(name = "TEAM_ID")
	private String id;
	
	private String name;

}

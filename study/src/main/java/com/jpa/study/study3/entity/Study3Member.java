package com.jpa.study.study3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "STUDY3_MEMBER")
public class Study3Member {
	
	@Id
	@Column(name = "MEMBER_ID")
	private String id;
	
	private String username;
	
	// 연관 관계 매핑
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;
	
	// 연관 관계 설정
	public void setTeam(Team team) {
		this.team = team;
	}
	
}

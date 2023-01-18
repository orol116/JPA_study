package com.jpa.study.study5.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Study5Team {
	
	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	
	private String name;
	
	@OneToMany
	@JoinColumn(name = "TEAM_ID") // MEMBER 테이블의 TEAM_ID (FK)
	private List<Study5Member> members = new ArrayList<>();
	
	public void addMember(Study5Member member) {
		this.members.add(member);
		
		// 무한루프에 빠지지 않도록 체크
		if (member.getTeam() != this) { 
			member.setTeam(this);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Study5Member> getMembers() {
		return members;
	}

	public void setMembers(List<Study5Member> members) {
		this.members = members;
	}
}

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
public class Team {
	
	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	
	private String name;
	
	@OneToMany
	@JoinColumn(name = "TEAM_ID") // MEMBER 테이블의 TEAM_ID (FK)
	private List<Member> members = new ArrayList<>();
	
	public void addMember(Member member) {
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

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
}

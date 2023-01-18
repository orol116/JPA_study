package com.jpa.study.study5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Study5Member {
	
	/* 양방향은 외래키가 있는 쪽이 연관관계의 주인이다.
	 * - 1:N, N:1 연관관계는 항상 N에 외래키가 있다.
	 * - JPA는 외래키를 관리할 때 연관관계의 주인만 사용한다.   
	 *   
	 * 양방향 연관관계는 항상 서로를 참조해야 한다.
	 * - 어느 한 쪽만 참조하면 양방향 연관관계가 성립하지 않는다.
	 */
	
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	private String username;
	
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Study5Team team;
	
	public void setTeam(Study5Team team) {
		this.team = team;
		
		// 무한루프에 빠지지 않도록 체크
		if (!team.getMembers().contains(this)) {
			team.getMembers().add(this);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Study5Team getTeam() {
		return team;
	}
}

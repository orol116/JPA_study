package com.jpa.study.study3.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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
	private Study3Team team;
	
	// 연관 관계 설정
	public void updateTeam(Study3Team team) {
		
		/* 여태 고려하지 않은 문제가 있다.
		 * 
		 * 만약 member1이 team1로 연관관계가 맺어져 있다가
		 * team2로 연관관계를 바꾸게 되면 
		 * team1과는 삭제되지 않은 관계가 되어버린다.
		 * 따라서 이런 경우에는 삭제 코드를 추가해주어야 한다.
		 */
		
		// 기존 팀과 관계를 제거
		if (this.team != null) {
			this.team.getMembers().remove(this);
		}
		
		this.team = team;
		team.getMembers().add(this);
	}
	
}

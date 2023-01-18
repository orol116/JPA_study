package com.jpa.study.study3.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Study3Team {
	
	@Id
	@Column(name = "TEAM_ID")
	private String id;
	
	private String name;
	
	/* 양방향 연관관계 매핑을 위한 추가 
	 * 
	 * - 엄밀히 얘기하면 객체에는 양방향 연관관계는 없다.
	 *   서로 다른 단방향 연관관계 2개를 애플리케이션 로직으로 잘 묶어
	 *   양방향처럼 보이게 할 뿐이다.
	 *   반면에 DB 테이블은 외래 키 하나로 양쪽이 서로 조인할 수 있다.
	 *   따라서 테이블은 외래 키 하나만으로 양방향 연관관계를 맺는다.
	 *   
	 * - Entity를 양방향 연관관계로 설정하면 객체의 참조는 둘인데 외래 키는 하나이다.
	 *   따라서 둘 사이의 차이가 발생...
	 * - 이런 차이로 인해 JPA에선 두 객체 연관관계 중 하나를 정해서 테이블의 외래키를 
	 *   관리해야 하는데 이것을 연관관계의 주인이라 한다.
	 * - 이 연관관계의 주인을 누구로 정할지는 mappedBy 속성을 사용하면 된다.
	 *   (주인이 아닌 곳에 mappedBy 설정 O, 주인에게 설정하는게 아님)
	 *   
	 * - 연관관계의 주인을 정한다는 것은 외래 키 관리자를 선택하는 것이다.
	 *   -> 연관관계의 주인 == 외래 키가 있는 곳
	 */
	@OneToMany(mappedBy = "team")
	private List<Study3Member> members = new ArrayList<>();

}

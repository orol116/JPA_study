package com.jpa.study.study5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Member1vs1 {
	
	/* 1:1 관계이므로 객체 매핑에 @OneToOne을 사용했고
	 * DB에는 LOCKER_ID 외래키에 유니크 제약 조건을 추가했다.
	 * 참고로 이 관계는 N:1 단방향과 거의 비슷하다.
	 */

	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	private String username;
	
	@OneToOne(mappedBy = "member")
//	@JoinColumn(name = "LOCKER_ID")
	private Locker1vs1 locker;
	
}

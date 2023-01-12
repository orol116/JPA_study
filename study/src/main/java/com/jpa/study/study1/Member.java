package com.jpa.study.study1;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "MEMBER", uniqueConstraints = {@UniqueConstraint( // 유니크 제약조건을 만들어주는 기능
		name = "NAME_AGE_UNIQUE",
		columnNames = {"NAME", "AGE"}
)})
public class Member {
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "NAME", nullable = false, length = 10) // 추가
	private String name;
	
	// 컬럼명과 필드명이 같으면 @Column 생략 가능
	private Integer age;
	
	
	/** 요구사항 추가로 인한 밑의 요소 추가
	 * 
	 *  1. 회원은 일반 회원과 관리자로 구분
	 *  2. 회원 가입일과 수정일이 있어야함
	 *  3. 회원을 설명할 수 있는 필드가 있어야 함, 이 필드는 길이 제한이 없음
	 */
	@Enumerated(EnumType.STRING)
	private RoleType roleType;  // ADMIN, USER
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;
	
	@Lob
	private String description;
	
}

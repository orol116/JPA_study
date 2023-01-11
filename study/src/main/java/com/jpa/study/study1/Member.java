package com.jpa.study.study1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "MEMBER")
public class Member {
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "NAME")
	private String username;
	
	// 컬럼명과 필드명이 같으면 @Column 생략 가능
	private Integer age;

}

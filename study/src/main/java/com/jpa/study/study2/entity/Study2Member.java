package com.jpa.study.study2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "STUDY2_MEMBER")
public class Study2Member {
	
	@Id
	@GeneratedValue // 전략 생략 시 AUTO 전략 설정
	@Column(name = "MEMBER_ID")
	private Long id;
	
	private String name;
	
	private String city;
	private String street;
	private String zipcode;
}

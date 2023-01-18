package com.jpa.study.study5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Study5Locker1vs1 {
	
	@Id @GeneratedValue
	@Column(name = "LOCKER_ID")
	private Long id;
	
	private String name;
	
//	@OneToOne(mappedBy = "locker")
	@OneToOne
	@JoinColumn(name = "MEMBER_ID")
	private Study5Member member;

}

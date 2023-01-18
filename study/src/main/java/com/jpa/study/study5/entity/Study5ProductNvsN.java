package com.jpa.study.study5.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Study5ProductNvsN {
	
	@Id @Column(name = "PRODUCT_ID")
	private String id;
	private String name;
	
	@ManyToMany(mappedBy = "products") // 역방향 추가
	private List<Study5MemberNvsN> members;

}

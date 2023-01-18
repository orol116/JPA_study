package com.jpa.study.study5.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Study5MemberNvsN {
	
	@Id @Column(name = "MEMBER_ID")
	private String id;
	private String username;
	
	@ManyToMany
	@JoinTable(name = "MEMBER_PRODUCT"
			 , joinColumns = @JoinColumn(name = "MEMBER_ID")
			 , inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
	private List<Study5ProductNvsN> products = new ArrayList<>();
	
	// 역방향 (클래스, N:N을 푸는 연결 엔티티)
	@OneToMany(mappedBy = "member")
	private List<Study5MemberProduct> memberProducts;
	
	@OneToMany(mappedBy = "member")
	private List<Study5Order> orders = new ArrayList<>();

}

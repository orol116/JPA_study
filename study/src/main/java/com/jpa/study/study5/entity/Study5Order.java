package com.jpa.study.study5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Study5Order {
	
	// 추천하는 기본 키 생성 전략은 DB에서 자동으로 생성해주는
	// 대리키를 Long 값으로 사용하는 것이다.
	// 장점은 간편하고 거의 영구적으로 쓸 수 있으며 비즈니스에 의존하지 않는다.
	// 또한 ORM 매핑 시에 복합키를 만들지 않아도 되므로 간단히 매핑을 완성할 수 있다.
	
	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Study5MemberNvsN member;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Study5ProductNvsN product;
	
	private int orderAmount;

}

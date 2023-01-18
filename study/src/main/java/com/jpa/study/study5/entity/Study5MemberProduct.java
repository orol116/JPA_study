package com.jpa.study.study5.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(Study5MemberProductId.class)
public class Study5MemberProduct {
	
	/* 회원상품 엔티티는 기본키가 
	 * MEMBER_ID와 PRODUCT_ID로 이루어진 복합 기본키이다.
	 * 
	 * JPA에서 복합키를 사용하려면 별도의 식별자 클래스를 만들어야 한다.
	 * 그리고 엔티티에 @IdClass를 사용해서 식별자 클래스를 지정하면 된다.
	 * 여기서는 MemberProductId 클래스를 복합키를 위한 식별자 클래스로 사용한다.
	 */
	
	@Id @ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Study5MemberNvsN member; // MemberProductId.member와 연결
	
	@Id @ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Study5ProductNvsN product; // MemberProductId.product와 연결
	
	private int orderAmount;
}

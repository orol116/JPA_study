package com.jpa.study.study2.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Study2Order {

	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;
	
	@Column(name = "MEMBER_ID")
	private Long memberId;
	
	@Temporal(TemporalType.TIMESTAMP) // 생략 시 동일하게 기본 값 설정
	private Date orderDate;     // 주문 날짜
	
	@Enumerated(EnumType.STRING)
	private Study2OrderStatus status; // 주문 상태
}

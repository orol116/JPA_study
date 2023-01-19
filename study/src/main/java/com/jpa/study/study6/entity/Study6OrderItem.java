package com.jpa.study.study6.entity;

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
public class Study6OrderItem {
	
	@Id @GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ITEM_ID")
	private Study6Item item; // 주문 상품
	
	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Study6Order order; // 주문
	
	private int orderPrice; // 주문 가격
	private int count;      // 주문 수량

}

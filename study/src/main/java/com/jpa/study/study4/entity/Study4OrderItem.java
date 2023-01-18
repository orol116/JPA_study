package com.jpa.study.study4.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ORDER_ITEM")
public class Study4OrderItem {
	
	@Id @GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ITEM_ID")
	private Study4Item item; // 주문 상품
	
	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Study4Order order; // 주문
	
	private int orderPrice; // 주문 가격
	private int count;      // 주문 수량

}

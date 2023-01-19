package com.jpa.study.study6.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Study6Order")
public class Study6Order {
	
	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Study6Member member; // 주문 회원
	
	@OneToMany(mappedBy = "order")
	private List<Study6OrderItem> orderItems = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "DELIVERY_ID")
	private Study6Delivery delivery; // 배송 정보
	
	private Date orderDate; // 주문 시간
	
	@Enumerated(EnumType.STRING)
	private Study6OrderStatus status;
	
	// 연관 관계 메서드 //
	public void setMember(Study6Member member) {
		
		// 기존 관계 제거
		if (this.member != null) {
			this.member.getOrders().remove(this);
		}
		
		this.member = member;
		member.getOrders().add(this);
	}
	
	public void addOrderItem(Study6OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	public void setDelivery(Study6Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}

}

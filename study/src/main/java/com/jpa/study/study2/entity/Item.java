package com.jpa.study.study2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Item {
	
	@Id
	@GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;
	
	private String name;	   // 상품 이름
	private int price;		   // 상품 가격
	private int stockQuantity; // 재고 수량

}

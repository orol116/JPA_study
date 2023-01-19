package com.jpa.study.study6.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Study6Item {
	
	@Id @GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;
	
	private String name;	   // 이름
	private int price;		   // 가격
	private int stockQuantity; // 재고수량
	
	@ManyToMany(mappedBy = "items")
	private List<Study6Category> categories = new ArrayList<>();

}

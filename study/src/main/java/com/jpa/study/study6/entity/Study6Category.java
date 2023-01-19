package com.jpa.study.study6.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Study6Category {
	
	@Id @GeneratedValue
	@Column(name = "CATEGORY_ID")
	private Long id;
	
	private String name;
	
	@ManyToMany
	@JoinTable(name = "CATEGORY_ITEM"
			 , joinColumns = @JoinColumn(name = "CATEGORY_ID")
			 , inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
	private List<Study6Item> items = new ArrayList<>();
	
	// 카테고리의 계층 구조를 위한 필드들
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	private Study6Category parent;
	
	@OneToMany(mappedBy = "parent")
	private List<Study6Category> child = new ArrayList<>();
	
	// 연관 관계 메서드 //
	public void addChildCategory(Study6Category child) {
		this.child.add(this);
		child.setParent(this);
	}

	public void addItem(Study6Item item) {
		items.add(item);
	}
}

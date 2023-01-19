package com.jpa.study.study6.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Study6Delivery {
	
	@Id @GeneratedValue
	@Column(name = "DELIVERY_ID")
	private Long id;
	
	@OneToOne(mappedBy = "delivery")
	private Study6Order order;
	
	private String city;
	private String street;
	private String zipcode;
	
	@Enumerated(EnumType.STRING)
	private Study6DeliveryStatus status;

}

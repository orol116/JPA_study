package com.jpa.study.study5;

import java.util.List;

import javax.persistence.EntityManager;

import com.jpa.study.study5.entity.MemberNvsN;
import com.jpa.study.study5.entity.MemberProduct;
import com.jpa.study.study5.entity.MemberProductId;
import com.jpa.study.study5.entity.ProductNvsN;

public class Study5Main {

	public static void main(String[] args) {
		
		/* N:N 연관관계 정리
		 * 
		 * 식별 관계
		 * - 받아온 식별자를 기본키 + 외래키로 사용한다.
		 * 
		 * 비식별 관계
		 * - 받아온 식별자는 외래키로만 사용하고 새로운 식별자를 추가한다.
		 * 
		 * 객체의 입장에서 보면 비식별 관계를 사용하는 것이 복합키를 위한 식별자 클래스를 만들지 않아도 되므로
		 * 단순하고 편리하게 ORM 매핑을 할 수 있다.
		 * 이러한 이유로 식별 관계보다는 비식별 관계를 더 추천한다.
		 */
		
	}
	
	// 저장
	public static void save(EntityManager manager) {
		
		ProductNvsN productA = new ProductNvsN();
		productA.setId("productA");
		productA.setName("상품A");
		manager.persist(productA);
		
		MemberNvsN member1 = new MemberNvsN();
		member1.setId("member1");
		member1.setUsername("회원1");
		member1.getProducts().add(productA); // 연관관계 설정
		manager.persist(member1);
		
	}
	
	// 탐색
	public static void find(EntityManager manager) {
		
		MemberNvsN member = manager.find(MemberNvsN.class, "member1");
		List<ProductNvsN> products = member.getProducts(); // 객체 그래프 탐색
		for (ProductNvsN product : products) {
			System.out.println("product.name = " + product.getName());
		}
		
	}
	
	// 역방향 탐색
	public static void findInverse(EntityManager manager) {
		
		ProductNvsN product = manager.find(ProductNvsN.class, "productA");
		List<MemberNvsN> members = product.getMembers();
		for (MemberNvsN member : members) {
			System.out.println("member = " + member.getUsername());
		}
		
	}
	
	// 식별 관계 저장하는 코드
	public static void saveNvsN(EntityManager manager) {
		
		// 회원 상품 엔티티는 DB에 저장될 때 연관된 회원의 식별자와 상품의 식별자를
		// 가져와서 자신의 기본 키 값으로 사용한다.
		
		// 회원 저장
		MemberNvsN member1 = new MemberNvsN();
		member1.setId("member1");
		member1.setUsername("회원1");
		manager.persist(member1);
		
		// 상품 저장
		ProductNvsN productA = new ProductNvsN();
		productA.setId("productA");
		productA.setName("상품1");
		manager.persist(productA);
		
		// 회원 상품 저장
		MemberProduct memberProduct = new MemberProduct();
		memberProduct.setMember(member1);   // 주문 회원 - 연관관계 설정
		memberProduct.setProduct(productA); // 주문 상품 - 연관관계 설정
		memberProduct.setOrderAmount(2);    // 주문 수량
		
		manager.persist(memberProduct);
		
	}
	
	// 식별 관계 조회 코드
	public static void findNvsN(EntityManager manager) {
		
		// 기본 키 값 생성
		MemberProductId memberProductId = new MemberProductId();
		memberProductId.setMember("member1");
		memberProductId.setProduct("productA");
		
		MemberProduct memberProduct = manager.find(MemberProduct.class, memberProductId);
		
		MemberNvsN member = memberProduct.getMember();
		ProductNvsN product = memberProduct.getProduct();
		
		System.out.println("member = " + member.getUsername());
		System.out.println("product = " + product.getName());
		System.out.println("orderAmount = " + memberProduct.getOrderAmount());
	}
}

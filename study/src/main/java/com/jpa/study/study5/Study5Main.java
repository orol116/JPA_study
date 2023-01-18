package com.jpa.study.study5;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jpa.study.study5.entity.Study5MemberNvsN;
import com.jpa.study.study5.entity.Study5MemberProduct;
import com.jpa.study.study5.entity.Study5MemberProductId;
import com.jpa.study.study5.entity.Study5ProductNvsN;

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
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaStudy");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			transaction.begin();
			
			// business logic
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			manager.close();
		}
		
	}
	
	// 저장
	public static void save(EntityManager manager) {
		
		Study5ProductNvsN productA = new Study5ProductNvsN();
		productA.setId("productA");
		productA.setName("상품A");
		manager.persist(productA);
		
		Study5MemberNvsN member1 = new Study5MemberNvsN();
		member1.setId("member1");
		member1.setUsername("회원1");
		member1.getProducts().add(productA); // 연관관계 설정
		manager.persist(member1);
		
	}
	
	// 탐색
	public static void find(EntityManager manager) {
		
		Study5MemberNvsN member = manager.find(Study5MemberNvsN.class, "member1");
		List<Study5ProductNvsN> products = member.getProducts(); // 객체 그래프 탐색
		for (Study5ProductNvsN product : products) {
			System.out.println("product.name = " + product.getName());
		}
		
	}
	
	// 역방향 탐색
	public static void findInverse(EntityManager manager) {
		
		Study5ProductNvsN product = manager.find(Study5ProductNvsN.class, "productA");
		List<Study5MemberNvsN> members = product.getMembers();
		for (Study5MemberNvsN member : members) {
			System.out.println("member = " + member.getUsername());
		}
		
	}
	
	// 식별 관계 저장하는 코드
	public static void saveNvsN(EntityManager manager) {
		
		// 회원 상품 엔티티는 DB에 저장될 때 연관된 회원의 식별자와 상품의 식별자를
		// 가져와서 자신의 기본 키 값으로 사용한다.
		
		// 회원 저장
		Study5MemberNvsN member1 = new Study5MemberNvsN();
		member1.setId("member1");
		member1.setUsername("회원1");
		manager.persist(member1);
		
		// 상품 저장
		Study5ProductNvsN productA = new Study5ProductNvsN();
		productA.setId("productA");
		productA.setName("상품1");
		manager.persist(productA);
		
		// 회원 상품 저장
		Study5MemberProduct memberProduct = new Study5MemberProduct();
		memberProduct.setMember(member1);   // 주문 회원 - 연관관계 설정
		memberProduct.setProduct(productA); // 주문 상품 - 연관관계 설정
		memberProduct.setOrderAmount(2);    // 주문 수량
		
		manager.persist(memberProduct);
		
	}
	
	// 식별 관계 조회 코드
	public static void findNvsN(EntityManager manager) {
		
		// 기본 키 값 생성
		Study5MemberProductId memberProductId = new Study5MemberProductId();
		memberProductId.setMember("member1");
		memberProductId.setProduct("productA");
		
		Study5MemberProduct memberProduct = manager.find(Study5MemberProduct.class, memberProductId);
		
		Study5MemberNvsN member = memberProduct.getMember();
		Study5ProductNvsN product = memberProduct.getProduct();
		
		System.out.println("member = " + member.getUsername());
		System.out.println("product = " + product.getName());
		System.out.println("orderAmount = " + memberProduct.getOrderAmount());
	}
}

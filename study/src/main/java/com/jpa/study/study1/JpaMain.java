package com.jpa.study.study1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {

		// EntityMangerFactory 생성
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaStudy");
		
		// EntityManager 생성
		EntityManager manager = factory.createEntityManager();
		
		// 트랜잭션 기능 획득
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			transaction.begin();  // 트랜잭션 시작
			logic(manager);       // 비즈니스 로직 수행
			transaction.commit(); // 트랜잭션 커밋
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback(); // 트랜잭션 롤백
		} finally {
			manager.close(); // EntityManager 종료
		}
	}
	
	// 비즈니스 로직
	public static void logic(EntityManager manager) {
		
		String id = "id1";
		Member member = new Member();
		member.setId(id);
		member.setName("주빈");
		member.setAge(25);
		
		// 등록
		manager.persist(member);
		
		// 수정
		member.setName("윤주빈");
		
		// 한 건 조회
		Member findMember = manager.find(Member.class, id);
		System.out.println("findMember = " + findMember.getName() + ", age = " + findMember.getAge());
		
		// 목록 조회
		List<Member> memberList = manager.createQuery("select m from Member m", Member.class).getResultList();
		System.out.println("memberList.size = " + memberList.size());
		
		// 삭제
		manager.remove(member);
		
	}
}

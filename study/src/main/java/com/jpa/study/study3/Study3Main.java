package com.jpa.study.study3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jpa.study.study3.entity.Study3Member;
import com.jpa.study.study3.entity.Team;

public class Study3Main {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaStudy");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			transaction.begin();
			
			// 비즈니스 로직
			testSave(manager);
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			manager.close();
		}

		factory.close();
	}
	
	/**
	 * 회원과 팀을 저장하는 코드
	 * 
	 * @param manager
	 */
	public static void testSave(EntityManager manager) {
		
		// 팀1 저장
		Team team1 = new Team();
		team1.setId("team1");
		team1.setName("팀1");
		manager.persist(team1); // 저장
		
		// 회원1 저장
		Study3Member member1 = new Study3Member();
		member1.setId("member1");
		member1.setUsername("회원1");
		member1.setTeam(team1); // 연관관계 설정 member1 -> team1
		manager.persist(member1);
		
		// 회원2 저장
		Study3Member member2 = new Study3Member();
		member2.setId("member2");
		member2.setUsername("회원2");
		member2.setTeam(team1); // 연관관계 설정 member2 -> team1
		manager.persist(member2);
	}

}

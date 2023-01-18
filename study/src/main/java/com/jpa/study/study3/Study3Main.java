package com.jpa.study.study3;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jpa.study.study3.entity.Study3Member;
import com.jpa.study.study3.entity.Study3Team;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Study3Main {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaStudy");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			transaction.begin();
			
			// 비즈니스 로직
//			testSave(manager);
//			testSelect(manager);
//			testUpdate(manager);
//			deleteRelation(manager);
			biDirection(manager);
			
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
		Study3Team team1 = new Study3Team("team1", "팀1", null);
		manager.persist(team1); // 저장
		
		// 회원1 저장
		Study3Member member1 = new Study3Member("member1", "회원1", null);
		member1.updateTeam(team1); // 연관관계 설정 member1 -> team1
		manager.persist(member1);
		
		// 회원2 저장
		Study3Member member2 = new Study3Member("member2", "회원2", null);
		member2.updateTeam(team1); // 연관관계 설정 member2 -> team1
		manager.persist(member2);
	}
	
	/**
	 * 회원과 팀을 조회하는 코드
	 * 
	 * @param manager
	 */
	public static void testSelect(EntityManager manager) {
		
		/* 조회 방법
		 * 
		 * 1. 객체 그래프 탐색 (객체 연관관계를 사용한 조회)
		 * 2. 객체지향 쿼리 사용 (JPQL)
		 */
		
		// 객체 그래프 탐색
//		Study3Member member = manager.find(Study3Member.class, "member1");
//		Team team = member.getTeam(); 
//		log.info("팀 이름 = " + team.getName());
		
		// JPQL 사용
		String jpql = "select m from Study3Member m join m.team t where t.name =: teamName";
		
		List<Study3Member> resultList = manager.createQuery(jpql, Study3Member.class)
											   .setParameter("teamName", "팀1")
											   .getResultList();
		
		for (Study3Member data : resultList) {
			log.info("[QUERY] member.username = " + data.getUsername());
		}
	}
	
	/**
	 * 회원과 팀을 수정하는 코드
	 *
	 * @param manager
	 */
	public static void testUpdate(EntityManager manager) {
		
		/* 이 장에서 배운 것
		 * 
		 * - Entity에서 setter를 사용하는 것보다 생성자를 통해 파라미터를 넘기는 것이 좋다.
		 *   setter를 남용하다보면 여기저기서 객체의 값을 변경할 수 있으므로 객체의 일관성을 보장할 수 없다.
		 *   setter는 의도를 알아채기 힘들기 때문에 사용을 자제해야 한다.
		 *   setter를 사용하는 경우엔 setter를 만들기 보다는 의미있는 변경 메서드 이름을 사용해서 만든다.
		 *   단, 양방향 연관관계같이 쉽게 풀리지 않는 부분에서는 Setter를 사용하는게 편할 수 있음
		 */
		
		// 새로운 팀2
		Study3Team team2 = new Study3Team("team2", "팀2", null);
//		manager.persist(team2);
		
		// 회원1에 새로운 팀2으로 설정
		Study3Member member1 = manager.find(Study3Member.class, "member1");
		member1.updateTeam(team2);
	}
	
	/**
	 * 연관관계 제거 코드
	 * 
	 * @param manager
	 */
	public static void deleteRelation(EntityManager manager) {
		
		Study3Member member1 = manager.find(Study3Member.class, "member1");
		Study3Member member2 = manager.find(Study3Member.class, "member2");
		Study3Team team1 = new Study3Team("team1", "팀1", null);
		
		/* 연관된 Entity 삭제
		 * 
		 * - 연관된 엔티티를 삭제하기 위해선 기존에 있던 연관관계를 먼저 제거하고
		 *   삭제를 해야 한다. 그러지 않으면 외래 키 제약조건으로 인해서 DB오류가 발생한다.
		 */
		member1.updateTeam(null);
		member2.updateTeam(null); // 연관관계 제거
		manager.remove(team1); // 팀 삭제
	}
	
	/**
	 * 1:N 방향으로 객체 그래프 탐색 코드
	 * 
	 * @param manager
	 */
	public static void biDirection(EntityManager manager) {
		
		Study3Team team = manager.find(Study3Team.class, "team1");
		List<Study3Member> members = team.getMembers(); // (팀 -> 회원)
														// 객체 그래프 탐색
		
		for (Study3Member member : members) {
			System.out.println("member.username = " + member.getUsername());
		}
	}
	
	/**
	 * 양방향 연관관계 저장
	 * 
	 * @param manager
	 */
	public static void saveMappedBy(EntityManager manager) {
		
		// 팀1 저장
		Study3Team team1 = new Study3Team("team1", "팀1", null);
		manager.persist(team1);
		
		// 회원1 저장
		Study3Member member1 = new Study3Member("member1", "회원1", null);
		member1.updateTeam(team1); // 연관관계 설정 member1 -> team1
		manager.persist(member1);
		
		// 회원2 저장
		Study3Member member2 = new Study3Member("member2", "회원2", null);
		member2.updateTeam(team1); // 연관관계 설정 member2 -> team1
		manager.persist(member2);
	}

	/**
	 * 양방향 연관관계 주의점
	 * 
	 * @param manager
	 */
	public static void saveNonOwner(EntityManager manager) {
		
		// 팀1 저장
		Study3Team team1 = new Study3Team("team1", "팀1", null);
		manager.persist(team1);
		
		// 회원1 저장
		Study3Member member1 = new Study3Member("member1", "회원1", null);
		
		// 양방향 연관관계 설정
		member1.updateTeam(team1);		 // 연관관계 설정 member1 -> team1
		team1.getMembers().add(member1); // 연관관계 설정 team1 -> member1
		manager.persist(member1);
		
		// 회원2 저장
		Study3Member member2 = new Study3Member("member2", "회원2", null);
		
		// 양방향 연관관계 설정
		member2.updateTeam(team1);		 // 연관관계 설정 member2 -> team1 
		team1.getMembers().add(member2); // 연관관계 설정 team1 -> member2
		manager.persist(member2);

		// 주인이 아닌 곳만 연관관계 설정
//		team1.getMembers().add(member1);
//		team1.getMembers().add(member2);
		
//		manager.persist(team1);
		
		// 양방향은 양쪽다 관계를 설정해야 한다. 
		// 그렇지 않으면 JPA를 사용하지 않는 순수한 객체만 사용할 때 문제가 발생한다.
//		List<Study3Member> members = team1.getMembers();
//		System.out.println("members.size = " + members.size());
	}
	
	/**
	 * 양방향 연관관계 리팩토링
	 * 
	 * @param manager
	 */
	public static void refactoring(EntityManager manager) {
		
		/* 양방향 연관관계는 양쪽 모두 신경을 써야 한다.
		 * 
		 * 위 주의사항 메서드처럼 각각 호출하다보면 실수로 둘 중 하나만 호출해서
		 * 양방향이 깨질 수 있다.
		 * 
		 * 아래와 같이 메서드 하나로 양방향 관계를 모두 설정하는 것을
		 * 연관관계 편의 메서드라고 한다.
		 */
		
		Study3Team team1 = new Study3Team("team1", "팀1", null);
		manager.persist(team1);
		
		Study3Member member1 = new Study3Member("member1", "회원1", null);
		member1.updateTeam(team1); // 양방향 설정
		manager.persist(member1);
		
		Study3Member member2 = new Study3Member("member2", "회원2", null);
		member2.updateTeam(team1); // 양방향 설정
		manager.persist(member2);
	}
}

package jpabook.start;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	
	public static void main(String[] args) {
		
		// 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		
		// 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();
		
		// 트랜잭션 획득
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();    // 트랜잭션 시작
			logic(em);	   // 비즈니스 로직 실행
			tx.commit();   // 트랜잭션 커밋
		} catch (Exception e) {
			tx.rollback(); // 트랜잭션 롤백
		} finally {
			em.close();    // 엔티티 매니저 종료
		}
		emf.close();	   // 엔티티 매니저 팩토리 종료
	}
	
	// 연습용 비즈니스 로직
	public static void logic(EntityManager em) {
		
		String id = "jbyun";
		Member member = new Member();
		member.setId(id);
		member.setUsername("주빈");
		member.setAge(24);
		
		// 등록
		em.persist(member);
		// INSERT INTO MEMBER (ID, NAME, AGE) VALUES ('jbyun', '주빈', 24)
		
		// 수정
		member.setAge(25);
		// UPDATE MEMBER SET AGE = 25, NAME = '주빈' WHERE ID = 'jbyun'
		
		// 한 건 조회
		Member findMember = em.find(Member.class, id);
		System.out.println("findMember = " + findMember.getUsername() + ", age = " + findMember.getAge());
		
		// 목록 조회 (JPQL)								
		// FROM에서의 MEMBER는 DB 테이블의 MEMBER가 아닌 엔티티의 MEMBER이다
		// => JPQL은 DB 테이블 정보에 대해 하나도 모름
		List<Member> members = em.createQuery("SELECT M FROM MEMBER M", Member.class).getResultList();
		System.out.println("members.size = " + members.size());
		
		// 삭제
		em.remove(member);
		// DELETE FROM MEMBER WHERE ID = 'jbyun'
	}
}

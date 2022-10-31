package jpabook.start;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // 이 클래스를 테이블과 매핑한다는 것을 JPA에게 알려주는 어노테이션 (엔티티 클래스)
@Table(name = "MEMBER") // 엔티티 클래스에 매핑할 테이블 정보를 알려주는 어노테이션
public class Member {
	
	@Id // 기본 키(Primary Key)에 매핑하는 어노테이션 (식별자 필드)
	@Column(name = "ID")
	private String id;
	
	@Column(name = "NAME")
	private String username;
	
	// 매핑 어노테이션을 생략하면 필드명을 사용해서 컬럼명으로 매핑
	// 단, 대소문자를 구분하는 DB 사용 시 @Column으로 명시해야함
	private Integer age;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	
}
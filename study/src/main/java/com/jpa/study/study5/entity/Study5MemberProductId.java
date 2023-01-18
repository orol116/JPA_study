package com.jpa.study.study5.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Study5MemberProductId implements Serializable {
	
	/* 복합키를 위한 별도의 식별자 클래스 규칙
	 * 
	 * - Serializable을 구현해야 한다.
	 * - equals와 hashCode 메서드를 구현해야 한다.
	 * - 기본 생성자가 있어야 한다.
	 * - 식별자 클래스는 public 이어야 한다.
	 * - IdClass를 사용하는 방법 외에 @EmbeddedId를 사용하는 방법도 있다.
	 */

	private static final long serialVersionUID = 1L;
	
	private String member;  // MemberProduct.member와 연결
	private String product; // MemberProduct.product와 연결

	// hashCode and equals
	@Override
	public int hashCode() {
		return Objects.hash(member, product);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Study5MemberProductId other = (Study5MemberProductId) obj;
		return Objects.equals(member, other.member) && Objects.equals(product, other.product);
	}
	
}

package sparta.spartaboard.domain.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;

/**
 * 모든 엔티가 가져야 할 속성을 가지는 엔티티.
 * 생성일자와 수정일자를 저장하며, 상속으로 사용 가능.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@CreatedDate
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt; // 생성일자

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modifiedAt; // 수정일자
}

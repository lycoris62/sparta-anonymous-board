package sparta.spartaboard.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.spartaboard.model.BaseEntity;
import sparta.spartaboard.post.dto.request.PostCreateRequestDto;

/**
 * 게시글 엔티티.
 */
@Getter
@Entity
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA는 기본 생성자를 필요하므로 최소의 접근제어자로 생성
public class Post extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30) // 제목은 1이상 30 글자 이하
	private String title;

	@Column(nullable = false, length = 20) // 작성자명은 2이상 20 글자 이하
	private String author;

	@Column(nullable = false, length = 128) // 비밀번호는 4이상 50이하 이나, 암호화로 128자 까지 저장
	private String password;

	@Column(nullable = false, columnDefinition = "text") // 글내용은 1이상 65,535 byte 이하
	private String contents;

	private Post(String title, String author, String password, String contents) {
		this.title = title;
		this.author = author;
		this.password = password;
		this.contents = contents;
	}

	public static Post create(PostCreateRequestDto request) {
		return new Post(request.getTitle(), request.getAuthor(), request.getPassword(), request.getContents());
	}
}

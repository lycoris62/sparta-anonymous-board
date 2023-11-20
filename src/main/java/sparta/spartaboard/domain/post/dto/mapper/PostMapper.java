package sparta.spartaboard.domain.post.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import sparta.spartaboard.domain.post.dto.response.PostDetailResponseDto;
import sparta.spartaboard.domain.post.dto.response.PostPreviewResponseDto;
import sparta.spartaboard.domain.post.entity.Post;

/**
 * Post 엔티티를 DTO 로 변환해주는 mapper
 */
@Mapper(
	componentModel = "spring", // 빈으로 등록해줌
	unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PostMapper {
	PostDetailResponseDto toPostDetailResponseDto(Post post);
	PostPreviewResponseDto toPostPreviewResponseDto(Post post);
}

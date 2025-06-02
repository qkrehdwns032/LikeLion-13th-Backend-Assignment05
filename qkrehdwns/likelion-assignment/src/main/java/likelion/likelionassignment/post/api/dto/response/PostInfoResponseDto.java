package likelion.likelionassignment.post.api.dto.response;

import likelion.likelionassignment.post.api.domain.Post;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record PostInfoResponseDto(
    Long postId,
    String title,
    String contents,
    String writer,
    List<String> tagNames
) {
    public static PostInfoResponseDto from(Post post) {
        List<String> tags = post.getPostTags() != null && !post.getPostTags().isEmpty()
            ? post.getPostTags().stream()
            .map(postTag -> postTag.getTag().getName())
            .collect(Collectors.toList())
            : new ArrayList<>();

        return PostInfoResponseDto.builder()
            .postId(post.getPostId())
            .title(post.getTitle())
            .contents(post.getContents())
            .writer(post.getMember().getName())
            .tagNames(tags)
            .build();
    }
}

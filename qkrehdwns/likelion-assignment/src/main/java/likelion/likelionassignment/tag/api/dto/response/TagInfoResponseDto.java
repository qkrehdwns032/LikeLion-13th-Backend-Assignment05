package likelion.likelionassignment.tag.api.dto.response;

import likelion.likelionassignment.tag.domain.Tag;
import lombok.Builder;

@Builder
public record TagInfoResponseDto(
    Long tagId,
    String name
) {
    public static TagInfoResponseDto from(Tag tag) {
        return TagInfoResponseDto.builder()
            .tagId(tag.getTagId())
            .name(tag.getName())
            .build();
    }
}

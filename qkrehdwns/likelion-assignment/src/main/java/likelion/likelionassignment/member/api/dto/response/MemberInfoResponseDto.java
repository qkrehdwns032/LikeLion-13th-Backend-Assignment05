package likelion.likelionassignment.member.api.dto.response;

import likelion.likelionassignment.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberInfoResponseDto(
        String name,
        int age,
        String part
) {
    public static MemberInfoResponseDto from(Member member){
        return MemberInfoResponseDto.builder()
                .name(member.getName())
                .age(member.getAge())
                .part(member.getName())
                .build();

    }
}

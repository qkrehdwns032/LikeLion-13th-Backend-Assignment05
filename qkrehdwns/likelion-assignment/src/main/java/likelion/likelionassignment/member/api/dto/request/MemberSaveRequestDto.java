package likelion.likelionassignment.member.api.dto.request;

import likelion.likelionassignment.member.domain.Part;

public record MemberSaveRequestDto (
    String name,
    int age,
    Part part
){

}

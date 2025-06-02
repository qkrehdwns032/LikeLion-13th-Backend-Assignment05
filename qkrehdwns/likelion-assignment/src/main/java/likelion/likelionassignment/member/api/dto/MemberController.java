package likelion.likelionassignment.member.api.dto;

import likelion.likelionassignment.common.error.SuccessCode;
import likelion.likelionassignment.common.template.ApiResTemplate;
import likelion.likelionassignment.member.api.dto.request.MemberSaveRequestDto;
import likelion.likelionassignment.member.api.dto.request.MemberUpdateRequestDto;
import likelion.likelionassignment.member.api.dto.response.MemberInfoResponseDto;
import likelion.likelionassignment.member.api.dto.response.MemberListResponseDto;
import likelion.likelionassignment.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    // 사용자 저장
    @PostMapping("/save")
    public ApiResTemplate<String> memberSave(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        memberService.memberSave(memberSaveRequestDto);
        return ApiResTemplate.successResponse(SuccessCode.MEMEBER_SAVE_SUCCESS, SuccessCode.MEMEBER_SAVE_SUCCESS.getMessage());
    }

    // 사용자 전체 조회
    @GetMapping("/all")
    public ApiResTemplate<MemberListResponseDto> memberFindAll() {
        MemberListResponseDto memberListResponseDto = memberService.memberFindAll();
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, memberListResponseDto);
    }

    // 회원 id를 통해 특정 사용자 조회
    @GetMapping("/{memberId}")
    public ApiResTemplate<MemberInfoResponseDto> memberFindOne(@PathVariable("memberId") Long memberId) {
        MemberInfoResponseDto memberInfoResponseDto = memberService.memberFindOne(memberId);

        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, memberInfoResponseDto);
    }

    @PatchMapping("/{memberId}")
    public ApiResTemplate<MemberUpdateRequestDto> memberUpdate(@PathVariable("memberId") Long memberId,
                                                               @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        memberService.memberUpdate(memberId, memberUpdateRequestDto);

        return ApiResTemplate.successResponse(SuccessCode.MEMBER_UPDATE_SUCCESS, memberUpdateRequestDto);
    }

    @DeleteMapping("/{memberId}")
    public ApiResTemplate<String> memberDelete(@PathVariable("memberId") Long memberId) {
        memberService.memberDelete(memberId);

        return ApiResTemplate.successResponse(SuccessCode.MEMBER_DELETE_SUCCESS, SuccessCode.MEMBER_DELETE_SUCCESS.getMessage());
    }


}

package likelion.likelionassignment.member.application;

import likelion.likelionassignment.common.error.ErrorCode;
import likelion.likelionassignment.common.exception.BusinessException;
import likelion.likelionassignment.member.api.dto.request.MemberSaveRequestDto;
import likelion.likelionassignment.member.api.dto.request.MemberUpdateRequestDto;
import likelion.likelionassignment.member.api.dto.response.MemberInfoResponseDto;
import likelion.likelionassignment.member.api.dto.response.MemberListResponseDto;
import likelion.likelionassignment.member.domain.Member;
import likelion.likelionassignment.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    // 사용자 정보 저장
    @Transactional
    public void memberSave(MemberSaveRequestDto memberSaveRequestDto) {
        Member member = Member.builder()
            .name(memberSaveRequestDto.name())
            .age(memberSaveRequestDto.age())
            .part(memberSaveRequestDto.part())
            .build();
        memberRepository.save(member);
    }

    // 사용자 모두 조회
    public MemberListResponseDto memberFindAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberInfoResponseDto> memberInfoResponseDtoList = members.stream()
            .map(MemberInfoResponseDto::from)
            .toList();
        return MemberListResponseDto.from(memberInfoResponseDtoList);
    }

    // 단일 사용자 조회
    public MemberInfoResponseDto memberFindOne(Long memberId) {
        Member member = memberRepository
            .findById(memberId)
            .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND_EXCEPTION,
                ErrorCode.MEMBER_NOT_FOUND_EXCEPTION.getMessage() + memberId));

        return MemberInfoResponseDto.from(member);
    }

    @Transactional
    public void memberUpdate(Long memberId, MemberUpdateRequestDto MemberUpdateRequestDto) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND_EXCEPTION,
                ErrorCode.MEMBER_NOT_FOUND_EXCEPTION.getMessage() + memberId));

        member.update(MemberUpdateRequestDto);
    }

    @Transactional
    public void memberDelete(Long memberId){
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND_EXCEPTION,
                ErrorCode.MEMBER_NOT_FOUND_EXCEPTION.getMessage() + memberId));

        memberRepository.delete(member);
    }
}

package com.codeit.springwebbasic.member.service;

import com.codeit.springwebbasic.member.dto.request.MemberCreateRequestDto;
import com.codeit.springwebbasic.member.dto.response.MemberResponseDto;
import com.codeit.springwebbasic.member.entity.Member;
import com.codeit.springwebbasic.member.entity.MemberGrade;
import com.codeit.springwebbasic.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto memberCreate(MemberCreateRequestDto requestDto) {
        // 이메일 중복체크
        if (memberRepository.existsByEmail(requestDto.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Member에 @Builder 추가
        Member member = Member.builder()
                .name(requestDto.name())
                .email(requestDto.email())
                .phone(requestDto.phone())
                .grade(MemberGrade.BRONZE)
                .joinedAt(LocalDateTime.now())
                .build();

        Member saved =  memberRepository.save(member);
        return MemberResponseDto.from(member);
    }

    public MemberResponseDto getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> {
                    log.warn("/api/v1/members/{} not found", memberId);
                    return new IllegalArgumentException("Member not found");
                } );
        return MemberResponseDto.from(member);
    }

    public List<MemberResponseDto> searchMembers(String name) {
        List<Member> members = memberRepository.findByNameContaining(name);
        return getMemberResponseDtos(members);
    }

    public List<MemberResponseDto> getAllMembers() {
        List<Member> members =  memberRepository.findAll();
        return getMemberResponseDtos(members);
    }

    // 커맨드+옵션+M
    private List<MemberResponseDto> getMemberResponseDtos(List<Member> members) {
        return members.stream()
                .map(MemberResponseDto::from).collect(Collectors.toList());
    }

}
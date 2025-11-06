package com.codeit.springwebbasic.member.service;

import com.codeit.springwebbasic.member.dto.request.MemberCreateRequestDto;
import com.codeit.springwebbasic.member.entity.Member;
import com.codeit.springwebbasic.member.entity.MemberGrade;
import com.codeit.springwebbasic.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member memberCreate(MemberCreateRequestDto requestDto) {
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

        return memberRepository.save(member);
    }

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found") );
    }

    public List<Member> searchMembers(String name) {
        return memberRepository.findByNameContaining(name);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

}
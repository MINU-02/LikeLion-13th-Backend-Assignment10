package com.kimminwoo.jpademo.member.application;

import com.kimminwoo.jpademo.exception.NotFoundException;
import com.kimminwoo.jpademo.member.api.dto.response.MemberResponseDto;
import com.kimminwoo.jpademo.member.domain.Member;
import com.kimminwoo.jpademo.member.domain.repository.MemberRepository;
import com.kimminwoo.jpademo.post.domain.Post;
import com.kimminwoo.jpademo.post.domain.respository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepo;
    private final PostRepository postRepo;

    @Transactional(readOnly = true)
    public java.util.List<MemberResponseDto> findAll() {
        return memberRepo.findAll().stream()
                .map(MemberResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findOneWithPosts(Long id) {
        Member m = memberRepo.findWithPostsById(id)
                .orElseThrow(() -> new NotFoundException("Member not found: " + id));
        return MemberResponseDto.from(m);
    }

    @Transactional
    public MemberResponseDto createMember(String nickname) {
        Member saved = memberRepo.save(Member.builder().nickname(nickname).build());
        return MemberResponseDto.from(saved);
    }

}


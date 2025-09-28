package com.kimminwoo.jpademo.member.api.dto.response;

import com.kimminwoo.jpademo.member.domain.Member;

import java.util.List;

public record MemberResponseDto(Long id, String nickname, List<PostSummary> posts) {
    public record PostSummary(Long id, String title) {

    }

    public static MemberResponseDto from(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getNickname(),
                member.getPosts().stream()
                        .map(post -> new PostSummary(post.getId(), post.getTitle()))
                        .toList()
        );
    }

}
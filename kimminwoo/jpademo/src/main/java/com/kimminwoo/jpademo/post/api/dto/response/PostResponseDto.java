package com.kimminwoo.jpademo.post.api.dto.response;

import com.kimminwoo.jpademo.post.domain.Post;

public record PostResponseDto(Long id, String title, String content, Long memberId) {

    public static PostResponseDto from(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getMember() != null ? post.getMember().getId() : null
        );
    }

}


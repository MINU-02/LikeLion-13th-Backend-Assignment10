package com.kimminwoo.jpademo.member.api.dto.response;

import java.util.List;

public record MemberDto(Long id, String nickname, List<PostSummary> posts) {
    public record PostSummary(Long id, String title) {

    }
}
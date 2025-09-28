package com.kimminwoo.jpademo.member.domain;

import com.kimminwoo.jpademo.post.domain.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    // 주인 아님 (읽기/탐색용)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String nickname) { // id는 제외!
        this.nickname = nickname;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

}

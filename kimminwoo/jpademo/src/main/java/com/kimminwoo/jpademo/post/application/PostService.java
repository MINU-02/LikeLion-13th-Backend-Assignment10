package com.kimminwoo.jpademo.post.application;

import com.kimminwoo.jpademo.exception.NotFoundException;
import com.kimminwoo.jpademo.member.domain.Member;
import com.kimminwoo.jpademo.member.domain.repository.MemberRepository;
import com.kimminwoo.jpademo.post.api.dto.response.PostResponseDto;
import com.kimminwoo.jpademo.post.domain.Post;
import com.kimminwoo.jpademo.post.domain.respository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepo;
    private final MemberRepository memberRepo;

    @Transactional
    public PostResponseDto create(Long memberId, String title, String content) {
        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new NotFoundException("Member not found: " + memberId));
        Post post = Post.builder().title(title).content(content).member(member).build();
        Post saved = postRepo.save(post);
        return PostResponseDto.from(saved);
    }

    @Transactional(readOnly = true)
    public PostResponseDto get(Long id) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found: " + id));
        return PostResponseDto.from(post);
    }

    @Transactional
    public PostResponseDto update(Long id, String title, String content) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found: " + id));
        post.update(title, content);
        return PostResponseDto.from(post);
    }

    @Transactional
    public void delete(Long id) {
        if (!postRepo.existsById(id)) throw new NotFoundException("Post not found: " + id);
        postRepo.deleteById(id);
    }

}



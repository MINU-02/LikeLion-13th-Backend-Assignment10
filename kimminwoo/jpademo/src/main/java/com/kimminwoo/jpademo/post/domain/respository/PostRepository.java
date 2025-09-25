package com.kimminwoo.jpademo.post.domain.respository;

import com.kimminwoo.jpademo.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> { }


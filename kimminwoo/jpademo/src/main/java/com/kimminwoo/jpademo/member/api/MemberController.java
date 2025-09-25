package com.kimminwoo.jpademo.member.api;

import com.kimminwoo.jpademo.member.api.dto.request.MemberRequests;
import com.kimminwoo.jpademo.member.api.dto.response.MemberDto;
import com.kimminwoo.jpademo.member.application.MemberService;
import com.kimminwoo.jpademo.post.api.dto.request.PostRequests;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    @GetMapping
    public List<MemberDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOneWithPosts(id));
    }

    @PostMapping
    public ResponseEntity<MemberDto> create(@RequestBody MemberRequests.Create body) {
        var res = service.createMember(body.nickname());
        return ResponseEntity.created(URI.create("/members/" + res.id())).body(res);
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<MemberDto> addPost(@PathVariable Long id, @RequestBody PostRequests.Create body) {
        var res = service.addPost(id, body.title(), body.content());
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}


package com.kimminwoo.jpademo.post.api;

import com.kimminwoo.jpademo.post.api.dto.request.PostRequests;
import com.kimminwoo.jpademo.post.api.dto.response.PostDto;
import com.kimminwoo.jpademo.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService service;

    @PostMapping
    public ResponseEntity<PostDto> create(@RequestBody PostRequests.Create body) {
        var res = service.create(body.memberId(), body.title(), body.content());
        return ResponseEntity.created(URI.create("/posts/" + res.id())).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> update(@PathVariable Long id, @RequestBody PostRequests.Update body) {
        return ResponseEntity.ok(service.update(id, body.title(), body.content()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
}


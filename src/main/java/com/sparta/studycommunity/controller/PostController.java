package com.sparta.studycommunity.controller;

import com.sparta.studycommunity.dto.PostRequestDto;
import com.sparta.studycommunity.dto.PostResponseDto;
import com.sparta.studycommunity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto) {
        PostResponseDto createdPost = postService.createPost(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto) {
        PostResponseDto updatedPost = postService.updatePost(postId, requestDto);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("모집글 삭제.");
    }

    @PostMapping("/posts/{postId}/tags")
    public void addTag(@PathVariable Long postId, @RequestParam(name = "id") Long tagId) {
        postService.addTag(postId, tagId);
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<PostResponseDto>> getPosts(
            @RequestParam(name = "tags", required = false) List<Long> tagIds,
            @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostResponseDto> posts = postService.getPosts(tagIds, pageable);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        PostResponseDto post = postService.getPost(id);
        return ResponseEntity.ok(post);
    }
}

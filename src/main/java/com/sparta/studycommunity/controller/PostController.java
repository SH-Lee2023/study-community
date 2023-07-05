package com.sparta.studycommunity.controller;

import com.sparta.studycommunity.dto.ApiResponseDto;
import com.sparta.studycommunity.dto.PostRequestDto;
import com.sparta.studycommunity.dto.PostResponseDto;
import com.sparta.studycommunity.security.UserDetailsImpl;
import com.sparta.studycommunity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto post = postService.createPost(requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<PostResponseDto>> getPosts(
            @RequestParam(name = "tags", required = false) List<Long> tagIds,
            @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostResponseDto> posts = postService.getPosts(tagIds, pageable);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        PostResponseDto post = postService.getPost(id);
        return ResponseEntity.ok().body(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        PostResponseDto post = postService.updatePost(id, requestDto);
        return ResponseEntity.ok().body(post);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().body(new ApiResponseDto("게시글 삭제 성공", HttpStatus.OK.value()));
    }

    @PostMapping("/posts/{id}/scrap")
    public ResponseEntity<ApiResponseDto> scrapPost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
       postService.scrapPost(id, userDetails.getUser());
        return ResponseEntity.ok().body(new ApiResponseDto("게시글 스크랩 성공", HttpStatus.OK.value()));
    }

    @DeleteMapping("/posts/{id}/scrap")
    public ResponseEntity<ApiResponseDto> unscrapPost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.unscrapPost(id, userDetails.getUser());
        return ResponseEntity.ok().body(new ApiResponseDto("게시글 스크랩 취소 성공", HttpStatus.OK.value()));
    }

    @PostMapping("/posts/{postId}/tags")
    public ResponseEntity<ApiResponseDto> addTag(@PathVariable Long postId, @RequestParam Long tagId) {
        postService.addTag(postId, tagId);
        return ResponseEntity.ok().body(new ApiResponseDto("게시글 태그 추가 성공", HttpStatus.OK.value()));
    }
}

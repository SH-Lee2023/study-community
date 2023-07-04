package com.sparta.studycommunity.service;

import com.sparta.studycommunity.dto.PostRequestDto;
import com.sparta.studycommunity.dto.PostResponseDto;
import com.sparta.studycommunity.entity.Post;
import com.sparta.studycommunity.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {

        String title = requestDto.getTitle();
        String contents = requestDto.getContents();
        String imageUrl = requestDto.getImageUrl();
        String tag = requestDto.getTag();

        Post post = new Post(title, contents, imageUrl, tag);
        Post savedPost = postRepository.save(post);
        return new PostResponseDto(savedPost);
    }

    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto) {

        String title = requestDto.getTitle();
        String contents = requestDto.getContents();
        String imageUrl = requestDto.getImageUrl();
        String tag = requestDto.getTag();

        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 모집글을 찾을 수 없습니다."));

        existingPost.setTitle(title);
        existingPost.setContents(contents);
        existingPost.setImageUrl(imageUrl);
        existingPost.setTag(tag);

        Post updatedPost = postRepository.save(existingPost);
        return new PostResponseDto(updatedPost);
    }

    public void deletePost(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 모집글을 찾을 수 없습니다."));


        postRepository.delete(post);
    }
}

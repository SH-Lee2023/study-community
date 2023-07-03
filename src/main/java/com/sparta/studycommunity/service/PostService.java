package com.sparta.studycommunity.service;

import com.sparta.studycommunity.dto.PostResponseDto;
import com.sparta.studycommunity.entity.Post;
import com.sparta.studycommunity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Page<PostResponseDto> getPosts(int page, int size, String sortBy, boolean isAsc, List<String> tags) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Post> postPage = null;

        if(tags == null) {
            postPage = postRepository.findAll(pageable);
        } else {
//          postPage = postRepository.findByTagsIn(tags, pageable);
        }

        return postPage.map(PostResponseDto::new);
    }

    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }
}

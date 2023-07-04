package com.sparta.studycommunity.service;

import com.sparta.studycommunity.dto.PostResponseDto;
import com.sparta.studycommunity.entity.Post;
import com.sparta.studycommunity.entity.PostTag;
import com.sparta.studycommunity.entity.Tag;
import com.sparta.studycommunity.repository.PostRepository;
import com.sparta.studycommunity.repository.PostTagRepository;
import com.sparta.studycommunity.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;

    public void addTag(Long postId, Long tagId) {
        Post post = findPost(postId);
        Tag tag = findTag(tagId);
        Optional<PostTag> duplicatedTag = postTagRepository.findByPostAndTag(post, tag);

        if(duplicatedTag.isPresent()) {
            throw new IllegalArgumentException("중복된 태그를 추가할 수 없습니다.");
        }

        postTagRepository.save(new PostTag(post, tag));
    }

    public Page<PostResponseDto> getPosts(List<Long> tagIds, Pageable pageable) {
        Page<Post> postPage;

        if(tagIds == null) {
            postPage = postRepository.findAll(pageable);
        } else {
            postPage = findPostsWithTags(tagIds, pageable);
        }

        return postPage.map(PostResponseDto::new);
    }

    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
    }

    private Tag findTag(Long id) {
        return tagRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 태그가 존재하지 않습니다.")
        );
    }

    private Page<Post> findPostsWithTags(List<Long> tagIds, Pageable pageable) {
        return postRepository.findAllByPostTagList_TagIdIn(tagIds, pageable);
    }
}

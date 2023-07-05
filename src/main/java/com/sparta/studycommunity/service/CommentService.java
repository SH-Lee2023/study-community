package com.sparta.studycommunity.service;

import com.sparta.studycommunity.dto.CommentRequestDto;
import com.sparta.studycommunity.dto.CommentResponseDto;
import com.sparta.studycommunity.entity.Comment;
import com.sparta.studycommunity.entity.Post;
import com.sparta.studycommunity.entity.User;
import com.sparta.studycommunity.entity.UserRoleEnum;
import com.sparta.studycommunity.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostService postService;
    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        Post post = postService.findPost(requestDto.getPostId());
        Comment comment = new Comment(requestDto.getContents());
        comment.setUser(user);
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(savedComment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user) {
        Comment comment = findComment(id);
        if (!comment.getUser().equals(user)) {
            throw new IllegalArgumentException("사용자가 작성한 댓글만 수정 가능합니다.");
        }
        comment.setContents(requestDto.getContents());
        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long id, User user) {
        Comment comment = findComment(id);
        if (!comment.getUser().equals(user)) {
            throw new IllegalArgumentException("사용자가 작성한 댓글만 삭제 가능합니다.");
        }
        commentRepository.delete(comment);
    }

    private Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
        });
    }
}
package com.sparta.studycommunity.repository;

import com.sparta.studycommunity.entity.Post;
import com.sparta.studycommunity.entity.PostTag;
import com.sparta.studycommunity.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
    Optional<PostTag> findByPostAndTag(Post post, Tag tag);
}

package com.sparta.studycommunity.repository;

import com.sparta.studycommunity.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByNameIn(List<String> tagNames);
}

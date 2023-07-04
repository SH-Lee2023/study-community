package com.sparta.studycommunity.service;

import com.sparta.studycommunity.dto.TagResponseDto;
import com.sparta.studycommunity.entity.Tag;
import com.sparta.studycommunity.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public void addTags(List<String> tagNames) {
        List<Tag> existTags = tagRepository.findAllByNameIn(tagNames);
        List<Tag> newTags = new ArrayList<>();

        for (String tagName : tagNames) {
            if(!isExistTag(tagName, existTags)) {
                Tag tag = new Tag(tagName);
                newTags.add(tag);
            }
        }

        tagRepository.saveAll(newTags);
    }

    public List<TagResponseDto> getAllTags() {
        return tagRepository.findAll().stream().map(TagResponseDto::new).toList();
    }

    private boolean isExistTag(String tagName, List<Tag> existTags) {
        for (Tag existTag : existTags) {
            if(tagName.equals(existTag.getName())) {
                return true;
            }
        }
        return false;
    }
}

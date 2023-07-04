package com.sparta.studycommunity.service;

import com.sparta.studycommunity.dto.LoungesRequestDto;
import com.sparta.studycommunity.dto.LoungesResponseDto;
import com.sparta.studycommunity.entity.Lounges;
import com.sparta.studycommunity.repository.LoungesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoungesService {

    private final LoungesRepository loungesRepository;

    @Autowired
    public LoungesService(LoungesRepository loungesRepository) {
        this.loungesRepository = loungesRepository;
    }

    public LoungesResponseDto createLounge(LoungesRequestDto requestDto) {
        Lounges lounges = new Lounges(requestDto);

        Lounges saveLounges = loungesRepository.save(lounges);

        return new LoungesResponseDto(saveLounges);
    }

    public List<LoungesResponseDto> getLounges() {
        List<Lounges> loungesList = loungesRepository.findAll();
        return loungesList.stream()
                .map(LoungesResponseDto::new)
                .collect(Collectors.toList());
    }

    public void likeLounge(Long loungeId) {
        Lounges lounges = loungesRepository.findById(loungeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lounge ID: " + loungeId));

        lounges.setLikeCount(lounges.getLikeCount() + 1);
        loungesRepository.save(lounges);

    }
}
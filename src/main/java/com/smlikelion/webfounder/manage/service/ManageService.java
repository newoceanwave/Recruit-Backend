package com.smlikelion.webfounder.manage.service;

import com.smlikelion.webfounder.Recruit.Entity.Track;
import com.smlikelion.webfounder.manage.dto.request.DocsQuestRequest;
import com.smlikelion.webfounder.manage.dto.response.DocsQuestResponse;
import com.smlikelion.webfounder.manage.entity.Question;
import com.smlikelion.webfounder.manage.exception.AlreadyExistsQuestionNumberException;
import com.smlikelion.webfounder.manage.exception.MismatchedYearException;
import com.smlikelion.webfounder.manage.exception.UnsupportedTrackException;
import com.smlikelion.webfounder.manage.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageService {

    private final QuestionRepository questionRepository;

    public DocsQuestResponse registerQuestion(DocsQuestRequest request) {

        //해당 년도가 맞는지 확인
        if(Year.now().getValue() != request.getYear() )
            throw new MismatchedYearException("현재 년도와 다른 년도입니다.");

        //해당 트랙이 유효한지 확인
        Track track = Optional.ofNullable(Track.getTrackByName(request.getTrack()))
                .orElseThrow(() -> new UnsupportedTrackException("해당 트랙이 지원되지 않습니다."));

        //해당 번호가 유효한지 확인 (1~10) -> 이미 있는 번호면 이미 문항 있다고 예외처리
        Question existingQuestion = questionRepository.findByYearAndTrackAndNumber(request.getYear(), track, request.getNumber());
        if (existingQuestion != null)
            throw new AlreadyExistsQuestionNumberException("이미 해당 년도 해당 트랙의 번호 문항이 존재합니다.");

        //문항 등록
        Question question = questionRepository.save(
                Question.builder()
                        .year(request.getYear())
                        .track(track)
                        .number(request.getNumber())
                        .content(request.getContent())
                        .maxLength(request.getMaxLength())
                        .build()
        );

        return DocsQuestResponse.builder()
                .id(question.getQuestionId())
                .year(question.getYear())
                .track(question.getTrack().getTrackName())
                .number(question.getNumber())
                .content(question.getContent())
                .maxLength(question.getMaxLength())
                .build();
    }
}

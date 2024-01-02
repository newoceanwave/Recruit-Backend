package com.smlikelion.webfounder.manage.service;

import com.smlikelion.webfounder.Recruit.Entity.Track;
import com.smlikelion.webfounder.manage.dto.request.DocsQuestRequest;
import com.smlikelion.webfounder.manage.dto.response.DocsQuestResponse;
import com.smlikelion.webfounder.manage.entity.Question;
import com.smlikelion.webfounder.manage.exception.*;
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

        validateCurrentYear(request.getYear());
        Track track = validateTrack(request.getTrack());
        validateQuestionNumber(request.getYear(), track, request.getNumber());

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

    public DocsQuestResponse updateQuestion(Long id, DocsQuestRequest request) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundQuestionException("해당 id를 가진 문항이 존재하지 않습니다."));

        validateCurrentYear(request.getYear());
        Track track = validateTrack(request.getTrack());
        validateCurrentTrack(question.getTrack(), track);
        validateQuestionNumber(request.getYear(), track, request.getNumber());

        //문항 수정
        question.setTrack(track);
        question.setNumber(request.getNumber());
        question.setContent(request.getContent());
        question.setMaxLength(request.getMaxLength());

        question = questionRepository.save(question);

        return DocsQuestResponse.builder()
                .id(question.getQuestionId())
                .year(question.getYear())
                .track(question.getTrack().getTrackName())
                .number(question.getNumber())
                .content(question.getContent())
                .maxLength(question.getMaxLength())
                .build();
    }

    private void validateCurrentYear(Long requestedYear) {
        if (Year.now().getValue() != requestedYear) {
            throw new MismatchedYearException("현재 년도와 다른 년도입니다.");
        }
    }

    private Track validateTrack(String requestedTrack) {
        Track track = Optional.ofNullable(Track.getTrackByName(requestedTrack))
                .orElseThrow(() -> new UnsupportedTrackException("해당 트랙이 지원되지 않습니다."));
        return track;
    }

    private void validateCurrentTrack(Track beforeTrack, Track requestedTrack) {
        if (beforeTrack != requestedTrack) {
            throw new MismatchedTrackException("해당 트랙은 기존의 트랙과 달라서 수정할 수 없습니다.");
        }
    }

    private void validateQuestionNumber(Long requestedYear, Track requestedTrack, Long requestedNumber) {
        Question existingQuestion = questionRepository.findByYearAndTrackAndNumber(requestedYear, requestedTrack, requestedNumber);
        if (existingQuestion != null) {
            throw new AlreadyExistsQuestionNumberException("이미 해당 년도 해당 트랙의 번호 문항이 존재합니다.");
        }
    }

}

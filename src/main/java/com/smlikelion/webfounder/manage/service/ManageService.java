package com.smlikelion.webfounder.manage.service;

import com.smlikelion.webfounder.Recruit.Entity.Joiner;
import com.smlikelion.webfounder.Recruit.Entity.Track;
import com.smlikelion.webfounder.Recruit.Repository.JoinerRepository;
import com.smlikelion.webfounder.manage.dto.request.DocsPassRequestDto;
import com.smlikelion.webfounder.manage.dto.request.DocsQuestRequest;
import com.smlikelion.webfounder.manage.dto.response.DocsQuestResponse;
import com.smlikelion.webfounder.manage.entity.Candidate;
import com.smlikelion.webfounder.manage.entity.Docs;
import com.smlikelion.webfounder.manage.entity.Question;
import com.smlikelion.webfounder.manage.exception.*;
import com.smlikelion.webfounder.manage.repository.CandidateRepository;
import com.smlikelion.webfounder.manage.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageService {

    private final QuestionRepository questionRepository;
    private final CandidateRepository candidateRepository;
    private final JoinerRepository joinerRepository;

    public DocsQuestResponse registerQuestion(DocsQuestRequest request) {
        validateCurrentYear(request.getYear());
        Track track = validateTrackName(request.getTrack());
        validateQuestionNumber(request, track, null);

        Question question = questionRepository.save(
                Question.builder()
                        .year(request.getYear())
                        .track(track)
                        .number(request.getNumber())
                        .content(request.getContent())
                        .maxLength(request.getMaxLength())
                        .build()
        );

        return mapQuestionToDocsQuestResponse(question);
    }

    public DocsQuestResponse updateQuestion(Long id, DocsQuestRequest request) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundQuestionException("해당 id를 가진 문항이 존재하지 않습니다."));

        validateCurrentYear(request.getYear());
        Track track = validateTrackName(request.getTrack());
        validateCurrentTrack(question.getTrack(), track);
        validateQuestionNumber(request, track, question);

        //문항 수정
        question.setTrack(track);
        question.setNumber(request.getNumber());
        question.setContent(request.getContent());
        question.setMaxLength(request.getMaxLength());

        question = questionRepository.save(question);

        return mapQuestionToDocsQuestResponse(question);
    }

    public DocsQuestResponse deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundQuestionException("해당 id를 가진 문항이 존재하지 않습니다."));

        validateCurrentYear(question.getYear());

        questionRepository.delete(question);

        return mapQuestionToDocsQuestResponse(question);
    }

    public List<DocsQuestResponse> retrieveQuestionByYearAndTrack(Long year, String track) {
        Track requestedTrack = validateTrackName(track);

        List<Question> questionList = questionRepository.findAllByYearAndTrack(year, requestedTrack);
        validateQuestionList(questionList);

        return questionList.stream()
                .map(this::mapQuestionToDocsQuestResponse)
                .collect(Collectors.toList());
    }

    private DocsQuestResponse mapQuestionToDocsQuestResponse(Question question) {
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

    private Track validateTrackName(String requestedTrack) {
        Track track = Optional.ofNullable(Track.getTrackByName(requestedTrack))
                .orElseThrow(() -> new UnsupportedTrackException("해당 트랙이 지원되지 않습니다."));
        return track;
    }

    private void validateCurrentTrack(Track beforeTrack, Track requestedTrack) {
        if (beforeTrack != requestedTrack) {
            throw new MismatchedTrackException("해당 트랙은 기존의 트랙과 달라서 수정할 수 없습니다.");
        }
    }

    private void validateQuestionNumber(DocsQuestRequest request, Track track, Question existingQuestion) {
        Question foundQuestion = questionRepository.findByYearAndTrackAndNumber(request.getYear(), track, request.getNumber());
        if (existingQuestion != foundQuestion) {
            throw new AlreadyExistsQuestionNumberException("이미 해당 년도 해당 트랙의 번호 문항이 존재합니다.");
        }
    }

    private void validateQuestionList(List<Question> questionList) {
        if (questionList.isEmpty()) {
            throw new NotFoundQuestionException("해당 년도 해당 트랙의 문항이 존재하지 않습니다.");
        }
    }

    public Long docsPass(DocsPassRequestDto requestDto){
        Joiner joiner = joinerRepository.findById(requestDto.getJoinerId()).orElseThrow(
                () -> new NotFoundJoinerException("해당 joiner 검색 실패")
        );
        Candidate candidate=candidateRepository.findByJoiner(joiner).orElseThrow(
                ()-> new NotFoundCandidateException("해당 candidate 검색 실패")
        );

        try{
            candidate.setDocs(Docs.PASS);
            candidateRepository.save(candidate);
            return candidate.getJoiner().getId();
        }catch (Exception e){
            throw new InternalServerCandidateException("지원자 합격 선정 실패");
        }
    }

    public Long docsFail(DocsPassRequestDto requestDto){
        Joiner joiner = joinerRepository.findById(requestDto.getJoinerId()).orElseThrow(
                () -> new NotFoundJoinerException("해당 joiner 검색 실패")
        );
        Candidate candidate=candidateRepository.findByJoiner(joiner).orElseThrow(
                ()-> new NotFoundCandidateException("해당 candidate 검색 실패")
        );

        try{
            candidate.setDocs(Docs.REJECT);
            candidateRepository.save(candidate);
            return candidate.getJoiner().getId();
        }catch (Exception e){
            throw new InternalServerCandidateException("지원자 합격 선정 취소 실패");
        }
    }


}

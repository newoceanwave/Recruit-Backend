package com.smlikelion.webfounder.manage.controller;

import com.smlikelion.webfounder.Recruit.Dto.Response.RecruitmentResponse;
import com.smlikelion.webfounder.global.dto.response.BaseResponse;
import com.smlikelion.webfounder.global.dto.response.ErrorCode;
import com.smlikelion.webfounder.manage.dto.request.DocsPassRequestDto;
import com.smlikelion.webfounder.manage.dto.request.DocsQuestRequest;
import com.smlikelion.webfounder.manage.dto.response.DocsPassResponseDto;
import com.smlikelion.webfounder.manage.dto.response.DocsQuestResponse;
import com.smlikelion.webfounder.manage.service.ManageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/manage")
@RequiredArgsConstructor
public class ManageController {

    private final ManageService manageService;

    @Operation(summary = "서류 질문 등록하기")
    @PostMapping("/docs/quest")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<DocsQuestResponse> registerQuestion(
            @RequestBody @Valid DocsQuestRequest request) {
        return new BaseResponse<>(manageService.registerQuestion(request));
    }

    @Operation(summary = "서류 문항 조회하기")
    @GetMapping("/docs/quest")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<DocsQuestResponse>> retrieveQuestionByYearAndTrack(
            @RequestParam("year") Long year,
            @RequestParam("track") String track) {
        return new BaseResponse<>(manageService.retrieveQuestionByYearAndTrack(year, track));
    }

    @Operation(summary = "서류 질문 삭제하기")
    @DeleteMapping("/docs/quest/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<DocsQuestResponse> deleteQuestion(
            @PathVariable("id") Long id) {
        return new BaseResponse<>(manageService.deleteQuestion(id));
    }

    @Operation(summary = "서류 질문 수정하기")
    @PutMapping("/docs/quest/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<DocsQuestResponse> updateQuestion(
            @PathVariable("id") Long id,
            @RequestBody @Valid DocsQuestRequest request) {
        return new BaseResponse<>(manageService.updateQuestion(id, request));
    }

    @Operation(summary = "서류 합격자 선정")
    @PostMapping("/docs/add")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> docsPass(@RequestBody DocsPassRequestDto requestDto){
        return new BaseResponse<>(manageService.docsPass(requestDto)+"번 지원자가 서류 합격자 선정되었습니다.");
    }

    @Operation(summary = "서류 합격자 취소")
    @DeleteMapping("/docs/del")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> docsFail(@RequestBody DocsPassRequestDto requestDto){
        return new BaseResponse<>(manageService.docsFail(requestDto)+"번 지원자가 서류 합격자 취소되었습니다.");
    }

    @Operation(summary = "면접 합격자 선정")
    @PostMapping("/interview/add")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> interviewPass(@RequestBody DocsPassRequestDto requestDto){
        return new BaseResponse<>(manageService.interviewPass(requestDto)+"번 지원자가 면접 합격자 선정되었습니다.");
    }

    @Operation(summary = "면접 합격자 취소")
    @DeleteMapping("/interview/del")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> interviewFail(@RequestBody DocsPassRequestDto requestDto){
        return new BaseResponse<>(manageService.interviewFail(requestDto)+"번 지원자가 면접 합격자 취소되었습니다.");
    }

    @Operation(summary = "서류 합격자 전채 조회")
    @GetMapping("/result")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<DocsPassResponseDto>> docsPassList(@RequestParam("track") String track){
        return new BaseResponse<>(manageService.docsPassList(track));
    }
}

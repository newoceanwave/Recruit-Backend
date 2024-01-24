package com.smlikelion.webfounder.Recruit.Controller;// Import statements

import com.smlikelion.webfounder.Recruit.Dto.Request.RecruitmentRequest;
import com.smlikelion.webfounder.Recruit.Dto.Response.RecruitmentResponse;
import com.smlikelion.webfounder.Recruit.Entity.Joiner;
import com.smlikelion.webfounder.Recruit.Repository.JoinerRepository;
import com.smlikelion.webfounder.Recruit.Service.RecruitService;
import com.smlikelion.webfounder.global.dto.response.BaseResponse;
import com.smlikelion.webfounder.global.dto.response.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Track;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recruit/docs")
@Slf4j
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    @Autowired
    private JoinerRepository joinerRepository;


    @Operation(summary = "트랙별 서류 작성하기")
    @PostMapping
    public BaseResponse<RecruitmentResponse> submitRecruitment(
            @RequestParam("track") String track,
            @RequestBody @Valid RecruitmentRequest request,
            BindingResult bindingResult) {

        logValidationErrors(bindingResult);

        if ("fe".equalsIgnoreCase(track) || "pm".equalsIgnoreCase(track) || "be".equalsIgnoreCase(track)) {
            // Process the valid request
            RecruitmentResponse recruitResponse = recruitService.registerRecruitment(request);
            return new BaseResponse<>(recruitResponse);

        } else {
            // Handle invalid track value
            String errorMessage = "Invalid track value. Please provide a valid track (fe, pm, be).";
            return new BaseResponse<>(ErrorCode.NOT_FOUND);
        }
    }
    @Operation(summary = "트랙별 서류 작성 페이지 조회하기")
    @GetMapping("/{joinerId}")
    public BaseResponse<RecruitmentResponse> getJoinerDetails(
            @PathVariable Long joinerId) {
        Joiner joiner = joinerRepository.findById(joinerId).orElse(null);

        if (joiner != null) {
            // Joiner를 찾은 경우, RecruitmentResponse로 변환하여 응답 반환
            RecruitmentResponse recruitResponse = RecruitmentResponse.builder()
                    .id(joiner.getId())
                    .studentInfo(joiner.toStudentInfoResponse())
                    .answerList(joiner.toAnswerListResponse())
                    .interviewTime(joiner.getInterviewTime().keySet()) // 필요에 따라 수정
                    .build();

            return new BaseResponse<>(recruitResponse);
        } else {
            // Joiner를 찾지 못한 경우, 오류 응답 반환
            return new BaseResponse<>(ErrorCode.NOT_FOUND);
        }
    }



    private void logValidationErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            errors.forEach(error -> log.error("Validation error: {}", error.getDefaultMessage()));
        }
    }
}

package com.smlikelion.webfounder.Recruit.Controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruit/docs")
public class RecruitController {

    @PostMapping("/fe")
    public ResponseEntity<ApiResponse> submitFeRecruitment(
            @RequestBody RecruitmentRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, false, "잘못된 요청입니다. 입력을 확인해주세요."));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(201, true, "fe 서류 작성 완료"));
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApiResponse {
        private int status;
        private boolean success;
        private String message;
    }

    @Getter
    @Setter
    public static class RecruitmentRequest {
        private StudentInfo studentInfo;
        private QuestionList questionList;
        private List<Integer> dateList;
    }

    @Getter
    @Setter
    public static class StudentInfo {
        private String name;
        private String track;
        private String phoneNumber;
        private long studentId;
        private String major;
        private int completedSem;
        private int schoolStatus;
        private String graduatedYear;
        private int programmers;
        private String programmersImg;
        private String password;
    }

    @Getter
    @Setter
    public static class QuestionList {
        private String A1;
        private String A2;
        private String A3;
        private String A4;
        private String A5;
        private String A6;
        private String A7;
    }

}

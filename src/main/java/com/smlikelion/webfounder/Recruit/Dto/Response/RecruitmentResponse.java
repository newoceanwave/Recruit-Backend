package com.smlikelion.webfounder.Recruit.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@Builder
public class RecruitmentResponse {
    private Long id;
    private StudentInfoResponse studentInfo;
    // 지원자 질문 별 답변 목록
    private ArrayList<String> answerList;
    //면접 시간
    private Set<String> interviewTime;


    public static RecruitmentResponseBuilder builder() {
        return new RecruitmentResponseBuilder();
    }


    public static class RecruitmentResponseBuilder {
        private Long id;
        private StudentInfoResponse studentInfo;
        private ArrayList<String> answerList;
        private Set<String> interviewTime;


        // interviewTime 설정 메서드
        public RecruitmentResponseBuilder answerList(AnswerListResponse answerListResponse) {
            this.answerList = new ArrayList<>();

            if (answerListResponse != null) {
                this.answerList.add(answerListResponse.getA1());
                this.answerList.add(answerListResponse.getA2());
                this.answerList.add(answerListResponse.getA3());
                this.answerList.add(answerListResponse.getA4());
                this.answerList.add(answerListResponse.getA5());
                this.answerList.add(answerListResponse.getA6());
                this.answerList.add(answerListResponse.getA7());

                // 나머지 필드에 대한 추가 작업
            }

            return this;
        }

        public RecruitmentResponseBuilder interviewTime(Set<String> interviewTime) {
            this.interviewTime = interviewTime;
            return this;
        }

        public RecruitmentResponse build() {
            return new RecruitmentResponse(id, studentInfo, answerList, interviewTime);
        }
    }

}

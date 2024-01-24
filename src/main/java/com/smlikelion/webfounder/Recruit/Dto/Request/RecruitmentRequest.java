package com.smlikelion.webfounder.Recruit.Dto.Request;

import com.smlikelion.webfounder.Recruit.Dto.Response.AnswerListResponse;
import com.smlikelion.webfounder.Recruit.Entity.Joiner;
import com.smlikelion.webfounder.Recruit.Entity.SchoolStatus;
import com.smlikelion.webfounder.Recruit.Entity.Track;
import com.smlikelion.webfounder.manage.entity.Candidate;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.Map;

@Getter
@Setter

public class RecruitmentRequest {
    @NotNull
    @Valid
    private StudentInfoRequest studentInfo;

    // 답변
    @NotNull
    @Valid
    private AnswerListRequest answerList;


    //인터뷰 타임
    @Valid
    @NotNull
    private Map<String, String> interview_time;

    public Joiner toJoiner() {
        Joiner joiner = new Joiner();

        joiner.setName(this.studentInfo.getName());

        //enum 타입
        joiner.setTrack(convertToTrackEnum(this.studentInfo.getTrack()));
        joiner.setPhoneNum(this.studentInfo.getPhoneNumber());
        joiner.setStudentId(String.valueOf(this.studentInfo.getStudentId()));
        joiner.setMajor(this.studentInfo.getMajor());
        joiner.setCompletedSem(this.studentInfo.getCompletedSem());
        //enum 타입
        joiner.setSchoolStatus(convertToSchoolStatusEnum(this.studentInfo.getSchoolStatus()));
        //enum 타입
        joiner.setProgrammers(this.studentInfo.getProgrammers());
        joiner.setPassword(this.studentInfo.getPassword());
        joiner.setPortfolio(this.studentInfo.getPortfolio());

        //인터뷰
        joiner.setInterviewTime(this.interview_time);


        joiner.setGraduatedYear(this.studentInfo.toJoiner().getGraduatedYear());
        return joiner;
    }

    public AnswerListRequest getAnswerListRequest() {
        return answerList;
    }


    private Track convertToTrackEnum(String track) {
        try {
            return Track.valueOf(track.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    private SchoolStatus convertToSchoolStatusEnum(String schoolStatus) {
        try {
            return SchoolStatus.valueOf(schoolStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            // 예외 처리 추가 가능
            return null;
        }
    }

}




package com.smlikelion.webfounder.Recruit.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.smlikelion.webfounder.Recruit.Dto.Response.AnswerListResponse;
import com.smlikelion.webfounder.Recruit.Dto.Response.StudentInfoResponse;
import com.smlikelion.webfounder.global.entity.DateEntity;
import com.smlikelion.webfounder.manage.entity.Candidate;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
@Table(name = "Joiner")
@Getter
@Setter
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Joiner extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "joiners_id")
    private Long Id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_num", nullable = false)
    private String phoneNum;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "major", nullable = false)
    private String major;

    @Column(name = "completed_sem", nullable = false)
    private Integer completedSem;

    @Column(name = "school_status")
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private SchoolStatus schoolStatus;

    @Column(name = "programmers", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Programmers programmers;

    @Column(name = "programmers_image_url")
    private String programmersImageUrl;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "track")
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Track track;


    @Type(type = "json")
    @Column(name = "interview_time", columnDefinition = "json")
    private Map<String, String> interviewTime = new HashMap<>();


    @Column(name = "portfolio",  nullable = false)

    private String portfolio;


    //JSON 형식으로 저장될 리스트는
    //해당 필드를 String 형태로 선언하고 데이터베이스에서는 JSON 형식으로 처리하도록 컬럼 정의를 추가
    //TEXT 타입: JSON 데이터를 저장하는 데 사용
    //JSON 형식의 데이터를 String으로 저장하고, 읽을 때는 해당 String을 JSON으로 파싱하여 사용

    @Type(type = "json")
    @Column(name = "answer_list", columnDefinition = "json")
    private List<String> answerList = new ArrayList<>();

    @Column(name = "graduated_year", nullable = false)
    private String graduatedYear;


    public StudentInfoResponse toStudentInfoResponse() {
        return StudentInfoResponse.builder()
                .name(this.name)
                .track(this.track.name())
                .phoneNumber(this.phoneNum)
                .studentId(Long.parseLong(this.studentId))
                .major(this.major)
                .portfolio(this.portfolio)
                .password(this.password)
                .completedSem(this.completedSem)
                .schoolStatus(this.schoolStatus.name()) // 열거형 상수의 문자열 표현 사용
                .programmers(this.programmers)
                .programmersImg(this.programmersImageUrl)
                .graduatedYear(this.graduatedYear)
                .build();
    }

    public AnswerListResponse toAnswerListResponse() {
        return AnswerListResponse.builder()
                .A1(this.answerList.get(0))
                .A2(this.answerList.get(1))
                .A3(this.answerList.get(2))
                .A4(this.answerList.get(3))
                .A5(this.answerList.get(4))
                .A6(this.answerList.get(5))
                .A7(this.answerList.get(6))
                // 나머지 필드에 대한 추가 작업
                .build();
    }



}

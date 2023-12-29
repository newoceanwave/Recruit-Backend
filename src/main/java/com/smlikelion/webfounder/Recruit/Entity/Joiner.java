package com.smlikelion.webfounder.Recruit.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "Joiner")
@Getter
@Setter

public class Joiner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "joiners_id")
    private Long joinersId;

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

    @Column(name = "school_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private SchoolStatus schoolStatus;

    @Column(name = "programmers", nullable = false)
    @Enumerated(EnumType.STRING)
    private Programmers programmers;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "track", nullable = false)
    @Enumerated(EnumType.STRING)
    private Track track;

    @ElementCollection
    @CollectionTable(name = "interview_time", joinColumns = @JoinColumn(name = "joiners_id"))
    @Column(name = "interview_time")
    private Set<String> interviewTime;

    @Column(name = "portfolio")
    private String portfolio;

// FILE 테이블에 대한 Entity 작성되면 주석 해제
//    @OneToMany(mappedBy = "joiner")
//    private Set<File> files;


    //JSON 형식으로 저장될 리스트는
    //해당 필드를 String 형태로 선언하고 데이터베이스에서는 JSON 형식으로 처리하도록 컬럼 정의를 추가
    //TEXT 타입: JSON 데이터를 저장하는 데 사용
    //JSON 형식의 데이터를 String으로 저장하고, 읽을 때는 해당 String을 JSON으로 파싱하여 사용
    @Column(name = "answer_list", columnDefinition = "TEXT")
    private String answerList;

    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

}

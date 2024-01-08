package com.smlikelion.webfounder.Recruit.Dto.Response;

import com.smlikelion.webfounder.Recruit.Entity.Programmers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
@Builder
public class StudentInfoResponse {
    private String name;
    private String password;
    private String portfolio;
    private String track;
    private String phoneNumber;
    private long studentId;
    private String major;


    private int completedSem;
    private String schoolStatus;
    private String graduatedYear;
    private Programmers programmers;
    private String programmersImg;

}

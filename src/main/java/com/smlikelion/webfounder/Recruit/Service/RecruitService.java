package com.smlikelion.webfounder.Recruit.Service;
import com.smlikelion.webfounder.Recruit.Dto.Request.RecruitmentRequest;
import com.smlikelion.webfounder.Recruit.Dto.Response.RecruitmentResponse;
import com.smlikelion.webfounder.Recruit.Dto.Response.StudentInfoResponse;
import com.smlikelion.webfounder.Recruit.Entity.Joiner;
import com.smlikelion.webfounder.Recruit.Entity.Programmers;
import com.smlikelion.webfounder.Recruit.Entity.Track;
import com.smlikelion.webfounder.Recruit.Repository.JoinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smlikelion.webfounder.Recruit.Entity.SchoolStatus;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class RecruitService {

    private final JoinerRepository joinerRepository;

    @Autowired
    public RecruitService(JoinerRepository joinerRepository) {

        this.joinerRepository = joinerRepository;
    }

    public RecruitmentResponse registerRecruitment(RecruitmentRequest request) {
        Joiner joiner = request.getStudentInfo().toJoiner();
        joiner.setInterviewTime(request.getInterview_time());

        List<String> answerList = request.getAnswerListRequest().toAnswerList();
        joiner.setAnswerList(answerList);


        joiner = joinerRepository.save(joiner);
        StudentInfoResponse studentInfoResponse = joiner.toStudentInfoResponse();


        Set<String> interviewTime = request.getInterview_time().values().stream().collect(Collectors.toSet());


        return RecruitmentResponse.builder()
                .id(joiner.getId())
                .studentInfo(studentInfoResponse)
                .answerList(joiner.toAnswerListResponse())
                .interviewTime(interviewTime)
                .build();
    }




}

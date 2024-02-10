package com.smlikelion.webfounder.Recruit.Service;
import com.smlikelion.webfounder.Recruit.Dto.Request.MailRequestDto;
import com.smlikelion.webfounder.Recruit.Dto.Request.RecruitmentRequest;
import com.smlikelion.webfounder.Recruit.Dto.Response.MailResponseDto;
import com.smlikelion.webfounder.Recruit.Dto.Response.RecruitmentResponse;
import com.smlikelion.webfounder.Recruit.Dto.Response.StudentInfoResponse;
import com.smlikelion.webfounder.Recruit.Entity.*;
import com.smlikelion.webfounder.Recruit.Repository.JoinerRepository;
import com.smlikelion.webfounder.Recruit.Repository.MailRepository;
import com.smlikelion.webfounder.Recruit.exception.NotFoundEmailException;
import com.smlikelion.webfounder.manage.entity.Candidate;
import com.smlikelion.webfounder.manage.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class RecruitService {

    private final JoinerRepository joinerRepository;
    private final CandidateRepository candidateRepository;
    private final MailRepository mailRepository;

    public RecruitmentResponse registerRecruitment(RecruitmentRequest request) {
        Joiner joiner = request.getStudentInfo().toJoiner();
        joiner.setInterviewTime(request.getInterview_time());

        List<String> answerList = request.getAnswerListRequest().toAnswerList();
        joiner.setAnswerList(answerList);

        joiner = joinerRepository.save(joiner);
        StudentInfoResponse studentInfoResponse = joiner.toStudentInfoResponse();

        // cadidate entity 생성 시 서류합 란을 reject로 초기 설정
        Candidate candidate=new Candidate(joiner,"REJECT","REJECT");
        candidateRepository.save(candidate);

        Set<String> interviewTime = request.getInterview_time().values().stream().collect(Collectors.toSet());


        return RecruitmentResponse.builder()
                .id(joiner.getId())
                .studentInfo(studentInfoResponse)
                .answerList(joiner.toAnswerListResponse())
                .interviewTime(interviewTime)
                .build();
    }


    public String mailSubmit(MailRequestDto requestDto) {
        Mail mail=mailRepository.save(
                Mail.builder()
                        .emailAdd(requestDto.getEmailAdd())
                        .build()
        );
        return mail.getEmailAdd();
    }

    public List<String> findAllmail(){
        try{
            List<Mail> mailList=mailRepository.findAll();
            List<String> result=new ArrayList<>();

            for(Mail mail:mailList){
                result.add(mail.getEmailAdd());
            }
            return result;
        }catch (Exception e){
            throw new NotFoundEmailException("이메일 전체 조회 실패");
        }
    }
}

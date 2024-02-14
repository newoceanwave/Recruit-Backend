package com.smlikelion.webfounder.Recruit.Service;

import com.smlikelion.webfounder.Recruit.Dto.Request.MailRequestDto;
import com.smlikelion.webfounder.Recruit.Entity.Mail;
import com.smlikelion.webfounder.Recruit.Repository.MailRepository;
import com.smlikelion.webfounder.Recruit.exception.NotFoundEmailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
    private final MailRepository mailRepository;
    private final JavaMailSender javaMailSender;

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

    public List<String> getEmailsOnly() {
        List<Mail> emailsWithId = mailRepository.findAll(); // 모든 이메일을 가져옴
        return emailsWithId.stream()
                .map(Mail::getEmailAdd) // 이메일만 추출
                .collect(Collectors.toList());
    }

    public String sendMail(){
        // 어떤 메일에, 총 몇개가 발송되었는지 로그 찍어주면 좋을 듯?!
        List<String> mailList=getEmailsOnly();
        SimpleMailMessage message=new SimpleMailMessage();

        message.setSubject("[숙멋] 멋사 지원기간입니다."); // 메일 제목
        message.setTo(mailList.toArray(new String[mailList.size()]));
        message.setText("멋사 지원해"); // 메일 내용
        javaMailSender.send(message);

        return "지원자에게 메일 발송이 완료되었습니다.";
    }
}

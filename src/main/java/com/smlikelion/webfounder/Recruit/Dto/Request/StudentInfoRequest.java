package com.smlikelion.webfounder.Recruit.Dto.Request;
import com.smlikelion.webfounder.Recruit.Entity.Joiner;
import com.smlikelion.webfounder.Recruit.Entity.Programmers;
import com.smlikelion.webfounder.Recruit.Entity.SchoolStatus;
import com.smlikelion.webfounder.Recruit.Entity.Track;
import lombok.Getter;
 import lombok.Setter;
 import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Getter
@Setter
public class StudentInfoRequest {
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "트랙을 입력해주세요.")
    private String track;
    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phoneNumber;
    @NotNull(message = "학번을 입력해주세요.")
    private long studentId;
    @NotBlank(message = "전공을 입력해주세요.")
    private String major;
    @NotNull(message = "수료학기를 선택해주세요.")
    private int completedSem;

    @NotNull(message = "포트폴리오를 작성해주세요.")
    private String Portfolio;

    @NotNull(message = "재/휴학 여부를 선택해주세요.")
    private String schoolStatus;

    @NotNull(message = "프로그래머스 수강 여부를 선택해주세요.")
    private Programmers programmers;

    private String programmersImg;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "졸업년도를 입력해주세요.")
    private String graduatedYear;



    public Joiner toJoiner() {
        Joiner joiner = new Joiner();
        joiner.setName(this.getName());
        joiner.setTrack(convertToTrackEnum(this.getTrack()));  // Track 열거형으로 변환
        joiner.setPhoneNum(this.getPhoneNumber());
        joiner.setStudentId(String.valueOf(this.getStudentId()));
        joiner.setMajor(this.getMajor());
        joiner.setCompletedSem(this.getCompletedSem());
        joiner.setPortfolio(this.getPortfolio());
        joiner.setSchoolStatus(convertToSchoolStatusEnum(this.getSchoolStatus()));
        joiner.setProgrammers(this.getProgrammers());
        joiner.setPassword(this.getPassword());
        joiner.setProgrammersImageUrl(this.getProgrammersImg());
        joiner.setGraduatedYear(this.getGraduatedYear());
        return joiner;
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


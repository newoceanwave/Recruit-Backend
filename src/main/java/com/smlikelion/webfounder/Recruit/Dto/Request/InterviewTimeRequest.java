package com.smlikelion.webfounder.Recruit.Dto.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class InterviewTimeRequest {
    private Map<String, String> interview_time;
}

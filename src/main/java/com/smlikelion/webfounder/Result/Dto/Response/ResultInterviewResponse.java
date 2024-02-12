package com.smlikelion.webfounder.Result.Dto.Response;

import com.smlikelion.webfounder.manage.entity.Interview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultInterviewResponse {

    private String name;
    private Interview interview;

    public static ResultInterviewResponseBuilder builder() {
        return new ResultInterviewResponseBuilder();
    }

    public static class ResultInterviewResponseBuilder {
        private String name;
        private Interview interview;

        ResultInterviewResponseBuilder() {}

        public ResultInterviewResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ResultInterviewResponseBuilder interview(Interview interview) {
            this.interview = interview;
            return this;
        }
    }
}

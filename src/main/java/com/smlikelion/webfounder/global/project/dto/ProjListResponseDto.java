// 프로젝트 상세 조회할 때 응답할 값 명시
package com.smlikelion.webfounder.global.project.dto;

import com.smlikelion.webfounder.global.project.entity.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjListResponseDto {
    private String title;
    private String summary;
    private String content;
    private Integer year;
    private String teamName;
    private String teamMember;

    public ProjListResponseDto(Project project){
        this.title= project.getTitle();
        this.summary= project.getSummary();
        this.content= project.getContent();
        this.year= project.getYear();
        this.teamName= project.getTeamName();
        this.teamMember= project.getTeamMember();
    }
}

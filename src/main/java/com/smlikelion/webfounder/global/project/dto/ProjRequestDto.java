// 프로젝트 게시물 작성할 때 프론트한테 요청받을 값 명시
package com.smlikelion.webfounder.global.project.dto;

import com.smlikelion.webfounder.global.project.entity.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjRequestDto {
    private String title;
    private String summary;
    private String content;
    private Integer year;
    private String teamName;
    private String teamMember;
    private String servIntro;
    private String gitBeUrl;
    private String gitFeUrl;
    private String servLaunch;
    private String bgImg;

    public ProjRequestDto(Project project) {
        this.title= project.getTitle();
        this.summary= project.getSummary();
        this.content= project.getContent();
        this.year= project.getYear();
        this.teamName= project.getTeamName();
        this.teamMember= project.getTeamMember();
        this.servIntro= project.getServIntro();
        this.gitBeUrl= project.getGitBeUrl();
        this.gitFeUrl= project.getGitFeUrl();
        this.servLaunch= project.getServLaunch();
        this.bgImg= project.getBgImg();
    }
}

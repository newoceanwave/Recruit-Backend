package com.smlikelion.webfounder.global.project.entity;

import com.smlikelion.webfounder.global.entity.DateEntity;
import com.smlikelion.webfounder.global.project.dto.ProjRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="project")
@Getter
@Setter
@NoArgsConstructor
public class Project extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "year", nullable = false)
    private Integer year; // 2022, 2023 ...

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @Column(name = "team_member", nullable = false)
    private String teamMember;

    //requestDto 정보를 가져와서 entity 만들 때 사용
    public Project(ProjRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.summary= requestDto.getSummary();
        this.content = requestDto.getContent();
        this.year = requestDto.getYear();
        this.teamName= requestDto.getTeamName();
        this.teamMember= requestDto.getTeamMember();
    }
}

package com.smlikelion.webfounder.global.project;

import com.smlikelion.webfounder.global.project.dto.ProjListResponseDto;
import com.smlikelion.webfounder.global.project.dto.ProjRequestDto;
import com.smlikelion.webfounder.global.project.dto.ProjResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjController {
    private final ProjectService projectService;

    // [POST] 프로젝트 작성
    @PostMapping("/api/project")
    public ProjRequestDto createProj(@RequestBody ProjRequestDto requestDto){
        ProjRequestDto project = projectService.createProj(requestDto);
        return project;
    }

    // [GET] 프로젝트 전체 조회
    @GetMapping("/api/project")
    public List<ProjResponseDto> getAllProj(){
        return projectService.findAllProj();
    }

    // [GET] 프로젝트 상세 조회
    @GetMapping("/api/project/{id}")
    public ProjListResponseDto getOneProj(@PathVariable Long id){
        return projectService.findOneProj(id);
    }
}


package com.smlikelion.webfounder.global.project;

import com.smlikelion.webfounder.global.project.dto.ProjListResponseDto;
import com.smlikelion.webfounder.global.project.dto.ProjRequestDto;
import com.smlikelion.webfounder.global.project.dto.ProjResponseDto;
import com.smlikelion.webfounder.global.project.entity.Project;
import com.smlikelion.webfounder.global.project.repo.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    // [POST] 프로젝트 작성
    public ProjRequestDto createProj(ProjRequestDto requestDto) {
        Project project = new Project(requestDto);
        projectRepository.save(project);
        return new ProjRequestDto(project);
    }

    // [GET] 프로젝트 전체 조회
    public List<ProjResponseDto> findAllProj(){
        try{
            List<Project> projectList=projectRepository.findAll();
            List<ProjResponseDto> responseDtoList=new ArrayList<>();

            for(Project project:projectList){
                responseDtoList.add(new ProjResponseDto(project));
            }
            return responseDtoList;
        }catch (Exception e){
            throw new IllegalArgumentException("프로젝트 전체 조회 실패");
        }
    }

    // [GET] 프로젝트 상세 조회
    public ProjListResponseDto findOneProj(Long id){
        Project project=projectRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("프로젝트 상세 조회 실패")
        );
        return new ProjListResponseDto(project);
    }

//    // [DELETE] 프로젝트 삭제
//    @Transactional
//    public Long deleteProj(Long id) {
//        ProjectRepository.deleteById(id);
//        return id;
//    }

}

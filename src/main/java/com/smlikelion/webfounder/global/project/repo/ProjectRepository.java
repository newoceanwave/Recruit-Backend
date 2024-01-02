package com.smlikelion.webfounder.global.project.repo;

import com.smlikelion.webfounder.global.project.dto.ProjListResponseDto;
import com.smlikelion.webfounder.global.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

package com.smlikelion.webfounder.manage.repository;

import com.smlikelion.webfounder.manage.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
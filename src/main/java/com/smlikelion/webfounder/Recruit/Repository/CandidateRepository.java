package com.smlikelion.webfounder.Recruit.Repository;

import com.smlikelion.webfounder.Recruit.Entity.Answer;
import com.smlikelion.webfounder.Recruit.Entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
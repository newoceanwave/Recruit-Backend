package com.smlikelion.webfounder.manage.repository;

import com.smlikelion.webfounder.Recruit.Entity.Joiner;
import com.smlikelion.webfounder.manage.entity.Candidate;
import com.smlikelion.webfounder.manage.entity.Docs;
import com.smlikelion.webfounder.manage.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByJoiner(Joiner joiner);
    List<Candidate> findAllByDocs(Docs docs);
    List<Candidate> findAllByDocsAndInterview(Docs docs, Interview interview);
    Candidate findByJoinerAndDocs(Joiner joiner, Docs docs);
    Candidate findByJoinerAndInterview(Joiner joiner, Interview interview);
}

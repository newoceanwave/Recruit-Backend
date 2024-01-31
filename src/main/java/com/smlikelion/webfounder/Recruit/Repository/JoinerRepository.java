package com.smlikelion.webfounder.Recruit.Repository;

import com.smlikelion.webfounder.Recruit.Entity.Joiner;
import com.smlikelion.webfounder.Recruit.Entity.Programmers;
import com.smlikelion.webfounder.Recruit.Entity.SchoolStatus;
import com.smlikelion.webfounder.Recruit.Entity.Track;
import com.smlikelion.webfounder.manage.entity.Candidate;
import com.smlikelion.webfounder.manage.entity.Docs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinerRepository extends JpaRepository<Joiner, Long> {
}
package com.smlikelion.webfounder.manage.repository;

import com.smlikelion.webfounder.Recruit.Entity.Track;
import com.smlikelion.webfounder.manage.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository <Question, Long> {
    Question findByYearAndTrackAndNumber(Long year, Track track, Long number);
}

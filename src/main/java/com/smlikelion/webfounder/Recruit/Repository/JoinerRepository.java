package com.smlikelion.webfounder.Recruit.Repository;

import com.smlikelion.webfounder.Recruit.Entity.Joiner;
import com.smlikelion.webfounder.Recruit.Entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface JoinerRepository extends JpaRepository<Joiner, Long> {
    Joiner findByNameAndStudentId(String name, String studentId);
    Long countByTrack(Track track);
    //List<Joiner> findAllByTrackOrderByJoinerIdAsc(Track track);
    List<Joiner> findAllByTrackOrderByCreatedAtAsc(Track track);

}
package com.smlikelion.webfounder.Recruit.Repository;

import com.smlikelion.webfounder.Recruit.Entity.Joiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JoinerRepository extends JpaRepository<Joiner, Long> {
    Joiner findByNameAndStudentId(String name, String studentId);
}
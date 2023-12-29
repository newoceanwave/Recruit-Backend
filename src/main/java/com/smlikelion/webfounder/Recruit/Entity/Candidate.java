package com.smlikelion.webfounder.Recruit.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidate")
@Getter
@Setter
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long candidateId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "joiners_id", unique = true)
    private Joiner joiner;

    @Column(name = "docs", nullable = false)
    @Enumerated(EnumType.STRING)
    private Docs docs;

    @Column(name = "interview", nullable = false)
    @Enumerated(EnumType.STRING)
    private Interview interview;

    @Column(name = "updated_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

}

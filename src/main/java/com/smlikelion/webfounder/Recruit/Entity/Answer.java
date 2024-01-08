package com.smlikelion.webfounder.Recruit.Entity;
import com.smlikelion.webfounder.global.entity.DateEntity;
import com.smlikelion.webfounder.manage.entity.Question;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "answer")
@Getter
@Setter
public class Answer extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;


    @Column(name = "content", nullable = false)
    private String content;


}

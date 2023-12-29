package com.smlikelion.webfounder.Recruit.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "answer")
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;

    // 일대다 관계로? ERD CLOUD 에 적혀있는 question_id 가 ENUM  타입인 것도 좀 이상한듯
//    @Column(name = "question_id", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private QuestionId questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;


    @Column(name = "content", nullable = false)
    private String content;
}

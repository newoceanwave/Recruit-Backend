package com.smlikelion.webfounder.admin.entity;
import com.smlikelion.webfounder.global.entity.DateEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "question")
@Getter
@Setter
public class Question extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "year", nullable = false)
    private Long year;

    @Column(name = "number", nullable = false)
    private Long number;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "max_length", nullable = false)
    private Long maxLength;

}

//package com.smlikelion.webfounder.Recruit.Entity;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "question")
//@Getter
//@Setter
//public class Question {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "question_id")
//    private Long questionId;
//
//    @Column(name = "content", nullable = false)
//    private String content;
//
//    @Column(name = "number", nullable = false)
//    private Long number;
//
//    @Column(name = "max_length", nullable = false)
//    private Long maxLength;
//
//    @Column(name = "created_at", nullable = false)
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at", nullable = false)
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime updatedAt;
//
//    @Column(name = "year", nullable = false)
//    private Long year;
//
//    @Column(name = "track", nullable = false)
//    @Enumerated(EnumType.ORDINAL)
//    private Track track;
//}

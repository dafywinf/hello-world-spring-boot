package com.dafywinf.hello.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "HELLO_LOGS")
public class HelloLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "generation_date", updatable = false, nullable = false)
    private LocalDateTime generationDate;

    @PrePersist
    public void prePersist() {
        generationDate = LocalDateTime.now();
    }
}

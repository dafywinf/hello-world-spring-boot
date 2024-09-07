package com.dafywinf.hello.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class HelloLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String message;

  @Column(updatable = false, nullable = false)
  private LocalDateTime generationDate;

  @PrePersist
  public void prePersist() {
    generationDate = LocalDateTime.now();
  }
}

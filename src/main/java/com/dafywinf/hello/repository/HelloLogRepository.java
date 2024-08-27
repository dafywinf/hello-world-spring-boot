package com.dafywinf.hello.repository;

import com.dafywinf.hello.domain.HelloLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloLogRepository extends JpaRepository<HelloLog, Long> {
}

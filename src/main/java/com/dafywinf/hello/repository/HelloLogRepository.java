package com.dafywinf.hello.repository;

import com.dafywinf.hello.domain.HelloLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HelloLogRepository extends JpaRepository<HelloLog, Long> {}

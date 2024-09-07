package com.dafywinf.hello.web.rest;

import com.dafywinf.hello.domain.HelloLog;
import com.dafywinf.hello.repository.HelloLogRepository;
import com.dafywinf.hello.web.rest.util.ResponseUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

@RestController
@RequestMapping("/api/hellos")
@RequiredArgsConstructor
public class HelloLogResource {

  private static final Logger log = LoggerFactory.getLogger(HelloLogResource.class);

  private final HelloLogRepository helloLogRepository;

  // @GetMapping("/{id}")
  public ResponseEntity<HelloLog> getHelloLog(@PathVariable Long id) {
    log.debug("REST request to get HelloLog : {}", id);
    return ResponseUtil.wrapOrNotFound(this.helloLogRepository.findById(id));
  }

  @GetMapping("")
  public ResponseEntity<List<HelloLog>> hello(
      @org.springdoc.core.annotations.ParameterObject Pageable pageable) {
    log.debug("REST request to get HelloLog : {}", pageable);
    Page<HelloLog> page = helloLogRepository.findAll(pageable);
    HttpHeaders headers =
        PaginationUtil.generatePaginationHttpHeaders(
            ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}

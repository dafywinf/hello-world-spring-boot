package com.dafywinf.hello.web.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.dafywinf.IntegrationTest;
import com.dafywinf.hello.domain.HelloLog;
import com.dafywinf.hello.repository.HelloLogRepository;
import java.util.List;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** Integration tests for the {@link HelloLogResource} REST controller. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @Testcontainers
@IntegrationTest
public class HelloWorldResourceIT {

  private static final Logger log = LoggerFactory.getLogger(HelloWorldResourceIT.class);

  // @Container @ServiceConnection
  // @Autowired private SqlTestContainer postgreSQLContainer;
  //   =
  //      new PostgreSQLContainer<>("postgres:16.4")
  //          .withTmpFs(Collections.singletonMap("/testtmpfs", "rw"))
  //          .withLogConsumer(new Slf4jLogConsumer(log))
  //          .withReuse(true);

  @Autowired private HelloLogRepository repository;
  @LocalServerPort private Integer port;
  @Autowired private TestRestTemplate restTemplate;

  //  @BeforeAll
  //  public static void startContainer() {
  //    postgreSQLContainer.start();
  //  }
  //
  //  @AfterAll
  //  public static void stopContainer() {
  //    postgreSQLContainer.stop();
  //  }

  @Test
  public void shouldReturnHello() {
    String message = "Hello, World!";

    ResponseEntity<List<HelloLog>> response =
        restTemplate.exchange(
            "/api/hellos",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<HelloLog>>() {});

    assertNotNull(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Hello World 1!", response.getBody().get(0).getMessage());
  }

  @BeforeEach
  void setup() {
    HelloLog log1 = new HelloLog();
    log1.setMessage("Hello World 1!");

    HelloLog log2 = new HelloLog();
    log2.setMessage("Hello World 2!");

    List<HelloLog> logs = List.of(log1, log2);

    Iterable<HelloLog> sList = repository.saveAll(logs);
  }

  @AfterEach
  void teardown() {
    repository.deleteAll();
  }
}

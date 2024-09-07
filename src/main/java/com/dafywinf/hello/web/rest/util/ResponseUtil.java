package com.dafywinf.hello.web.rest.util;

import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

/** Utility class for REST response creation. */
public interface ResponseUtil {

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse) {
    return wrapOrNotFound(maybeResponse, null);
  }

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, HttpHeaders headers) {
    return maybeResponse
        .map(response -> ResponseEntity.ok().headers(headers).body(response))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }
}

package org.lexsoft.library.core.exception.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorMessage {

  Long timestamp;
  List<Error> errors;

  @Data
  @Builder
  public static class Error {
    Integer code;
    String message;
  }
}

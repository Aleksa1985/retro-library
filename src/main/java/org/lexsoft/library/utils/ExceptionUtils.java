package org.lexsoft.library.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.lexsoft.library.core.exception.ErrorMessages;
import org.lexsoft.library.core.exception.model.ErrorMessage.Error;

public class ExceptionUtils {

    public static List<Error> addError(ErrorMessages error, List<Error> errors, String... messagePart) {
      errors = Optional.ofNullable(errors).orElse(new ArrayList<>());
      errors.add(Error.builder()
                      .code(error.getCode())
                      .message(String.format(error.getMessage(), messagePart))
                      .build());
      return errors;
    }

}

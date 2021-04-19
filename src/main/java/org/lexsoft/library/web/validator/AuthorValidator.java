package org.lexsoft.library.web.validator;

import java.util.ArrayList;
import java.util.List;
import org.lexsoft.library.core.exception.model.ErrorMessage.Error;
import org.lexsoft.library.web.model.AuthorDto;

public class AuthorValidator extends AbstractValidator {

  public List<Error> validate(List<AuthorDto> authors) {
      List<Error> errors = new ArrayList<>();
      validateMandatory("authors", authors,errors);
      authors.forEach(a -> {
        validateMandatory("firstName",a.getFirstName(), errors);
        validateMandatory("lastName",a.getLastName(),errors);
      });
      return errors;
    };

  }

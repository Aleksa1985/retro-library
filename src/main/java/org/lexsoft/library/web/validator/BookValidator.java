package org.lexsoft.library.web.validator;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.lexsoft.library.core.exception.ErrorMessages;
import org.lexsoft.library.core.exception.model.ErrorMessage.Error;
import org.lexsoft.library.utils.ExceptionUtils;
import org.lexsoft.library.web.model.BookDto;

@AllArgsConstructor
public class BookValidator extends AbstractValidator {

  private AuthorValidator authorValidator;

  public List<Error> validate(List<BookDto> books) {
    List<Error> errors = new ArrayList<>();

    books.forEach(b -> {
      validateMandatory("genre",b.getGenre(),errors);
      validateMandatory("isbn",b.getIsbn(),errors);
      validateMandatory("title",b.getTitle(),errors);
      validateMandatory("pageNumber",b.getPageNumber(),errors);
      validateIsbn(b.getIsbn(),errors);
      errors.addAll(authorValidator.validate(b.getAuthors()));
    });

    return errors;

  }

  private void validateIsbn(String isbn,List<Error> errors){
    if (isbn.length() != 13 && isbn.length() != 10) {
      ExceptionUtils.addError(ErrorMessages.INVALID_ESBN,errors,isbn);
    }
  }

}

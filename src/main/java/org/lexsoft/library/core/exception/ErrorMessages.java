package org.lexsoft.library.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {

  PARAM_MANDATORY(1000,"Parameter %s is mandatory."),
  BOOK_ALLREADY_EXIST(2000,"Book with title %s and genre %s already exists in the system."),
  EXISTING_ISBN(2001,"Book with isbn %s already exists in the system"),
  INVALID_ESBN(2002,"Isbn %s is in invalid format");


  Integer code;
  String message;


}

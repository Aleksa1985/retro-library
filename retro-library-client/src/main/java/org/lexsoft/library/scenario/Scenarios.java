package org.lexsoft.library.scenario;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Scenarios {

  EXIT(0,"Exit application"),
  RANDOMIZE_BOOK_IMPORT(1,"Randomize 20 imports for random Books and authors."),
  IMPORT_YOUR_BOOK(2,"Import your book."),
  SELECT_BOOKS(3,"Select books by isbn or all books");

  Integer code;
  String message;

  public static Scenarios selectScenario(Integer number){
    Scenarios scenario = null;
    for(Scenarios sc : Scenarios.values()){
      if(sc.getCode().intValue() == number.intValue()){
        scenario = sc;
        break;
      }
    }
    return scenario;
  }




}

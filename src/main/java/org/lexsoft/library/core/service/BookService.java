package org.lexsoft.library.core.service;

import java.util.List;
import org.lexsoft.library.core.model.BookEntity;

public interface BookService {

  List<BookEntity> findAllBooks();
  List<BookEntity> findBooksByIsbns(List<String> isbn);
  public List<BookEntity> saveBooks(List<BookEntity> bookEntities);
}

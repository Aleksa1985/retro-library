package org.lexsoft.library.core.service.impl;

import static java.util.stream.Collectors.groupingBy;

import com.github.isopropylcyanide.jdbiunitofwork.JdbiUnitOfWork;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ws.rs.core.Response.Status;

import lombok.AllArgsConstructor;

import org.lexsoft.library.core.exception.BusinessException;
import org.lexsoft.library.core.exception.ErrorMessages;
import org.lexsoft.library.core.exception.model.ErrorMessage.Error;
import org.lexsoft.library.core.model.AuthorBookAggregationEntity;
import org.lexsoft.library.core.model.AuthorEntity;
import org.lexsoft.library.core.model.BookEntity;
import org.lexsoft.library.core.repository.AuthorBookAggregationRepository;
import org.lexsoft.library.core.repository.BookRepository;
import org.lexsoft.library.core.service.AuthorService;
import org.lexsoft.library.core.service.BookService;

@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final AuthorService authorService;
  private final AuthorBookAggregationRepository authorBookAggregationRepository;

  @Override
  public List<BookEntity> findAllBooks() {
    List<BookEntity> allBooks = bookRepository.findAllBooks();
    return prepareResult(allBooks);
  }

  @Override
  public List<BookEntity> findBooksByIsbns(List<String> isbns) {
    List<BookEntity> byIsbns = bookRepository.findByIsbns(isbns);
    return prepareResult(byIsbns);
  }

  @Override
  @JdbiUnitOfWork
  public List<BookEntity> saveBooks(List<BookEntity> bookEntities) {

    // find all data to enable processing of the books
    List<AuthorEntity> allAuthors = authorService.findAllAuthors();
    List<BookEntity> allBooks = bookRepository.findAllBooks();

    List<String> titlesAndGenres =
        allBooks.stream().map(BookEntity::getTitleAndGenre).collect(Collectors.toList());
    List<String> isbns = allBooks.stream().map(BookEntity::getIsbn).collect(Collectors.toList());

    bookEntities.forEach( be -> {
          checkBook(be, titlesAndGenres, isbns);
          List<AuthorEntity> authorEntities = authorService.saveNewAuthors(be.getAuthors(), allAuthors);
          BookEntity bookEntity = bookRepository.insertBook(be);
          List<AuthorBookAggregationEntity> authorBooksAggregation = new ArrayList<>();
          authorEntities.forEach(ae -> {
                authorBooksAggregation.add(
                    AuthorBookAggregationEntity.builder()
                        .bookId(bookEntity.getId())
                        .authorId(ae.getId())
                        .build());
          });

          authorBookAggregationRepository.insertAuthorBookAggregation(authorBooksAggregation);

      });

    return findBooksByIsbns(
        bookEntities.stream().map(BookEntity::getIsbn).collect(Collectors.toList()));
  }

  private List<BookEntity> getFinalBookResultList(Map<Long, List<BookEntity>> mapOfBooks) {
    List<BookEntity> resultList = new ArrayList<>();
    mapOfBooks.forEach(
        (e, v) -> {
          BookEntity bookEntity = v.get(0);
          bookEntity.setAuthors(
              v.stream()
                  .map(s -> s.getAuthors())
                  .flatMap(List::stream)
                  .collect(Collectors.toList()));
          resultList.add(bookEntity);
        });
    return resultList;
  }

  private void checkBook(BookEntity book, List<String> namesAndGenres, List<String> isbns) {
    if (namesAndGenres.contains(book.getTitleAndGenre())) {
      throw new BusinessException(
          Status.BAD_REQUEST,
          Arrays.asList(
              Error.builder()
                  .code(ErrorMessages.BOOK_ALLREADY_EXIST.getCode())
                  .message(
                      String.format(
                          ErrorMessages.BOOK_ALLREADY_EXIST.getMessage(),
                          book.getTitle(),
                          book.getGenre()))
                  .build()));
    }
    if (isbns.contains(book.getIsbn())) {
      throw new BusinessException(
          Status.BAD_REQUEST,
          Arrays.asList(
              Error.builder()
                  .code(ErrorMessages.EXISTING_ISBN.getCode())
                  .message(String.format(ErrorMessages.EXISTING_ISBN.getMessage(), book.getIsbn()))
                  .build()));
    }
  }

  private List<BookEntity> prepareResult(List<BookEntity> books) {
    Map<Long, List<BookEntity>> collect = books.stream().collect(groupingBy(BookEntity::getId));
    return getFinalBookResultList(collect);
  }
}

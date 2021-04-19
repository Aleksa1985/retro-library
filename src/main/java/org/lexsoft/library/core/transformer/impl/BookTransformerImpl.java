package org.lexsoft.library.core.transformer.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.lexsoft.library.core.model.AuthorEntity;
import org.lexsoft.library.core.model.BookEntity;
import org.lexsoft.library.core.transformer.Transformer;
import org.lexsoft.library.web.model.AuthorDto;
import org.lexsoft.library.web.model.BookDto;

@AllArgsConstructor
public class BookTransformerImpl implements Transformer<BookDto, BookEntity> {

  private Transformer<AuthorDto, AuthorEntity> authorTransformer;

  @Override
  public BookEntity transform(BookDto bookDto) {
    return BookEntity.builder()
        .id(bookDto.getId())
        .genre(bookDto.getGenre())
        .isbn(bookDto.getIsbn())
        .pageNumber(bookDto.getPageNumber())
        .title(bookDto.getTitle())
        .authors(
            Optional.ofNullable(bookDto.getAuthors())
                .map(a -> authorTransformer.transformBatch(a))
                .orElse(Collections.emptyList()))
        .build();
  }

  @Override
  public List<BookEntity> transformBatch(List<BookDto> list) {
    return list.stream().map(e -> transform(e)).collect(Collectors.toList());
  }

  @Override
  public BookDto transformReverse(BookEntity bookEntity) {
    return BookDto.builder()
                     .id(bookEntity.getId())
                     .genre(bookEntity.getGenre())
                     .isbn(bookEntity.getIsbn())
                     .pageNumber(bookEntity.getPageNumber())
                     .title(bookEntity.getTitle())
                     .authors(
                         Optional.ofNullable(bookEntity.getAuthors())
                                 .map(a -> authorTransformer.transformReverseBatch(a))
                                 .orElse(Collections.emptyList()))
                     .build();
  }

  @Override
  public List<BookDto> transformReverseBatch(List<BookEntity> list) {
    return list.stream().map(e -> transformReverse(e)).collect(Collectors.toList());
  }
}

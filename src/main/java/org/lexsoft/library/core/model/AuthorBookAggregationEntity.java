package org.lexsoft.library.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorBookAggregationEntity {

  @ColumnName("id")
  Long id;

  @ColumnName("author_id")
  private Long authorId;

  @ColumnName("book_id")
  private Long bookId;

}

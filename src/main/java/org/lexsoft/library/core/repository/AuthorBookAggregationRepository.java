package org.lexsoft.library.core.repository;

import java.util.List;
import org.jdbi.v3.sqlobject.customizer.BindBeanList;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.lexsoft.library.core.model.AuthorBookAggregationEntity;

public interface AuthorBookAggregationRepository {

  @SqlUpdate("insert into authors_books (author_id, book_id) values <authors_books>")
  void insertAuthorBookAggregation(@BindBeanList(value = "authors_books", propertyNames = {"authorId", "bookId"}) List<AuthorBookAggregationEntity> authorsBooks);

}

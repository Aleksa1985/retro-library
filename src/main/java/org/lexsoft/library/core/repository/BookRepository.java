package org.lexsoft.library.core.repository;

import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.lexsoft.library.core.model.BookEntity;
import org.lexsoft.library.core.repository.mapper.BookDaoMapper;
import org.lexsoft.library.core.repository.mapper.InsertBookMapper;

public interface BookRepository {

  String BASE_SELECT_QUERY = "SELECT b.id,b.isbn ,b.title ,b.genre ,b.page_number,a.id AS author_id, a.first_name,a.last_name \n" +
      "FROM books b INNER JOIN authors_books ab ON b.id = ab.book_id \n" +
      "INNER JOIN authors a ON ab.author_id = a.id";

  @SqlUpdate("insert into books (page_number, genre, isbn, title) values (:pageNumber, :genre, :isbn, :title )")
  @GetGeneratedKeys
  @RegisterRowMapper(InsertBookMapper.class)
  BookEntity insertBook(@BindBean BookEntity book);

  @SqlQuery(BASE_SELECT_QUERY + " where b.isbn in (<isbns>)")
  @RegisterRowMapper(BookDaoMapper.class)
  List<BookEntity> findByIsbns(@BindList(value = "isbns") List<String> isbns);

  @SqlQuery(BASE_SELECT_QUERY)
  @RegisterRowMapper(BookDaoMapper.class)
  List<BookEntity> findAllBooks();



}




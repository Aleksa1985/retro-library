package org.lexsoft.library.core.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.lexsoft.library.core.model.AuthorEntity;
import org.lexsoft.library.core.model.BookEntity;

@AllArgsConstructor
public class InsertBookMapper implements RowMapper<BookEntity> {

  @Override
  public BookEntity map(ResultSet rs, StatementContext ctx) throws SQLException {
    BookEntity book = new BookEntity();
    book.setId(rs.getLong("id"));
    book.setGenre(rs.getString("genre"));
    book.setIsbn(rs.getString("isbn"));
    book.setPageNumber(rs.getInt("page_number"));
    book.setTitle(rs.getString("title"));
    return book;
  }

}

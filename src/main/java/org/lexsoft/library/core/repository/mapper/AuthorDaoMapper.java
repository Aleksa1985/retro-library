package org.lexsoft.library.core.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.lexsoft.library.core.model.AuthorEntity;
import org.lexsoft.library.core.model.BookEntity;

@AllArgsConstructor
public class AuthorDaoMapper implements RowMapper<AuthorEntity> {

  @Override
  public AuthorEntity map(ResultSet rs, StatementContext ctx) throws SQLException {
    AuthorEntity authorEntity = new AuthorEntity();
    authorEntity.setId(rs.getLong("id"));
    authorEntity.setFirstName(rs.getString("first_name"));
    authorEntity.setLastName(rs.getString("last_name"));

    return authorEntity;
  }

}

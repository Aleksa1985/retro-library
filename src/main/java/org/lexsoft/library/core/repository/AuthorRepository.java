package org.lexsoft.library.core.repository;

import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindBeanList;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.lexsoft.library.core.model.AuthorEntity;
import org.lexsoft.library.core.repository.mapper.AuthorDaoMapper;

public interface AuthorRepository {

  @SqlUpdate("insert into authors (first_name, last_name) values (:firstName, :lastName)")
  @RegisterRowMapper(AuthorDaoMapper.class)
  @GetGeneratedKeys
  AuthorEntity insertAuthors(@BindBean AuthorEntity author);

  @SqlQuery("select a.id,a.first_name,a.last_name from authors a")
  @RegisterRowMapper(AuthorDaoMapper.class)
  List<AuthorEntity> getAllAuthors();


}

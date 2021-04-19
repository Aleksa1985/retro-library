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
public class AuthorEntity {

  @ColumnName("id")
  Long id;

  @ColumnName("first_name")
  private String firstName;

  @ColumnName("last_name")
  private String lastName;

  public String getFullName(){
    return this.firstName + " " + this.lastName;
  }

}

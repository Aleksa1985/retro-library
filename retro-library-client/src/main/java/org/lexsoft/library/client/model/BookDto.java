package org.lexsoft.library.client.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

  private Long id;
  private List<AuthorDto> authors = new ArrayList<>();
  private Integer pageNumber;
  private String genre;
  private String isbn;
  private String title;

}

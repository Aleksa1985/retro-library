package org.lexsoft.library.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {

  @JsonProperty
  private Long id;
  @JsonProperty
  private List<AuthorDto> authors;
  @JsonProperty
  private Integer pageNumber;
  @JsonProperty
  private String genre;
  @JsonProperty
  private String isbn;
  @JsonProperty
  private String title;

}

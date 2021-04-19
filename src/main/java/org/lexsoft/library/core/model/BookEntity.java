package org.lexsoft.library.core.model;

import java.util.ArrayList;
import java.util.List;
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
public class BookEntity {

    @ColumnName("id")
    private Long id;
    @ColumnName("title")
    private String title;
    @ColumnName("page_number")
    private Integer pageNumber;
    @ColumnName("genre")
    private String genre;
    @ColumnName("isbn")
    private String isbn;

    public List<AuthorEntity> authors = new ArrayList<>();

    public String getTitleAndGenre(){ return this.title + " " + this.genre; }



}

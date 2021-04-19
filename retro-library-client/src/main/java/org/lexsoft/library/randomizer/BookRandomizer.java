package org.lexsoft.library.randomizer;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.lexsoft.library.client.model.AuthorDto;
import org.lexsoft.library.client.model.BookDto;

@AllArgsConstructor
public class BookRandomizer {

  private static final String[] GENRES = {"crime", "comedy", "horror", "classic", "science"};

  Random random;
  Faker faker;

  public List<BookDto> getRandomBooks(Integer number){
    List<BookDto> books = new ArrayList<>();
    if( Objects.nonNull(number)){
      for (int i = 0; i < number; i++) {
        books.add(randomizeBook());
      }
    }else{
      for (int i = 0; i < 20 ; i++){
        books.add(randomizeBook());
      }
    }
   return books;
  }


  private BookDto randomizeBook() {

    BookDto bookDto = new BookDto();
    bookDto.setGenre(getRandomGenre());
    bookDto.setIsbn(getRandomISBN());
    bookDto.setTitle(faker.book().title());
    bookDto.setPageNumber(getRandom(70,960));

    Integer random = getRandom(1, 4);
    for (int i = 0; i < random; i++) {
      bookDto.getAuthors().add(generateRandomAuthor());
    }
    return bookDto;
  }

  private AuthorDto generateRandomAuthor(){
    AuthorDto authorDto = new AuthorDto();
    authorDto.setFirstName(faker.name().firstName());
    authorDto.setLastName(faker.name().lastName());
    return authorDto;
  }


  private String getRandomGenre() {
    int genreId = new Random().nextInt(GENRES.length);
    return GENRES[genreId];
  }

  private String getRandomISBN() {
    return RandomStringUtils.randomAlphanumeric(10);
  }

  private Integer getRandom(int from, int to) {
    return RandomUtils.nextInt(from, to);
  }


}

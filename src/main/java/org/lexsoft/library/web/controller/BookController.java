package org.lexsoft.library.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import lombok.AllArgsConstructor;
import org.lexsoft.library.core.model.BookEntity;
import org.lexsoft.library.core.service.BookService;
import org.lexsoft.library.core.transformer.Transformer;
import org.lexsoft.library.web.model.BookDto;
import org.lexsoft.library.web.validator.BookValidator;

@Path("/books")
@AllArgsConstructor
public class BookController {

  private final BookService bookService;
  private final BookValidator bookValidator;
  private final Transformer<BookDto, BookEntity> transformer;

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_JSON})
  public Response saveBooks(List<BookDto> books) {
    bookValidator.cancelProcessingAndThrowException(bookValidator.validate(books));
    List<BookEntity> booksToBeStored = transformer.transformBatch(books);
    List<BookEntity> savedBooks = bookService.saveBooks(booksToBeStored);
    List<BookDto> bookDtos = transformer.transformReverseBatch(savedBooks);
    return Response.status(Status.OK).entity(bookDtos).build();
  }

  @GET
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_JSON})
  public Response findBooks(@QueryParam(value = "isbn") String isbn) {
    List<BookEntity> allBooks = Optional.ofNullable(isbn)
            .map(i -> bookService.findBooksByIsbns(Arrays.asList(i)))
            .orElseGet(() -> bookService.findAllBooks());
    List<BookDto> bookDtos = transformer.transformReverseBatch(allBooks);
    return Response.ok(bookDtos).build();
  }
}

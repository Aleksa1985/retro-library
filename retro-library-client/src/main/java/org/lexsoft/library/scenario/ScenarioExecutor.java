package org.lexsoft.library.scenario;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.lexsoft.library.Configuration;
import org.lexsoft.library.client.RetroHttpClient;
import org.lexsoft.library.client.model.BookDto;
import org.lexsoft.library.printer.ConsolePrinter;
import org.lexsoft.library.randomizer.BookRandomizer;

@AllArgsConstructor
public class ScenarioExecutor {

  RetroHttpClient retroHttpClient;
  ConsolePrinter consolePrinter;
  BookRandomizer bookRandomizer;
  Gson objectMapper;


  public void executeScenario(Scenarios scenarios)  {
    try{
    switch (scenarios){
      case EXIT -> System.exit(0);
      case SELECT_BOOKS -> selectBooks();
      case IMPORT_YOUR_BOOK ->  importYourBook();
      case RANDOMIZE_BOOK_IMPORT -> randomizeBookImport();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    executeScenario(consolePrinter.printAndSelectScenario());
  }


  private void selectBooks() throws IOException {
    String isbn = consolePrinter.printAndTakeInputForIsbn();
    String uri = Configuration.RETRO_API_URL_ROOT.concat("/books");
    if(!StringUtils.isEmpty(isbn)) uri = uri.concat("?isbn=").concat(isbn);
    String response = retroHttpClient.createGetRequest(uri);
    consolePrinter.printResponseFromService(response);
  }

  private void importYourBook() throws IOException {
    String booksBody = consolePrinter.insertYourBook();
    if(!StringUtils.isEmpty(booksBody)){
      String uri = Configuration.RETRO_API_URL_ROOT.concat("/books");
      String response = retroHttpClient.createPostRequest(uri, booksBody);
      consolePrinter.printResponseFromService(response);
    } else {
      importYourBook();
    }
  }

  private void randomizeBookImport() throws IOException {
    Integer integer = consolePrinter.insertRandomizeBookNumber();
    List<BookDto> randomBooks = bookRandomizer.getRandomBooks(integer);
    String uri = Configuration.RETRO_API_URL_ROOT.concat("/books");
    String response = retroHttpClient.createPostRequest(uri, objectMapper.toJson(randomBooks));
    consolePrinter.printResponseFromService(response);
  }





}

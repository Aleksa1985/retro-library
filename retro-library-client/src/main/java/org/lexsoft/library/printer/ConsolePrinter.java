package org.lexsoft.library.printer;

import java.util.Objects;
import java.util.Scanner;
import lombok.AllArgsConstructor;
import org.lexsoft.library.scenario.Scenarios;

@AllArgsConstructor
public class ConsolePrinter {

  private static final String LINE_DELIMITER = "*****************************************";

  Scanner scanner;

  public void printWelcomeMessage(){
    System.out.println(LINE_DELIMITER);
    System.out.println(LINE_DELIMITER);
    System.out.println(" WELCOME TO THE RETRO LIBRARY CLIENT ");
    System.out.println(LINE_DELIMITER);
    System.out.println(LINE_DELIMITER);
  }

  public Scenarios printAndSelectScenario(){
    Scenarios scenarioResult = null;
    System.out.println("*************************************************************");
    System.out.println("Please select on of possible scenarios by typing of scenario:");
    for(Scenarios sc : Scenarios.values()){
      System.out.println(String.valueOf(sc.getCode()).concat(" : ").concat(sc.getMessage()));
    }
    int i = scanner.nextInt();
    Scenarios scenarios = Scenarios.selectScenario(i);
    if(Objects.nonNull(scenarios)){
      System.out.println("You have selected scenario identified by number : " + i);
      scenarioResult = scenarios;
    }else{
      System.out.println("You have selected  non existing scenario. Please try again ");
      printAndSelectScenario();
    }
    return scenarioResult;
  }

  public String printAndTakeInputForIsbn(){
    System.out.println("Provide ISBN number for specific book:");
    String isbn = scanner.nextLine();
    isbn = scanner.nextLine();
    return isbn;
  }

  public void printResponseFromService(String resonse){
    System.out.println("RESPONSE : ");
    System.out.println(resonse);
  }

  public String insertYourBook(){
    System.out.println("Insert your book in json format, otherwise it will not be processed:");
    String booksBody = scanner.nextLine();
    return booksBody;
  }
  public Integer insertRandomizeBookNumber(){
    System.out.println("pick number of books to be randomized. Default will be 20.");
    Integer i = scanner.nextInt();
    return i;
  }




}

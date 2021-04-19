package org.lexsoft.library.container;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import java.util.Random;
import java.util.Scanner;
import lombok.Getter;
import org.lexsoft.library.client.RetroHttpClient;
import org.lexsoft.library.printer.ConsolePrinter;
import org.lexsoft.library.randomizer.BookRandomizer;
import org.lexsoft.library.scenario.ScenarioExecutor;

@Getter
public class ComponentsContainer {

  Gson objectMapper;
  RetroHttpClient retroHttpClient;
  ConsolePrinter consolePrinter;
  Scanner scanner;
  ScenarioExecutor scenarioExecutor;
  Random random;
  Faker faker;
  BookRandomizer randomizer;

  public void prepareComponents(){
    this.faker = new Faker();
    this.random =  new Random();
    this.randomizer = new BookRandomizer(random,faker);
    this.scanner = new Scanner(System.in);
    this.objectMapper = new Gson();
    this.retroHttpClient = new RetroHttpClient(null);
    this.consolePrinter = new ConsolePrinter(scanner);
    this.scenarioExecutor = new ScenarioExecutor(retroHttpClient,consolePrinter,randomizer,objectMapper);
  }




}

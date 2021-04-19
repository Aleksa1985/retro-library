package org.lexsoft.library;

import org.lexsoft.library.container.ComponentsContainer;
import org.lexsoft.library.scenario.Scenarios;

public class RetroLibraryClient {

  public static void main(String[] args) {
    ComponentsContainer componentsContainer = new ComponentsContainer();
    componentsContainer.prepareComponents();
    componentsContainer.getConsolePrinter().printWelcomeMessage();
    Scenarios scenarios = componentsContainer.getConsolePrinter().printAndSelectScenario();
    componentsContainer.getScenarioExecutor().executeScenario(scenarios);
  }

}

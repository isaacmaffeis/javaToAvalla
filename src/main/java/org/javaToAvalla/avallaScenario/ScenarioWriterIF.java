package org.javaToAvalla.avallaScenario;

import org.javaToAvalla.model.Scenario;
import org.javaToAvalla.model.ScenarioFile;

/**
 * The {@code ScenarioWriterIF} interface defines the contract for writing a {@link Scenario}
 * object into a {@link ScenarioFile}.
 */
public interface ScenarioWriterIF {

  /**
   * Converts a given {@link Scenario} object into a {@link ScenarioFile}.
   *
   * @param scenario the {@link Scenario} object to be written into a file.
   * @return a {@link ScenarioFile} representing the written scenario.
   */
  public ScenarioFile write(Scenario scenario);

}

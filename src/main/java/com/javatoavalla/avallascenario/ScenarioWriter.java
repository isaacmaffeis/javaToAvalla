package com.javatoavalla.avallascenario;

import com.javatoavalla.model.Scenario;
import com.javatoavalla.model.ScenarioFile;

/**
 * The {@code ScenarioWriterIF} interface defines the contract for writing a {@link Scenario}
 * object into a {@link ScenarioFile}.
 */
public interface ScenarioWriter {

  /**
   * Converts a given {@link Scenario} object into a {@link ScenarioFile}.
   *
   * @param scenario the {@link Scenario} object to be written into a file.
   * @return a {@link ScenarioFile} representing the written scenario.
   */
  public ScenarioFile write(Scenario scenario);

}

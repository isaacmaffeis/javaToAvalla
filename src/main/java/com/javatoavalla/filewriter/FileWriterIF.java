package com.javatoavalla.filewriter;

import java.nio.file.Path;
import com.javatoavalla.model.ScenarioFile;

/**
 * Interface for writing {@link ScenarioFile} objects to a file.
 */
public interface FileWriterIF {

  /**
   * Writes the specified {@link ScenarioFile} to a default location.
   *
   * @param scenarioFile the {@link ScenarioFile} to write
   * @return {@code true} if the write operation was successful,
   *         {@code false} otherwise
   */
  boolean writeToFile(ScenarioFile scenarioFile);

  /**
   * Writes the specified {@link ScenarioFile} to the given output path.
   *
   * @param scenarioFile the {@link ScenarioFile} to write
   * @param outputFolder  the {@link Path} representing the desired output location
   * @return {@code true} if the write operation was successful,
   *         {@code false} otherwise
   */
  boolean writeToFile(ScenarioFile scenarioFile, Path outputFolder );
}

package com.javatoavalla.javascenario;

import java.nio.file.Path;
import java.util.List;
import com.javatoavalla.model.Scenario;
import com.javatoavalla.model.terms.JavaArgumentTerm;

/**
 * The {@code ScenarioReaderIF} interface defines the contract for reading and parsing
 * Java scenario files.
 */
public interface ScenarioReaderIF {

  /**
   * Reads a java scenario from the file at the specified {@code path}
   * and parses its content to retrieve a list of {@link Scenario} objects.
   *
   * @param path the {@link Path} to the file containing the scenario
   * @param javaArgumentTermList the Argument list of the stepFunction
   * @return a list of {@link Scenario} objects parsed from the file,
   * or an empty list if an error occurs
   */
  public List<Scenario> readJavaScenario(Path path, List<JavaArgumentTerm> javaArgumentTermList);

}

package com.javatoavalla.javascenario.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.javatoavalla.antlr.JavaScenarioLexer;
import com.javatoavalla.antlr.JavaScenarioParser;
import com.javatoavalla.javascenario.ScenarioReaderIF;
import com.javatoavalla.javascenario.listener.JavaScenarioListener;
import com.javatoavalla.model.Scenario;
import com.javatoavalla.model.terms.JavaArgumentTerm;

public class ScenarioReader implements ScenarioReaderIF {

  private final static Logger log = LogManager.getLogger(ScenarioReader.class);

  /**
   * Reads a java scenario from the file at the specified {@code path} and parses its content to
   * retrieve a list of {@link Scenario} objects.
   *
   * @param path the {@link Path} to the file containing the scenario
   * @param javaArgumentTermList the Argument list of the stepFunction
   * @return a list of {@link Scenario} objects parsed from the file, or an empty list if an error
   * occurs
   */
  @Override
  public List<Scenario> readJavaScenario(Path path, List<JavaArgumentTerm> javaArgumentTermList) {
    log.info("Reading the Scenario File at the path {}", path);
    String javaFile = null;
    try {
      byte[] fileBytes = Files.readAllBytes(path);
      javaFile = new String(fileBytes, StandardCharsets.UTF_8);
      log.debug("Content red: {} ",javaFile);
    } catch (IOException e) {
      log.error("An exception occurred while reading the file: {}", e.getMessage(), e);
    }

    JavaScenarioLexer javaScenarioLexer = new JavaScenarioLexer(CharStreams.fromString(javaFile));
    CommonTokenStream tokens = new CommonTokenStream(javaScenarioLexer);
    JavaScenarioParser javaScenarioParser = new JavaScenarioParser(tokens);
    ParseTreeWalker walker = new ParseTreeWalker();
    JavaScenarioListener javaScenarioWalker = new JavaScenarioListener(javaArgumentTermList);
    walker.walk(javaScenarioWalker, javaScenarioParser.start());

    return javaScenarioWalker.getScenarioList();
  }

}

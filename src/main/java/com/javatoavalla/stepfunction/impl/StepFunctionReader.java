package com.javatoavalla.stepfunction.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.javatoavalla.antlr.StepFunctionArgsLexer;
import com.javatoavalla.antlr.StepFunctionArgsParser;
import com.javatoavalla.model.terms.JavaArgumentTerm;
import com.javatoavalla.stepfunction.StepFunctionArgsListener;
import com.javatoavalla.stepfunction.StepFunctionReaderIF;

/**
 * The {@code StepFunctionReader} class provides functionality to read and parse step function
 * arguments from a file. It uses a lexer and parser generated from the {@code StepFunctionArgs}
 * grammar to convert the contents of the file into a list of {@link JavaArgumentTerm} objects.
 *
 * <p>The file can be specified via a {@link Path}, or if no path is provided, the reader uses
 * a default file located in the "input" directory within the project structure.</p>
 */
public class StepFunctionReader implements StepFunctionReaderIF {

  private final static Logger log = LogManager.getLogger(StepFunctionReader.class);

  /**
   * Default path of the stepFunctionArgs.
   */
  public static final Path DEFAULT_PATH = Paths.get(
      System.getProperty("user.dir"),
      "input",
      "StepFunctionArgs.txt");

  public StepFunctionReader() {
  }

  /**
   * {@inheritDoc}
   *
   * <p>The method reads the file contents, converts it to a string, and uses the
   * lexer and parser generated from the StepFunctionArgs grammar to walk the parse
   * tree and extract the argument list. If an error occurs while reading the file,
   * it logs the error.</p>
   *
   * @param path the {@link Path} to the file containing the step function definition
   * @return a list of {@link JavaArgumentTerm} objects parsed from the file, or an empty list if
   *          an error occurs
   */
  @Override
  public List<JavaArgumentTerm> readStepFunction(Path path) {
    log.info("Reading the StepFunctionArgs File at the path {}", path);
    String content = null;
    try {
      byte[] fileBytes = Files.readAllBytes(path);
      content = new String(fileBytes, StandardCharsets.UTF_8);
      log.debug("Content red: {} ",content);
    } catch (IOException e) {
      log.error("An exception occurred while reading the file: {}", e.getMessage(), e);
    }

    StepFunctionArgsLexer stepFunctionSupportLexer =
        new StepFunctionArgsLexer(CharStreams.fromString(content));
    CommonTokenStream tokens = new CommonTokenStream(stepFunctionSupportLexer);
    StepFunctionArgsParser stepFunctionSupportParser = new StepFunctionArgsParser(tokens);
    ParseTreeWalker walker = new ParseTreeWalker();
    StepFunctionArgsListener stepFunctionSupportWalker = new StepFunctionArgsListener();
    walker.walk(stepFunctionSupportWalker, stepFunctionSupportParser.argumentList());

    return stepFunctionSupportWalker.getArgumentList();
  }

  /**
   * {@inheritDoc}
   *
   * Call the {@code readStepFunction(Path path)} method with the DEFAULT_PATH as argument.
   *
   * @return a list of {@link JavaArgumentTerm} objects parsed from the file, or an empty list if an
   * error occurs
   */
  @Override
  public List<JavaArgumentTerm> readStepFunction() {
    return readStepFunction(DEFAULT_PATH);
  }

}

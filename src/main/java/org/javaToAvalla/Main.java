package org.javaToAvalla;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.javaToAvalla.avallaScenario.ScenarioListMapperIF;
import org.javaToAvalla.avallaScenario.impl.ScenarioListMapper;
import org.javaToAvalla.fileWriter.FileWriterIF;
import org.javaToAvalla.fileWriter.impl.FileWriter;
import org.javaToAvalla.javaScenario.ScenarioReaderIF;
import org.javaToAvalla.javaScenario.impl.ScenarioReader;
import org.javaToAvalla.model.Scenario;
import org.javaToAvalla.model.ScenarioFile;
import org.javaToAvalla.model.terms.JavaArgumentTerm;
import org.javaToAvalla.stepFunction.StepFunctionReaderIF;
import org.javaToAvalla.stepFunction.impl.StepFunctionReader;

public class Main {

  private final static Logger log = LogManager.getLogger(Main.class);

  private void runTheApplication(Path inputPath, Path stepFunctionArgsPath, Path outputPath){

    log.info("Processing StepFunctionArgs...");
    StepFunctionReaderIF stepFunctionReaderIF = new StepFunctionReader();
    List<JavaArgumentTerm> javaArgumentTermList = stepFunctionArgsPath == null ?
        stepFunctionReaderIF.readStepFunction() :
        stepFunctionReaderIF.readStepFunction(stepFunctionArgsPath);

    log.info("Processing JavaScenario...");
    ScenarioReaderIF scenarioReaderIF = new ScenarioReader();
    List<Scenario> scenarioList = scenarioReaderIF.readJavaScenario(inputPath,javaArgumentTermList);

    log.info("Mapping Scenario Files...");
    ScenarioListMapperIF scenarioListMapper = new ScenarioListMapper();
    List<ScenarioFile> scenarioFiles = scenarioListMapper.mapScenarioListToFileList(scenarioList);

    log.info("Exporting output Files...");
    for(ScenarioFile scenarioFile : scenarioFiles){
      FileWriterIF fileWriter = new FileWriter();
      boolean result = outputPath == null ?
          fileWriter.writeToFile(scenarioFile) :
          fileWriter.writeToFile(scenarioFile, outputPath);
      if(result){
        log.info("Generated: {} .", scenarioFile.getName());
      }
      else{
        log.error("Error generating: {} .", scenarioFile.getName());
      }
    }
    log.info("Operations completed.");
  }

  /**
   * Creates and returns the available command-line options for the tool.
   *
   * @return the command-line options.
   */
  public static Options getCommandLineOptions() {
    Options options = new Options();

    // print help
    Option help = new Option("help", "print this message");

    // input file
    Option input = Option.builder("input")
        .argName("input")
        .type(String.class)
        .hasArg(true)
        .desc("The Java input file (required)")
        .build();

    // stepFunctionArgs file
    Option stepFunctionArgs = Option.builder("stepFunctionArgs")
        .argName("stepFunctionArgs")
        .type(String.class)
        .hasArg(true)
        .desc("The stepFunctionArgs.txt file "
            + "(optional, defaults to `./input/stepFunctionArgs.txt`)")
        .build();

    // output directory
    Option output = Option.builder("output")
        .argName("output")
        .type(String.class)
        .hasArg(true)
        .desc("The output folder (optional, defaults to `./output/`)")
        .build();

    // clean option
    Option clean = Option.builder("clean")
        .argName("<boolean>")
        .type(String.class)
        .hasArg(true)
        .desc("Clean the input and the stepFunctionArgs files.")
        .build();

    options.addOption(help);
    options.addOption(input);
    options.addOption(stepFunctionArgs);
    options.addOption(output);
    options.addOption(clean);

    return options;
  }

  /**
   * Parses the command-line arguments using the provided options.
   *
   * @param args    the command-line arguments.
   * @param options the available command-line options.
   * @return        the parsed CommandLine object.
   */
  public CommandLine parseCommandLine(String[] args, Options options) {
    CommandLineParser parser = new DefaultParser();
    CommandLine line = null;
    try {
      line = parser.parse(options, args);
    } catch (ParseException e) {
      log.error("Failed to parse commandline arguments.");
    }
    return line;
  }

  /**
   * Executes the main process based on the command-line arguments.
   *
   * @param line    the parsed CommandLine object.
   * @param options the available command-line options.
   */
  private void execute (CommandLine line, Options options) throws FileNotFoundException {

    String input = line.getOptionValue("input");
    Path inputPath = Paths.get(input);
    checkFile(inputPath);

    Path stepFunctionArgsPath = null;
    if(line.hasOption("stepFunctionArgs")){
      String stepFunctionArgs = line.getOptionValue("stepFunctionArgs");
      stepFunctionArgsPath = Paths.get(stepFunctionArgs);
      checkFile(stepFunctionArgsPath);
    }

    Path outputFolderPath = null ;
    if(line.hasOption("output")){
      String outputFolder = line.getOptionValue("output");
      outputFolderPath = Paths.get(outputFolder);
      checkFolder(outputFolderPath);
    }

    runTheApplication(inputPath, stepFunctionArgsPath, outputFolderPath);

    if(line.hasOption("clean") && Boolean.parseBoolean(line.getOptionValue("clean"))){
      clean(inputPath);
      if(line.hasOption("stepFunctionArgsPath")){
        clean(stepFunctionArgsPath);
      }
      else{
        clean(StepFunctionReader.DEFAULT_PATH);
      }
    }
  }

  private void checkFile(Path path) throws FileNotFoundException {
    if(!path.toFile().isFile()) {
      throw new FileNotFoundException("Specified file doesn't exist: " + path);
    }
  }

  private void checkFolder(Path path) throws FileNotFoundException {
    if(!path.toFile().exists()) {
      throw new FileNotFoundException("Specified folder doesn't exist: " + path);
    }
  }

  private void clean(Path path){
    try {
      Files.delete(path);
    } catch (IOException e) {
      throw new RuntimeException("Errors while cleaning the path: " +path,e);
    }
  }

  public static void main(String[] args) {
    try {
      Main main = new Main();
      Options options = getCommandLineOptions();
      CommandLine line = main.parseCommandLine(args, options);
      log.info("Performing requested operation ...");
      if (line == null || line.hasOption("help") || line.getOptions().length == 0) {
        HelpFormatter formatter = new HelpFormatter();
        // Do not sort
        formatter.setOptionComparator(null);
        // Header and footer strings
        String header = "AvallaToJava\n\n";
        String footer = "\n";

        formatter.printHelp("AvallaToJava",header, options, footer , false);
      }else if(!line.hasOption("input")){
        log.error("Please specify the Java input file path");
        throw new Exception("Input option can't be null");
      }
      else{
        main.execute(line, options);
      }
      log.info("Requested operation completed.");
    } catch (Exception e) {
      log.error("An error occurred, {}", e.getMessage(), e);
      System.exit(1);
    }
    System.exit(0);

  }
}
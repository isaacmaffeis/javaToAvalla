package org.javaToAvalla.fileWriter.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.javaToAvalla.fileWriter.FileWriterIF;
import org.javaToAvalla.model.ScenarioFile;

/**
 * The {@code FileWriter} class provides methods to write a {@link ScenarioFile}
 * to a specified output folder or a default folder in the project root directory.
 * It implements the {@link FileWriterIF} interface.
 */
public class FileWriter implements FileWriterIF {

  /**
   * The default output path where files will be written if no specific folder is provided.
   * This is the "output" folder within the project root.
   */
  private final Path DEFAULT_OUTPUT_PATH = Paths.get(
      System.getProperty("user.dir"), "output");

  /**
   * Constructs a {@code FileWriter} instance.
   */
  public FileWriter() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean writeToFile(ScenarioFile scenarioFile) {
    return write(scenarioFile, DEFAULT_OUTPUT_PATH);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean writeToFile(ScenarioFile scenarioFile, Path outputFolder) {
    return write(scenarioFile, outputFolder);
  }

  /**
   * Writes the given {@link ScenarioFile} to the specified output folder.
   * Ensures that the folder exists and creates it if necessary.
   *
   * @param scenarioFile The scenario file to be written.
   * @param outputFolder The folder where the file will be saved.
   * @return {@code true} if the file was successfully written, {@code false} otherwise.
   * @throws RuntimeException if an I/O error occurs during directory creation or file writing.
   */
  private boolean write(ScenarioFile scenarioFile, Path outputFolder) {

    try {
      if (Files.notExists(outputFolder)) {
        Files.createDirectories(outputFolder);
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to create output directory: " + outputFolder, e);
    }

    Path fullOutputPath = outputFolder.resolve(
        scenarioFile.getName() + scenarioFile.getExtension());

    boolean result = false;
    try {
      Files.write(fullOutputPath, scenarioFile.getText().getBytes(StandardCharsets.UTF_8));
      result = true;
    } catch (IOException e) {
      throw new RuntimeException("Error writing to file: " + fullOutputPath, e);
    }

    return result;
  }

  /**
   * Checks if the given path exists.
   *
   * @param path The path to check.
   * @return {@code true} if the path exists, {@code false} otherwise.
   */
  private boolean checkPath(Path path) {
    return Files.exists(path);
  }

  /**
   * Creates a directory at the specified path if it does not exist.
   * Prints a message if the directory is created or an error occurs.
   *
   * @param path The path where the directory should be created.
   */
  private void createPath(Path path) {
    try {
      Files.createDirectories(path);
      System.out.println("Directory created: " + path.toAbsolutePath());
    } catch (IOException e) {
      System.err.println("Failed to create directory: " + e.getMessage());
    }
  }
}

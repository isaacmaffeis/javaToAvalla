package org.javaToAvalla.fileWriter.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.javaToAvalla.model.ScenarioFile;
import org.javaToAvalla.util.ScenarioAvallaUtil;
import org.junit.Test;

public class FileWriterTest {

  @Test
  public void writeToFileAndCheckThatFileIsCreated(){

    FileWriter fileWriter = new FileWriter();
    ScenarioFile scenarioFile = ScenarioAvallaUtil.getScenarioFile();

    boolean result = fileWriter.writeToFile(scenarioFile);
    assertTrue(result);

    Path outputFile = Paths.get(
        System.getProperty("user.dir"),
        "output",
        scenarioFile.getName(),
        scenarioFile.getExtension());
    Files.exists(outputFile);

  }

}

package org.javaToAvalla.avallaScenario.impl;

import static org.junit.Assert.assertEquals;

import org.javaToAvalla.model.Scenario;
import org.javaToAvalla.model.ScenarioFile;
import org.javaToAvalla.util.ScenarioAvallaUtil;
import org.junit.Test;

public class ScenarioWriterTest {

  @Test
  public void whenWriteScenario_thenScenarioFileIsCreated(){

    Scenario scenario = ScenarioAvallaUtil.getScenarioAvalla();

    ScenarioWriter scenarioWriter = new ScenarioWriter();
    ScenarioFile scenarioFile = scenarioWriter.write(scenario);

    System.out.println(scenarioFile.getText());
    assertEquals(scenarioFile.getText(),ScenarioAvallaUtil.getAvallaScenario());
    assertEquals(scenarioFile.getName(),ScenarioAvallaUtil.avallaHeaderTerm().getScenarioName());
  }

}

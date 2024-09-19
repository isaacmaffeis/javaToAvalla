package com.javatoavalla.avallascenario.impl;

import static org.junit.Assert.assertEquals;

import com.javatoavalla.model.Scenario;
import com.javatoavalla.model.ScenarioFile;
import com.javatoavalla.util.ScenarioAvallaUtil;
import org.junit.Test;

public class ScenarioWriterTest {

  @Test
  public void whenWriteScenario_thenScenarioFileIsCreated(){

    Scenario scenario = ScenarioAvallaUtil.getScenarioAvalla();

    ScenarioWriter scenarioWriter = new ScenarioWriter();
    ScenarioFile scenarioFile = scenarioWriter.write(scenario);

    assertEquals(scenarioFile.getText(),ScenarioAvallaUtil.getAvallaScenario());
    assertEquals(scenarioFile.getName(),ScenarioAvallaUtil.avallaHeaderTerm().getScenarioName());
  }

}

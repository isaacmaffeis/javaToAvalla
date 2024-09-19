package com.javatoavalla.avallascenario.impl;

import static org.junit.Assert.assertEquals;

import com.javatoavalla.model.Scenario;
import com.javatoavalla.model.ScenarioFile;
import com.javatoavalla.util.ScenarioAvallaUtil;
import org.junit.Test;

public class ScenarioWriterImplTest {

  @Test
  public void whenWriteScenario_thenScenarioFileIsCreated(){

    Scenario scenario = ScenarioAvallaUtil.getScenarioAvalla();

    ScenarioWriterImpl scenarioWriterImpl = new ScenarioWriterImpl();
    ScenarioFile scenarioFile = scenarioWriterImpl.write(scenario);

    assertEquals(scenarioFile.getText(),ScenarioAvallaUtil.getAvallaScenario());
    assertEquals(scenarioFile.getName(),ScenarioAvallaUtil.avallaHeaderTerm().getScenarioName());
  }

}

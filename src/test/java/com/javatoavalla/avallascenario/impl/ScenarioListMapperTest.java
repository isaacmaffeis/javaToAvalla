package com.javatoavalla.avallascenario.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;
import com.javatoavalla.model.Scenario;
import com.javatoavalla.model.ScenarioFile;
import com.javatoavalla.util.ScenarioAvallaUtil;
import org.junit.Test;

public class ScenarioListMapperTest {

  @Test
  public void mapScenarioListAndCheckResults(){

    Scenario scenario1 = ScenarioAvallaUtil.getScenarioAvalla();
    Scenario scenario2 = ScenarioAvallaUtil.getScenarioAvalla();

    List<Scenario> scenarioList = new LinkedList<>();

    scenarioList.add(scenario1);
    scenarioList.add(scenario2);

    ScenarioListMapper scenarioListMapper = new ScenarioListMapper();

    List<ScenarioFile> scenarioFileList =
        scenarioListMapper.mapScenarioListToFileList(scenarioList);

    assertFalse(scenarioFileList.isEmpty());
    assertEquals(scenarioFileList.size(),2);

    ScenarioFile scenarioFile = scenarioFileList.remove(0);

    assertEquals(scenarioFile.getText(),ScenarioAvallaUtil.getAvallaScenario());
    assertEquals(scenarioFile.getName(),ScenarioAvallaUtil.avallaHeaderTerm().getScenarioName());

    scenarioFile = scenarioFileList.remove(0);

    assertEquals(scenarioFile.getText(),ScenarioAvallaUtil.getAvallaScenario());
    assertEquals(scenarioFile.getName(),ScenarioAvallaUtil.avallaHeaderTerm().getScenarioName());

    assertTrue(scenarioFileList.isEmpty());

  }

}

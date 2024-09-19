package com.javatoavalla.avallascenario.impl;

import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.javatoavalla.avallascenario.ScenarioListMapperIF;
import com.javatoavalla.avallascenario.ScenarioWriterIF;
import com.javatoavalla.model.Scenario;
import com.javatoavalla.model.ScenarioFile;

/**
 * The {@code ScenarioListMapper} class implements the {@link ScenarioListMapperIF} interface
 * and is responsible for mapping a list of {@link Scenario} objects to a list of
 * {@link ScenarioFile} objects.
 * It uses the {@link ScenarioWriterIF} to perform the conversion of each valid {@link Scenario}.
 */
public class ScenarioListMapper implements ScenarioListMapperIF {

  private static final Logger log = LogManager.getLogger(ScenarioListMapper.class);

  /**
   * The {@link ScenarioWriterIF} used to convert each {@link Scenario} into a {@link ScenarioFile}.
   */
  private final ScenarioWriterIF scenarioWriter;

  /**
   * Constructs a new {@code ScenarioListMapper} and initializes the {@code ScenarioWriter}.
   */
  public ScenarioListMapper() {
    this.scenarioWriter = new ScenarioWriter();
  }

  /**
   * {@inheritDoc}
   *
   * <p>This method iterates through the given list of {@link Scenario} objects and converts each
   * valid {@link Scenario} into a {@link ScenarioFile} using the {@link ScenarioWriterIF}.</p>
   *
   * @param scenarioList the list of {@link Scenario} objects to be mapped to {@link ScenarioFile}
   *                    objects.
   * @return a list of {@link ScenarioFile} objects corresponding to the valid {@link Scenario}
   *                    objects in the input list.
   */
  @Override
  public List<ScenarioFile> mapScenarioListToFileList(List<Scenario> scenarioList) {
    log.debug("Mapping ScenarioList to ScenarioFile");
    List<ScenarioFile> scenarioFiles = new LinkedList<>();
    for (Scenario scenario : scenarioList){
      log.debug("Processing the scenario: {}",scenario);
      if(scenario.isValid()){
        log.debug("Scenario is valid, mapping scenario to ScenarioFile");
        scenarioFiles.add(scenarioWriter.write(scenario));
      }
    }
    return scenarioFiles;
  }

}

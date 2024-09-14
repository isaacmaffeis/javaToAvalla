package org.javaToAvalla.javaScenario.impl;

import java.util.List;
import org.javaToAvalla.javaScenario.ScenarioManagerIF;
import org.javaToAvalla.model.Scenario;
import org.javaToAvalla.model.SetTerm;
import org.javaToAvalla.model.StepTerm;
import org.javaToAvalla.model.Variable;

public class ScenarioManager implements ScenarioManagerIF {

  public ScenarioManager() {
  }

  /**
   *
   */
  @Override
  public void setHeader() {

  }

  /**
   *
   */
  @Override
  public void setLoad() {

  }

  /**
   * @param variablesList
   */
  @Override
  public void setSetTerm(Scenario avallaScenario, List<Variable> variablesList) {
    for(Variable variable: variablesList){
      String name = variable.getName();
      String value = retrieveValue(variable);
      SetTerm setTerm = new SetTerm(name, value);
      avallaScenario.getScenario().add(setTerm);
    }
  }

  /**
   *
   */
  @Override
  public void setStepTerm(Scenario avallaScenario) {
    StepTerm stepTerm = new StepTerm();
    avallaScenario.getScenario().add(stepTerm);
  }

  /**
   * @param assertEquals
   */
  @Override
  public void setCheckTerm(Scenario avallaScenario, Object assertEquals) {

  }

  private String retrieveValue(Variable variable){
    String value = variable.getValue();
    return variable.isPrimitive() ?
        value :
        value.substring(value.lastIndexOf('.')+1);
  }

}

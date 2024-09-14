package org.javaToAvalla.javaScenario.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.javaToAvalla.javaScenario.TermManagerIF;
import org.javaToAvalla.model.AvallaTerm;
import org.javaToAvalla.model.SetTerm;
import org.javaToAvalla.model.StepTerm;
import org.javaToAvalla.model.Variable;

public class ScenarioManager implements TermManagerIF {

  private final Queue<AvallaTerm> avallaScenario;

  public ScenarioManager() {
    this.avallaScenario = new LinkedList<>();
  }
  
  /**
   * @param variablesList
   */
  @Override
  public void setSetTerm(List<Variable> variablesList) {
    for(Variable variable: variablesList){
      String name = variable.getName();
      String value = retrieveValue(variable);
      SetTerm setTerm = new SetTerm(name, value);
      this.avallaScenario.add(setTerm);
    }
  }

  /**
   *
   */
  @Override
  public void setStepTerm() {
    StepTerm stepTerm = new StepTerm();
    this.avallaScenario.add(stepTerm);
  }

  /**
   * @param assertEquals
   */
  @Override
  public void setCheckTerm(Object assertEquals) {

  }

  public Queue<AvallaTerm> getAvallaScenario() {
    return avallaScenario;
  }

  private String retrieveValue(Variable variable){
    String value = variable.getValue();
    return variable.isPrimitive() ?
        value :
        value.substring(value.lastIndexOf('.')+1);
  }

}

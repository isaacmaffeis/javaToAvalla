package org.javaToAvalla.javaScenario.impl;

import java.util.List;
import org.javaToAvalla.javaScenario.ScenarioManagerIF;
import org.javaToAvalla.model.terms.AvallaCheckTerm;
import org.javaToAvalla.model.terms.AvallaHeaderTerm;
import org.javaToAvalla.model.terms.AvallaLoadTerm;
import org.javaToAvalla.model.Scenario;
import org.javaToAvalla.model.terms.AvallaSetTerm;
import org.javaToAvalla.model.terms.AvallaStepTerm;
import org.javaToAvalla.model.terms.JavaAssertionTerm;
import org.javaToAvalla.model.terms.JavaVariableTerm;

public class ScenarioManager implements ScenarioManagerIF {

  public ScenarioManager() {
  }

  /**
   *
   */
  @Override
  public void setHeaderTerm(Scenario avallaScenario, String asmName, int scenarioIndex) {
    AvallaHeaderTerm avallaHeaderTerm = new AvallaHeaderTerm();
    avallaHeaderTerm.setScenarioName(retrieveAsmName(asmName) + "_" + scenarioIndex);
    avallaScenario.add(avallaHeaderTerm);
  }

  /**
   *
   */
  @Override
  public void setLoadTerm(Scenario avallaScenario, String asmName) {
    AvallaLoadTerm avallaLoadTerm = new AvallaLoadTerm();
    avallaLoadTerm.setLoad(retrieveAsmName(asmName));
    avallaScenario.add(avallaLoadTerm);
  }

  /**
   * @param variablesList
   */
  @Override
  public void setSetTerm(Scenario avallaScenario, List<JavaVariableTerm> variablesList) {
    for(JavaVariableTerm javaVariable : variablesList){
      String name = javaVariable.getName();
      String value = retrieveValue(javaVariable);
      AvallaSetTerm avallaSetTerm = new AvallaSetTerm(name, value);
      avallaScenario.add(avallaSetTerm);
    }
  }

  /**
   *
   */
  @Override
  public void setStepTerm(Scenario avallaScenario) {
    AvallaStepTerm avallaStepTerm = new AvallaStepTerm();
    avallaScenario.add(avallaStepTerm);
  }

  /**
   *
   * @param avallaScenario
   * @param javaAssertionTerm
   */
  @Override
  public void setCheckTerm(Scenario avallaScenario, JavaAssertionTerm javaAssertionTerm) {
    AvallaCheckTerm avallaCheckTerm = new AvallaCheckTerm();
    avallaCheckTerm.setActual(retrieveActual(javaAssertionTerm.getActual()));
    avallaCheckTerm.setExpected(retrieveExpected(javaAssertionTerm.getExpected()));
    avallaScenario.add(avallaCheckTerm);
  }

  private String retrieveValue(JavaVariableTerm javaVariable){
    String value = javaVariable.getValue();
    return javaVariable.isPrimitive() ?
        value :
        value.substring(value.lastIndexOf('.')+1);
  }

  private String retrieveAsmName(String asmName){
    return asmName.substring(0,asmName.lastIndexOf("_ASM"));
  }

  private String retrieveActual(String actual){
    return actual.substring(actual.lastIndexOf(".")+1);
  }

  private String retrieveExpected(String expected){
    return expected.substring(
        expected.lastIndexOf(".get_")+5).replaceAll("\\(\\)","");
  }

}

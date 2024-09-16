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

/**
 * The {@code ScenarioManager} class implements the {@link ScenarioManagerIF} interface.
 * It manages the addition of various terms (header, load, set, step, and check) to the
 * Avalla scenario.
 */
public class ScenarioManager implements ScenarioManagerIF {

  /**
   * Default constructor for the {@code ScenarioManager} class.
   */
  public ScenarioManager() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setHeaderTerm(Scenario avallaScenario, String asmName, int scenarioIndex) {
    AvallaHeaderTerm avallaHeaderTerm = new AvallaHeaderTerm();
    avallaHeaderTerm.setScenarioName(retrieveAsmName(asmName) + "_scenario" + scenarioIndex);
    avallaScenario.add(avallaHeaderTerm);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setLoadTerm(Scenario avallaScenario, String asmName) {
    AvallaLoadTerm avallaLoadTerm = new AvallaLoadTerm();
    avallaLoadTerm.setLoad(retrieveAsmName(asmName));
    avallaScenario.add(avallaLoadTerm);
  }

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
   */
  @Override
  public void setStepTerm(Scenario avallaScenario) {
    AvallaStepTerm avallaStepTerm = new AvallaStepTerm();
    avallaScenario.add(avallaStepTerm);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setCheckTerm(Scenario avallaScenario, JavaAssertionTerm javaAssertionTerm) {
    AvallaCheckTerm avallaCheckTerm = new AvallaCheckTerm();
    avallaCheckTerm.setLeftTerm(retrieveActual(javaAssertionTerm.getActual()));
    avallaCheckTerm.setRightTerm(retrieveExpected(javaAssertionTerm.getExpected()));
    avallaScenario.add(avallaCheckTerm);
  }

  /**
   * Retrieves the value of a {@link JavaVariableTerm}, adjusting its value if it's a non-primitive
   * type.
   *
   * @param javaVariable the variable whose value needs to be retrieved.
   * @return the processed value as a string.
   */
  private String retrieveValue(JavaVariableTerm javaVariable){
    String value = javaVariable.getValue();
    return javaVariable.isPrimitive() ?
        value :
        value.substring(value.lastIndexOf('.')+1);
  }

  /**
   * Retrieves the ASM name from the given ASM name string.
   *
   * @param asmName the ASM name string.
   * @return the processed ASM name.
   */
  private String retrieveAsmName(String asmName){
    return asmName.substring(0,asmName.lastIndexOf("_ASM"));
  }

  /**
   * Retrieves the actual value from a given string, adjusting its format if necessary.
   *
   * @param actual the actual value string.
   * @return the processed actual value.
   */
  private String retrieveActual(String actual){
    return actual.substring(actual.lastIndexOf(".")+1);
  }

  /**
   * Retrieves the expected value from a given string, adjusting its format if necessary.
   *
   * @param expected the expected value string.
   * @return the processed expected value.
   */
  private String retrieveExpected(String expected){
    return expected.substring(
        expected.lastIndexOf(".get_")+5).replaceAll("\\(\\)","");
  }

}

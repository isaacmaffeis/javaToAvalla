package com.javatoavalla.javascenario;

import java.util.List;
import com.javatoavalla.model.Scenario;
import com.javatoavalla.model.terms.AvallaCheckTerm;
import com.javatoavalla.model.terms.AvallaHeaderTerm;
import com.javatoavalla.model.terms.AvallaLoadTerm;
import com.javatoavalla.model.terms.AvallaSetTerm;
import com.javatoavalla.model.terms.AvallaStepTerm;
import com.javatoavalla.model.terms.JavaAssertionTerm;
import com.javatoavalla.model.terms.JavaVariableTerm;

/**
 * The {@code ScenarioManagerIF} interface defines the methods required to manage and modify
 * scenarios within the Avalla framework.
 * It provides a series of methods to add various types of terms
 * (header, load, set, step, and check) to a scenario.
 */
public interface ScenarioManagerIF {

  /**
   * Adds a {@link AvallaHeaderTerm} to the specified scenario.
   *
   * @param avallaScenario the scenario to which the header term is added.
   * @param asmName the name of the ASM specification.
   * @param scenarioName the name (or ID) of the scenario.
   */
  void setHeaderTerm(Scenario avallaScenario, String asmName, int scenarioName);

  /**
   * Adds a {@link AvallaLoadTerm} to the specified scenario.
   *
   * @param avallaScenario the scenario to which the load term is added.
   * @param asmName the name of the ASM specification to be loaded.
   */
  void setLoadTerm(Scenario avallaScenario, String asmName);

  /**
   * Adds one or more {@link AvallaSetTerm} objects to the specified scenario.
   *
   * @param avallaScenario the scenario to which the set terms are added.
   * @param variablesList a list of {@link JavaVariableTerm} representing the variables to set in
   *                      the scenario.
   */
  void setSetTerm(Scenario avallaScenario, List<JavaVariableTerm> variablesList);

  /**
   * Adds an {@link AvallaStepTerm} to the specified scenario.
   * This term represents a step in the execution of the ASM.
   *
   * @param avallaScenario the scenario to which the step term is added.
   */
  void setStepTerm(Scenario avallaScenario);

  /**
   * Adds an {@link AvallaCheckTerm} to the specified scenario.
   * This term is used to check the equality of two values.
   *
   * @param avallaScenario the scenario to which the check term is added.
   * @param javaAssertionTerm a {@link JavaAssertionTerm} containing the expected and actual
   *                         values to be checked.
   */
  void setCheckTerm(Scenario avallaScenario, JavaAssertionTerm javaAssertionTerm);
}

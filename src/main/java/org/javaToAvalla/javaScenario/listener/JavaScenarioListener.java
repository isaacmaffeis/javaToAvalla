package org.javaToAvalla.javaScenario.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javaToAvalla.antlr.JavaScenarioBaseListener;
import org.javaToAvalla.antlr.JavaScenarioParser.ActualContext;
import org.javaToAvalla.antlr.JavaScenarioParser.ArgumentContext;
import org.javaToAvalla.antlr.JavaScenarioParser.AsmDeclarationContext;
import org.javaToAvalla.antlr.JavaScenarioParser.AssertEqualsContext;
import org.javaToAvalla.antlr.JavaScenarioParser.ExpectedContext;
import org.javaToAvalla.antlr.JavaScenarioParser.ScenarioContext;
import org.javaToAvalla.antlr.JavaScenarioParser.StartContext;
import org.javaToAvalla.antlr.JavaScenarioParser.StepFunctionContext;
import org.javaToAvalla.antlr.JavaScenarioParser.VariableDeclarationContext;
import org.javaToAvalla.antlr.JavaScenarioParser.VariableNameContext;
import org.javaToAvalla.antlr.JavaScenarioParser.VariableTypeContext;
import org.javaToAvalla.antlr.JavaScenarioParser.VariableValueContext;
import org.javaToAvalla.javaScenario.ScenarioManagerIF;
import org.javaToAvalla.javaScenario.impl.ScenarioManager;
import org.javaToAvalla.model.terms.JavaArgumentTerm;
import org.javaToAvalla.model.Scenario;
import org.javaToAvalla.model.terms.JavaAssertionTerm;
import org.javaToAvalla.model.terms.JavaVariableTerm;

public class JavaScenarioListener extends JavaScenarioBaseListener {

  private static final Logger log = LogManager.getLogger(JavaScenarioListener.class);

  /**
   * List of arguments of the step function.
   */
  private final List<JavaArgumentTerm> stepFunctionArgsList;

  /**
   * List of scenarios parsed and processed.
   */
  private final List<Scenario> scenarioList;

  /**
   * Map of declared variables within the current scenario.
   */
  private Map<String, JavaVariableTerm> variablesList;

  /**
   * List of currently processed variables during a step function.
   */
  private List<JavaVariableTerm> currentVariablesList;

  /**
   * The Scenario Manager interface to manage and transform scenario terms.
   */
  private final ScenarioManagerIF scenarioManagerIF;

  /**
   * The current scenario index.
   */
  private int scenarioIndex;

  /**
   * The current argument index within the step function.
   */
  private int argumentIndex;

  /**
   * The current Java variable being processed.
   */
  private JavaVariableTerm currentJavaVariable;

  /**
   * The current scenario being processed.
   */
  private Scenario currenteScenario;

  /**
   * The current Java assertion being processed.
   */
  private JavaAssertionTerm currentJavaAssertionTerm;

  /**
   * Constructor for the {@code JavaScenarioListener}.
   *
   * @param stepFunctionArgsList List of {@link JavaArgumentTerm} representing arguments for the step function.
   */
  public JavaScenarioListener(List<JavaArgumentTerm> stepFunctionArgsList) {
    this.stepFunctionArgsList = stepFunctionArgsList;
    this.scenarioManagerIF = new ScenarioManager();
    this.scenarioList = new LinkedList<>();
  }

  /**
   * {@inheritDoc}
   * <p>Initializes the scenario index to zero.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void enterStart(StartContext ctx) {
    log.info("Parsing the Java Scenario...");
    log.debug("Entering start: {} .", ctx.getText());
    this.scenarioIndex = 0;
  }

  /**
   * {@inheritDoc}
   * <p>Processes the ASM declaration and adds header and load terms to the current scenario.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void enterAsmDeclaration(AsmDeclarationContext ctx) {
    log.debug("Entering start_test_scenario_asmDeclaration: {} .", ctx.getText());
    String asmName = ctx.ASMID(0).getText();
    this.scenarioManagerIF.setHeaderTerm(this.currenteScenario, asmName, this.scenarioIndex);
    this.scenarioManagerIF.setLoadTerm(this.currenteScenario, asmName);
  }

  /**
   * {@inheritDoc}
   * <p>Initializes the variable map and creates a new scenario object.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void enterScenario(ScenarioContext ctx) {
    log.debug("Entering start_test_scenario: {} .", ctx.getText());
    log.info("Found a scenario, creating a new Scenario Object.");
    this.variablesList = new HashMap<>();
    this.currenteScenario = new Scenario();
  }

  /**
   * {@inheritDoc}
   * <p>Creates a new empty {@link JavaVariableTerm} for the variable declaration.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void enterVariableDeclaration(VariableDeclarationContext ctx) {
    log.debug("Entering start_test_scenario_variableDeclaration: {} .", ctx.getText());
    this.currentJavaVariable = new JavaVariableTerm();
  }

  /**
   * {@inheritDoc}
   * <p>Sets the type of the current variable.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void enterVariableType(VariableTypeContext ctx) {
    log.debug("Entering start_test_scenario_variableDeclaration_variableType: {} .", ctx.getText());
    this.currentJavaVariable.setType(ctx.getText());
    this.currentJavaVariable.setPrimitive(false);
  }

  /**
   * {@inheritDoc}
   * <p>Sets the name of the current variable.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void enterVariableName(VariableNameContext ctx) {
    log.debug("Entering start_test_scenario_variableDeclaration_variableName: {} .", ctx.getText());
    this.currentJavaVariable.setName(ctx.getText());
  }

  /**
   * {@inheritDoc}
   * <p>Sets the value of the current variable.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void enterVariableValue(VariableValueContext ctx) {
    log.debug("Entering start_test_scenario_variableDeclaration_variableValue: {} .", ctx.getText());
    this.currentJavaVariable.setValue(ctx.getText());
  }

  /**
   * {@inheritDoc}
   * <p>Adds the current variable to the variables map.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void exitVariableDeclaration(VariableDeclarationContext ctx) {
    log.debug("Exiting start_test_scenario_variableDeclaration: {} .", ctx.getText());
    this.variablesList.put(this.currentJavaVariable.getName(),this.currentJavaVariable);
  }

  /**
   * {@inheritDoc}
   * <p>Initializes the current variables list and resets the argument index.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void enterStepFunction(StepFunctionContext ctx) {
    log.debug("Entering start_test_scenario_stepFunction: {} .", ctx.getText());
    log.info("Scenario is valid.");
    this.currentVariablesList = new ArrayList<>();
    this.argumentIndex = 0;
  }

  /**
   * {@inheritDoc}
   * <p>Processes an argument within a step function and assigns it to the current variable.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void enterArgument(ArgumentContext ctx) {
    log.debug("Entering start_test_scenario_stepFunction_argument: {} .", ctx.getText());

    JavaArgumentTerm currentJavaArgumentTerm = stepFunctionArgsList.get(argumentIndex);
    this.currentJavaVariable = new JavaVariableTerm();

    if(currentJavaArgumentTerm.isPrimitive()){
      this.currentJavaVariable.setPrimitive(currentJavaArgumentTerm.isPrimitive());
      this.currentJavaVariable.setValue(ctx.getText());
    } else {
      String key = ctx.getText();
      this.currentJavaVariable = this.variablesList.get(key);
    }
    this.currentJavaVariable.setName(currentJavaArgumentTerm.getName());
    this.currentJavaVariable.setType(currentJavaArgumentTerm.getType());
    log.debug("Created new currentJavaVariable");

    this.currentVariablesList.add(currentJavaVariable);
    log.debug("Added currentJavaVariable to the currentJavaVariable list");

    this.argumentIndex += 1;
  }

  /**
   * {@inheritDoc}
   * <p>Processes a step function, setting the necessary terms in the scenario.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void exitStepFunction(StepFunctionContext ctx) {
    log.debug("Exiting start_test_scenario_stepFunction: {} .", ctx.getText());
    this.scenarioManagerIF.setSetTerm(this.currenteScenario,this.currentVariablesList);
    this.scenarioManagerIF.setStepTerm(this.currenteScenario);
  }

  /**
   * {@inheritDoc}
   * <p>Initializes the assertion term for {@code assertEquals} checks.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void enterAssertEquals(AssertEqualsContext ctx) {
    log.debug("Entering start_test_scenario_assertEquals: {} .", ctx.getText());
    this.currentJavaAssertionTerm = new JavaAssertionTerm();
    this.currentJavaAssertionTerm.setType("AssertEquals");
  }

  /**
   * {@inheritDoc}
   * <p>Sets the actual value in the assertion term.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void enterActual(ActualContext ctx) {
    log.debug("Entering start_test_scenario_assertEquals_actual: {} .", ctx.getText());
    this.currentJavaAssertionTerm.setActual(ctx.getText());
  }

  /**
   * {@inheritDoc}
   * <p>Sets the expected value in the assertion term.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void enterExpected(ExpectedContext ctx) {
    log.debug("Entering start_test_scenario_assertEquals_expected: {} .", ctx.getText());
    this.currentJavaAssertionTerm.setExpected(ctx.getText());
  }

  /**
   * {@inheritDoc}
   * <p>Processes the assertion term for {@code assertEquals} and adds it to the scenario.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void exitAssertEquals(AssertEqualsContext ctx) {
    log.debug("Exiting start_test_scenario_assertEquals: {} . Setting AvallaCheckTerm:", ctx.getText());
    this.scenarioManagerIF.setCheckTerm(this.currenteScenario,this.currentJavaAssertionTerm);
  }

  /**
   * {@inheritDoc}
   * <p>Adds the completed scenario to the scenario list.</p>
   *
   * @param ctx the parse tree context.
   */
  @Override
  public void exitScenario(ScenarioContext ctx) {
    this.scenarioList.add(this.currenteScenario);
    log.info("Scenario processing completed. Scenario added to the list.");
  }

  /**
   * Returns the list of scenarios that have been processed by this listener.
   *
   * @return a list of {@link Scenario} objects.
   */
  public List<Scenario> getScenarioList() {
    return scenarioList;
  }
}

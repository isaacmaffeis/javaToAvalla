package org.javaToAvalla.javaScenario.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

  private final List<JavaArgumentTerm> stepFunctionArgsList;

  private final List<Scenario> scenarioList;

  private Map<String, JavaVariableTerm> variablesList;

  private List<JavaVariableTerm> currentVariablesList;

  private final ScenarioManagerIF scenarioManagerIF;

  private int scenarioIndex;

  private int argumentIndex;

  private JavaVariableTerm currentJavaVariable;

  private Scenario currenteScenario;

  private JavaAssertionTerm currentJavaAssertionTerm;


  public JavaScenarioListener(List<JavaArgumentTerm> stepFunctionArgsList) {
    this.stepFunctionArgsList = stepFunctionArgsList;
    this.scenarioManagerIF = new ScenarioManager();
    this.scenarioList = new LinkedList<>();
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   *
   * @param ctx
   */
  @Override
  public void enterStart(StartContext ctx) {
    this.scenarioIndex = 0;
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   *
   * @param ctx
   */
  @Override
  public void enterAsmDeclaration(AsmDeclarationContext ctx) {
    String asmName = ctx.ASMID(0).getText();
    this.scenarioManagerIF.setHeaderTerm(this.currenteScenario, asmName, this.scenarioIndex);
    this.scenarioManagerIF.setLoadTerm(this.currenteScenario, asmName);
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   *
   * @param ctx
   */
  @Override
  public void enterScenario(ScenarioContext ctx) {
    this.variablesList = new HashMap<>();
    this.currenteScenario = new Scenario();
  }

  /**
   * {@inheritDoc}
   *
   * <p>Create e new empty variable.</p>
   *
   * @param ctx
   */
  @Override
  public void enterVariableDeclaration(VariableDeclarationContext ctx) {
    this.currentJavaVariable = new JavaVariableTerm();
  }

  /**
   * {@inheritDoc}
   *
   * <p>Set the type of the variable.</p>
   *
   * @param ctx
   */
  @Override
  public void enterVariableType(VariableTypeContext ctx) {
    this.currentJavaVariable.setType(ctx.getText());
    this.currentJavaVariable.setPrimitive(false);
  }

  /**
   * {@inheritDoc}
   *
   * <p>Set the name of the variable.</p>
   *
   * @param ctx
   */
  @Override
  public void enterVariableName(VariableNameContext ctx) {
    this.currentJavaVariable.setName(ctx.getText());
  }

  /**
   * {@inheritDoc}
   *
   * <p>Set the value of the variable.</p>
   *
   * @param ctx
   */
  @Override
  public void enterVariableValue(VariableValueContext ctx) {
    this.currentJavaVariable.setValue(ctx.getText());
  }

  /**
   * {@inheritDoc}
   *
   * <p>Add the current variable to the current variable list.</p>
   *
   * @param ctx
   */
  @Override
  public void exitVariableDeclaration(VariableDeclarationContext ctx) {
    this.variablesList.put(this.currentJavaVariable.getName(),this.currentJavaVariable);
  }

  /**
   * {@inheritDoc}
   *
   * <p>Create a new current variables list.</p>
   *
   * @param ctx
   */
  @Override
  public void enterStepFunction(StepFunctionContext ctx) {
    this.currentVariablesList = new ArrayList<>();
    this.argumentIndex = 0;
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   *
   * @param ctx
   */
  @Override
  public void enterArgument(ArgumentContext ctx) {

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

    this.currentVariablesList.add(currentJavaVariable);

    this.argumentIndex += 1;
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   *
   * @param ctx
   */
  @Override
  public void exitStepFunction(StepFunctionContext ctx) {
    this.scenarioManagerIF.setSetTerm(this.currenteScenario,this.currentVariablesList);
    this.scenarioManagerIF.setStepTerm(this.currenteScenario);
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   *
   * @param ctx
   */
  @Override
  public void enterAssertEquals(AssertEqualsContext ctx) {
    this.currentJavaAssertionTerm = new JavaAssertionTerm();
    this.currentJavaAssertionTerm.setType("AssertEquals");
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   *
   * @param ctx
   */
  @Override
  public void enterActual(ActualContext ctx) {
    this.currentJavaAssertionTerm.setActual(ctx.getText());
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   *
   * @param ctx
   */
  @Override
  public void enterExpected(ExpectedContext ctx) {
    this.currentJavaAssertionTerm.setExpected(ctx.getText());
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   *
   * @param ctx
   */
  @Override
  public void exitAssertEquals(AssertEqualsContext ctx) {
    this.scenarioManagerIF.setCheckTerm(this.currenteScenario,this.currentJavaAssertionTerm);
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   *
   * @param ctx
   */
  @Override
  public void exitScenario(ScenarioContext ctx) {
    this.scenarioList.add(this.currenteScenario);
  }

  /**
   *
   * @return
   */
  public List<Scenario> getScenarioList() {
    return scenarioList;
  }
}

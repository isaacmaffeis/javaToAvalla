package org.javaToAvalla.javaScenario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javaToAvalla.antlr.JavaScenarioBaseListener;
import org.javaToAvalla.antlr.JavaScenarioParser.ArgumentContext;
import org.javaToAvalla.antlr.JavaScenarioParser.ScenarioContext;
import org.javaToAvalla.antlr.JavaScenarioParser.StepFunctionContext;
import org.javaToAvalla.antlr.JavaScenarioParser.VariableDeclarationContext;
import org.javaToAvalla.antlr.JavaScenarioParser.VariableNameContext;
import org.javaToAvalla.antlr.JavaScenarioParser.VariableTypeContext;
import org.javaToAvalla.antlr.JavaScenarioParser.VariableValueContext;
import org.javaToAvalla.model.Argument;
import org.javaToAvalla.model.Variable;

public class JavaScenarioListener extends JavaScenarioBaseListener {

  private final List<Argument> stepFunctionArgsList;

  private int currentIndex;

  private Argument currentArgument;

  private Map<String,Variable> variablesList;

  private List<Variable> currentVariablesList;

  private Variable currentVariable;

  public JavaScenarioListener(List<Argument> stepFunctionArgsList) {
    this.stepFunctionArgsList = stepFunctionArgsList;
  }

  public List<Argument> getStepFunctionArgsList() {
    return stepFunctionArgsList;
  }

  public int getCurrentIndex() {
    return currentIndex;
  }

  public Argument getCurrentArgument() {
    return currentArgument;
  }

  public Map<String, Variable> getVariablesList() {
    return variablesList;
  }

  public List<Variable> getCurrentVariablesList() {
    return currentVariablesList;
  }

  public Variable getCurrentVariable() {
    return currentVariable;
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
    this.currentVariable = new Variable();
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
    this.currentVariable.setType(ctx.getText());
    this.currentVariable.setPrimitive(false);
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
    this.currentVariable.setName(ctx.getText());
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
    this.currentVariable.setValue(ctx.getText());
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
    this.variablesList.put(this.currentVariable.getName(),this.currentVariable);
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
    this.currentIndex = 0;
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

    this.currentArgument = stepFunctionArgsList.get(currentIndex);
    this.currentVariable = new Variable();

    if(currentArgument.isPrimitive()){
      this.currentVariable.setPrimitive(currentArgument.isPrimitive());
      this.currentVariable.setValue(ctx.getText());
    } else {
      String key = ctx.getText();
      this.currentVariable = this.variablesList.get(key);
    }
    this.currentVariable.setName(currentArgument.getName());
    this.currentVariable.setType(currentArgument.getType());

    this.currentVariablesList.add(currentVariable);

    this.currentIndex += 1;
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
    //return this.currentVariablesList;
  }



}

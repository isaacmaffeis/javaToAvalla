package org.javaToAvalla.stepFunctionSupport;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.javaToAvalla.antlr.StepFunctionArgsBaseListener;
import org.javaToAvalla.antlr.StepFunctionArgsParser.ArgumentContext;
import org.javaToAvalla.antlr.StepFunctionArgsParser.NameContext;
import org.javaToAvalla.antlr.StepFunctionArgsParser.TypeContext;
import org.javaToAvalla.model.terms.JavaArgumentTerm;

/**
 *
 */
public class StepFunctionArgsListener extends StepFunctionArgsBaseListener {

  private final List<JavaArgumentTerm> javaArgumentTermList;

  private JavaArgumentTerm currentJavaArgumentTerm;

  public StepFunctionArgsListener() {
    this.javaArgumentTermList = new ArrayList<>();
  }

  /**
   * {@inheritDoc}
   *
   * <p>Create a new empty Argument.</p>
   *
   * @param ctx
   */
  @Override
  public void enterArgument(ArgumentContext ctx) {
    this.currentJavaArgumentTerm = new JavaArgumentTerm();
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   *
   * @param ctx
   */
  @Override
  public void exitArgument(ArgumentContext ctx) {
    this.javaArgumentTermList.add(this.currentJavaArgumentTerm);
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   *
   * @param ctx
   */
  @Override
  public void enterType(TypeContext ctx) {
    this.currentJavaArgumentTerm.setType(ctx.getText());
    TerminalNode primitiveType = ctx.PrimitiveType();
    this.currentJavaArgumentTerm.setPrimitive(primitiveType != null);
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation does nothing.</p>
   *
   * @param ctx
   */
  @Override
  public void enterName(NameContext ctx) {
    this.currentJavaArgumentTerm.setName(ctx.getText());
  }

  /**
   *
   * @return
   */
  public List<JavaArgumentTerm> getArgumentList() {
    return javaArgumentTermList;
  }

}

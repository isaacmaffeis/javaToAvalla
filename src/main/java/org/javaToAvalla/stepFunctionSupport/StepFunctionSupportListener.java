package org.javaToAvalla.stepFunctionSupport;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.javaToAvalla.antlr.StepFunctionSupportBaseListener;
import org.javaToAvalla.antlr.StepFunctionSupportParser.ArgumentContext;
import org.javaToAvalla.antlr.StepFunctionSupportParser.NameContext;
import org.javaToAvalla.antlr.StepFunctionSupportParser.TypeContext;
import org.javaToAvalla.stepFunctionSupport.model.Argument;

/**
 *
 */
public class StepFunctionSupportListener extends StepFunctionSupportBaseListener {

  private final List<Argument> argumentList;

  private Argument currentArgument;

  public StepFunctionSupportListener() {
    this.argumentList = new ArrayList<>();
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
    this.currentArgument = new Argument();
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
    this.argumentList.add(this.currentArgument);
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
    this.currentArgument.setType(ctx.getText());
    TerminalNode primitiveType = ctx.PrimitiveType();
    this.currentArgument.setPrimitive(primitiveType != null);
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
    this.currentArgument.setName(ctx.getText());
  }

  /**
   *
   * @return
   */
  public List<Argument> getArgumentList() {
    return argumentList;
  }

}

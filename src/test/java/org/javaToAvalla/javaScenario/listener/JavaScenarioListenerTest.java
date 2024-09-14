package org.javaToAvalla.javaScenario.listener;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.javaToAvalla.antlr.JavaScenarioLexer;
import org.javaToAvalla.antlr.JavaScenarioParser;
import org.javaToAvalla.model.Argument;
import org.javaToAvalla.util.JavaScenarioUtil;
import org.junit.Test;

public class JavaScenarioListenerTest {

  @Test
  public void whenStepFunctionSupportContainsArguments_thenListOfArgumentsIsReturned() {

    String javaFile = JavaScenarioUtil.getJavaFile_RegistroDiCassa();
    List<Argument> stepFunctionArgsList = JavaScenarioUtil.getArgumentList_RegistroDiCassa();

    JavaScenarioLexer javaScenarioLexer = new JavaScenarioLexer(CharStreams.fromString(javaFile));
    CommonTokenStream tokens = new CommonTokenStream(javaScenarioLexer);
    JavaScenarioParser javaScenarioParser = new JavaScenarioParser(tokens);
    ParseTreeWalker walker = new ParseTreeWalker();
    JavaScenarioListener javaScenarioWalker = new JavaScenarioListener(stepFunctionArgsList);
    walker.walk(javaScenarioWalker, javaScenarioParser.scenario());

    assertThat(javaScenarioWalker.getCurrentVariablesList().size(), is(6));

    System.out.println(javaScenarioWalker.getCurrentVariablesList().toString());

  }

}

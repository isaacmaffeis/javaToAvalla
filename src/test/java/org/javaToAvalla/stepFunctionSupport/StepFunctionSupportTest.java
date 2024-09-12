package org.javaToAvalla.stepFunctionSupport;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.javaToAvalla.antlr.StepFunctionSupportLexer;
import org.javaToAvalla.antlr.StepFunctionSupportParser;
import org.javaToAvalla.stepFunctionSupport.model.Argument;
import org.junit.Test;

public class StepFunctionSupportTest {

  @Test
  public void whenStepFunctionSupportContainsArguments_thenListOfArgumentsIsReturned(){
    String stepFunctionArguments = "RegistroDiCassa.Servizio servizioSelezionato,\n" +
    "String pizzaInserita,\n" +
    "RegistroDiCassa.AggiungiPizza sceltaDiAggiuntaPizza,\n" +
    "RegistroDiCassa.SelezioneTipoDiPizza sceltaDelTipoPizza,\n" +
    "int insertQuantita,\n" +
    "int insertPrezzo" ;
    StepFunctionSupportLexer stepFunctionSupportLexer = new StepFunctionSupportLexer(CharStreams.fromString(stepFunctionArguments));
    CommonTokenStream tokens = new CommonTokenStream( stepFunctionSupportLexer );
    StepFunctionSupportParser stepFunctionSupportParser = new StepFunctionSupportParser(tokens);
    ParseTreeWalker walker = new ParseTreeWalker();
    StepFunctionSupportListener stepFunctionSupportWalker = new StepFunctionSupportListener();
    walker.walk(stepFunctionSupportWalker, stepFunctionSupportParser.argumentList());

    assertThat(stepFunctionSupportWalker.getArgumentList().size(), is(6));

    Argument argument = stepFunctionSupportWalker.getArgumentList().get(0);
    assertThat(argument.getName(), is("servizioSelezionato"));
    assertThat(argument.getType(), is("RegistroDiCassa.Servizio"));
    assertThat(argument.isPrimitive(), is(false));

    argument = stepFunctionSupportWalker.getArgumentList().get(1);
    assertThat(argument.getName(), is("pizzaInserita"));
    assertThat(argument.getType(), is("String"));
    assertThat(argument.isPrimitive(), is(true));

    argument = stepFunctionSupportWalker.getArgumentList().get(2);
    assertThat(argument.getName(), is("sceltaDiAggiuntaPizza"));
    assertThat(argument.getType(), is("RegistroDiCassa.AggiungiPizza"));
    assertThat(argument.isPrimitive(), is(false));

    argument = stepFunctionSupportWalker.getArgumentList().get(3);
    assertThat(argument.getName(), is("sceltaDelTipoPizza"));
    assertThat(argument.getType(), is("RegistroDiCassa.SelezioneTipoDiPizza"));
    assertThat(argument.isPrimitive(), is(false));

    argument = stepFunctionSupportWalker.getArgumentList().get(4);
    assertThat(argument.getName(), is("insertQuantita"));
    assertThat(argument.getType(), is("int"));
    assertThat(argument.isPrimitive(), is(true));

    argument = stepFunctionSupportWalker.getArgumentList().get(5);
    assertThat(argument.getName(), is("insertPrezzo"));
    assertThat(argument.getType(), is("int"));
    assertThat(argument.isPrimitive(), is(true));

  }

  @Test
  public void whenReadingStepFunctionArgumentsFromFile_thenListOfArgumentsIsNotEmpty() {

    Path filePath = Paths.get("src/test/resources/parserSupport.txt");
    String content = null;

    try {
      byte[] fileBytes = Files.readAllBytes(filePath);
      content = new String(fileBytes, StandardCharsets.UTF_8);
    } catch (IOException e) {
      fail("An exception occurred while reading the file: " + e.getMessage());
    }

    assertNotNull(content);

    StepFunctionSupportLexer stepFunctionSupportLexer = new StepFunctionSupportLexer(CharStreams.fromString(content));
    CommonTokenStream tokens = new CommonTokenStream( stepFunctionSupportLexer );
    StepFunctionSupportParser stepFunctionSupportParser = new StepFunctionSupportParser(tokens);
    ParseTreeWalker walker = new ParseTreeWalker();
    StepFunctionSupportListener stepFunctionSupportWalker = new StepFunctionSupportListener();
    walker.walk(stepFunctionSupportWalker, stepFunctionSupportParser.argumentList());

    assertFalse(stepFunctionSupportWalker.getArgumentList().isEmpty());

  }

}

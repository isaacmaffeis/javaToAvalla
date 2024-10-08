package com.javatoavalla.javascenario.listener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import com.javatoavalla.antlr.JavaScenarioLexer;
import com.javatoavalla.antlr.JavaScenarioParser;
import com.javatoavalla.model.Scenario;
import com.javatoavalla.model.terms.AvallaCheckTerm;
import com.javatoavalla.model.terms.AvallaHeaderTerm;
import com.javatoavalla.model.terms.AvallaLoadTerm;
import com.javatoavalla.model.terms.AvallaSetTerm;
import com.javatoavalla.model.terms.AvallaStepTerm;
import com.javatoavalla.model.terms.AvallaTerm;
import com.javatoavalla.model.terms.JavaArgumentTerm;
import com.javatoavalla.util.JavaScenarioUtil;
import org.junit.Test;

public class JavaScenarioListenerTest {

  @Test
  public void whenAddNewScenario_ThenParseAndCreateScenarioAvalla() {

    String javaFile = JavaScenarioUtil.getJavaFile_RegistroDiCassa();
    List<JavaArgumentTerm> stepFunctionArgsList =
        JavaScenarioUtil.getArgumentList_RegistroDiCassa();

    JavaScenarioLexer javaScenarioLexer = new JavaScenarioLexer(CharStreams.fromString(javaFile));
    CommonTokenStream tokens = new CommonTokenStream(javaScenarioLexer);
    JavaScenarioParser javaScenarioParser = new JavaScenarioParser(tokens);
    ParseTreeWalker walker = new ParseTreeWalker();
    JavaScenarioListener javaScenarioWalker = new JavaScenarioListener(stepFunctionArgsList);
    walker.walk(javaScenarioWalker, javaScenarioParser.start());

    List<Scenario> scenarioList = javaScenarioWalker.getScenarioList();
    assertFalse(scenarioList.isEmpty());
    assertEquals(javaScenarioWalker.getScenarioList().size(),1);
    Scenario avallaScenario = scenarioList.get(0);
    assertTrue(avallaScenario.isValid());
    assertFalse(avallaScenario.getScenario().isEmpty());

    AvallaTerm avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaHeaderTerm);
    assertEquals(((AvallaHeaderTerm) avallaTerm).getScenarioName(),
        "RegistroDiCassav3_scenario0");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaLoadTerm);
    assertEquals(((AvallaLoadTerm) avallaTerm).getLoad(),"RegistroDiCassav3");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaCheckTerm);
    assertEquals(((AvallaCheckTerm) avallaTerm).getLeftTerm(),"statoCassa");
    assertEquals(((AvallaCheckTerm) avallaTerm).getRightTerm(),"ATTENDI_ORDINAZIONI");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaCheckTerm);
    assertEquals(((AvallaCheckTerm) avallaTerm).getLeftTerm(),"totale");
    assertEquals(((AvallaCheckTerm) avallaTerm).getRightTerm(),"0");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaSetTerm);
    assertEquals(((AvallaSetTerm) avallaTerm).getName(),"servizioSelezionato");
    assertEquals(((AvallaSetTerm) avallaTerm).getValue(),"NEWORDINE");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaSetTerm);
    assertEquals(((AvallaSetTerm) avallaTerm).getName(),"pizzaInserita");
    assertEquals(((AvallaSetTerm) avallaTerm).getValue(),"margherita");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaSetTerm);
    assertEquals(((AvallaSetTerm) avallaTerm).getName(),"sceltaDiAggiuntaPizza");
    assertEquals(((AvallaSetTerm) avallaTerm).getValue(),"SI");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaSetTerm);
    assertEquals(((AvallaSetTerm) avallaTerm).getName(),"sceltaDelTipoPizza");
    assertEquals(((AvallaSetTerm) avallaTerm).getValue(),"STANDARD");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaSetTerm);
    assertEquals(((AvallaSetTerm) avallaTerm).getName(),"insertQuantita");
    assertEquals(((AvallaSetTerm) avallaTerm).getValue(),"2");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaSetTerm);
    assertEquals(((AvallaSetTerm) avallaTerm).getName(),"insertPrezzo");
    assertEquals(((AvallaSetTerm) avallaTerm).getValue(),"2");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaStepTerm);

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaCheckTerm);
    assertEquals(((AvallaCheckTerm) avallaTerm).getLeftTerm(),
        "outMess");
    assertEquals(((AvallaCheckTerm) avallaTerm).getRightTerm(),"\"Scegli il tipo di pizza desiderata:\"");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaCheckTerm);
    assertEquals(((AvallaCheckTerm) avallaTerm).getLeftTerm(),"totale");
    assertEquals(((AvallaCheckTerm) avallaTerm).getRightTerm(),"0");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaCheckTerm);
    assertEquals(((AvallaCheckTerm) avallaTerm).getLeftTerm(),"statoCassa");
    assertEquals(((AvallaCheckTerm) avallaTerm).getRightTerm(),"SCEGLI_TIPO_DI_PIZZA");

    assertTrue(avallaScenario.getScenario().isEmpty());

  }

}

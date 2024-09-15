package org.javaToAvalla.javaScenario.listener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.javaToAvalla.antlr.JavaScenarioLexer;
import org.javaToAvalla.antlr.JavaScenarioParser;
import org.javaToAvalla.model.Scenario;
import org.javaToAvalla.model.terms.AvallaCheckTerm;
import org.javaToAvalla.model.terms.AvallaHeaderTerm;
import org.javaToAvalla.model.terms.AvallaLoadTerm;
import org.javaToAvalla.model.terms.AvallaSetTerm;
import org.javaToAvalla.model.terms.AvallaStepTerm;
import org.javaToAvalla.model.terms.AvallaTerm;
import org.javaToAvalla.model.terms.JavaArgumentTerm;
import org.javaToAvalla.util.JavaScenarioUtil;
import org.junit.Test;

public class JavaScenarioListenerTest {

  @Test
  public void whenAddNewScenario_ThenParseAndCreateScenarioAvalla() {

    String javaFile = JavaScenarioUtil.getJavaFile_RegistroDiCassa();
    List<JavaArgumentTerm> stepFunctionArgsList = JavaScenarioUtil.getArgumentList_RegistroDiCassa();

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
    assertEquals(((AvallaHeaderTerm) avallaTerm).getScenarioName(),"RegistroDiCassav3_0");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaLoadTerm);
    assertEquals(((AvallaLoadTerm) avallaTerm).getLoad(),"RegistroDiCassav3");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaCheckTerm);
    assertEquals(((AvallaCheckTerm) avallaTerm).getLeftTerm(),"ATTENDI_ORDINAZIONI");
    assertEquals(((AvallaCheckTerm) avallaTerm).getRightTerm(),"statoCassa");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaCheckTerm);
    assertEquals(((AvallaCheckTerm) avallaTerm).getLeftTerm(),"0");
    assertEquals(((AvallaCheckTerm) avallaTerm).getRightTerm(),"totale");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaSetTerm);
    assertEquals(((AvallaSetTerm) avallaTerm).getName(),"servizioSelezionato");
    assertEquals(((AvallaSetTerm) avallaTerm).getValue(),"NEWORDINE");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaSetTerm);
    assertEquals(((AvallaSetTerm) avallaTerm).getName(),"pizzaInserita");
    assertEquals(((AvallaSetTerm) avallaTerm).getValue(),"\"margherita\"");

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
    assertEquals(((AvallaCheckTerm) avallaTerm).getLeftTerm(),"\"Scegli il tipo di pizza desiderata:\"");
    assertEquals(((AvallaCheckTerm) avallaTerm).getRightTerm(),"outMess");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaCheckTerm);
    assertEquals(((AvallaCheckTerm) avallaTerm).getLeftTerm(),"0");
    assertEquals(((AvallaCheckTerm) avallaTerm).getRightTerm(),"totale");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaCheckTerm);
    assertEquals(((AvallaCheckTerm) avallaTerm).getLeftTerm(),"SCEGLI_TIPO_DI_PIZZA");
    assertEquals(((AvallaCheckTerm) avallaTerm).getRightTerm(),"statoCassa");

    assertTrue(avallaScenario.getScenario().isEmpty());

  }

}

package org.javaToAvalla.javaScenario.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.javaToAvalla.model.terms.AvallaCheckTerm;
import org.javaToAvalla.model.terms.AvallaTerm;
import org.javaToAvalla.model.terms.AvallaHeaderTerm;
import org.javaToAvalla.model.terms.AvallaLoadTerm;
import org.javaToAvalla.model.Scenario;
import org.javaToAvalla.model.terms.AvallaSetTerm;
import org.javaToAvalla.model.terms.AvallaStepTerm;
import org.javaToAvalla.model.terms.JavaVariableTerm;
import org.javaToAvalla.util.AssertionUtil;
import org.javaToAvalla.util.VariableUtil;
import org.junit.Test;

public class ScenarioManagerTest {

  @Test
  public void whenSetHeaderTerm_HeaderTermIsCreatedAndAddedToQueue(){
    ScenarioManager scenarioManager = new ScenarioManager();
    Scenario scenario = new Scenario();
    scenarioManager.setHeaderTerm(scenario,"registroDiCassav3_ASM0",0);

    AvallaTerm avallaTerm = scenario.remove();
    assertTrue(avallaTerm instanceof AvallaHeaderTerm);
    assertEquals(((AvallaHeaderTerm) avallaTerm).getScenarioName(),"registroDiCassav3_scenario0");
  }

  @Test
  public void whenSetLoadTerm_HeaderTermIsCreatedAndAddedToQueue(){
    ScenarioManager scenarioManager = new ScenarioManager();
    Scenario scenario = new Scenario();
    scenarioManager.setLoadTerm(scenario,"registroDiCassav3_ASM0");

    AvallaTerm avallaTerm = scenario.remove();
    assertTrue(avallaTerm instanceof AvallaLoadTerm);
    assertEquals(((AvallaLoadTerm) avallaTerm).getLoad(),"registroDiCassav3");
  }

  @Test
  public void whenSetSetTerm_SetTermIsCreatedAndAddedToQueue(){
    List<JavaVariableTerm> javaVariableList = VariableUtil.getVariableList();
    ScenarioManager scenarioManager = new ScenarioManager();
    Scenario avallaScenario = new Scenario();
    scenarioManager.setSetTerm(avallaScenario, javaVariableList);

    assertFalse(avallaScenario.getScenario().isEmpty());

    AvallaTerm avallaTerm = avallaScenario.remove();
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
  }

  @Test
  public void whenSetStepTerm_SetTermIsCreatedAndAddedToQueue(){
    ScenarioManager scenarioManager = new ScenarioManager();
    Scenario avallaScenario = new Scenario();
    scenarioManager.setStepTerm(avallaScenario);

    AvallaTerm avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaStepTerm);
  }

  @Test
  public void whenSetCheckTerm_CheckTermIsCreatedAndAddedToQueue(){
    ScenarioManager scenarioManager = new ScenarioManager();
    Scenario avallaScenario = new Scenario();
    scenarioManager.setCheckTerm(avallaScenario, AssertionUtil.getAssertion0());
    scenarioManager.setCheckTerm(avallaScenario, AssertionUtil.getAssertion1());
    scenarioManager.setCheckTerm(avallaScenario, AssertionUtil.getAssertion2());

    AvallaTerm avallaTerm = avallaScenario.remove();
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

  }


}


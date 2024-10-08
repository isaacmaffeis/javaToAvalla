package com.javatoavalla.javascenario.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import com.javatoavalla.model.terms.AvallaCheckTerm;
import com.javatoavalla.model.terms.AvallaTerm;
import com.javatoavalla.model.terms.AvallaHeaderTerm;
import com.javatoavalla.model.terms.AvallaLoadTerm;
import com.javatoavalla.model.Scenario;
import com.javatoavalla.model.terms.AvallaSetTerm;
import com.javatoavalla.model.terms.AvallaStepTerm;
import com.javatoavalla.model.terms.JavaVariableTerm;
import com.javatoavalla.util.AssertionUtil;
import com.javatoavalla.util.VariableUtil;
import org.junit.Test;

public class ScenarioManagerImplTest {

  @Test
  public void whenSetHeaderTerm_HeaderTermIsCreatedAndAddedToQueue(){
    ScenarioManagerImpl scenarioManagerImpl = new ScenarioManagerImpl();
    Scenario scenario = new Scenario();
    scenarioManagerImpl.setHeaderTerm(scenario,"registroDiCassav3_ASM0",0);

    AvallaTerm avallaTerm = scenario.remove();
    assertTrue(avallaTerm instanceof AvallaHeaderTerm);
    assertEquals(((AvallaHeaderTerm) avallaTerm).getScenarioName(),"registroDiCassav3_scenario0");
  }

  @Test
  public void whenSetLoadTerm_HeaderTermIsCreatedAndAddedToQueue(){
    ScenarioManagerImpl scenarioManagerImpl = new ScenarioManagerImpl();
    Scenario scenario = new Scenario();
    scenarioManagerImpl.setLoadTerm(scenario,"registroDiCassav3_ASM0");

    AvallaTerm avallaTerm = scenario.remove();
    assertTrue(avallaTerm instanceof AvallaLoadTerm);
    assertEquals(((AvallaLoadTerm) avallaTerm).getLoad(),"registroDiCassav3");
  }

  @Test
  public void whenSetSetTerm_SetTermIsCreatedAndAddedToQueue(){
    List<JavaVariableTerm> javaVariableList = VariableUtil.getVariableList();
    ScenarioManagerImpl scenarioManagerImpl = new ScenarioManagerImpl();
    Scenario avallaScenario = new Scenario();
    scenarioManagerImpl.setSetTerm(avallaScenario, javaVariableList);

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
    ScenarioManagerImpl scenarioManagerImpl = new ScenarioManagerImpl();
    Scenario avallaScenario = new Scenario();
    scenarioManagerImpl.setStepTerm(avallaScenario);

    AvallaTerm avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaStepTerm);
  }

  @Test
  public void whenSetCheckTerm_CheckTermIsCreatedAndAddedToQueue(){
    ScenarioManagerImpl scenarioManagerImpl = new ScenarioManagerImpl();
    Scenario avallaScenario = new Scenario();
    scenarioManagerImpl.setCheckTerm(avallaScenario, AssertionUtil.getAssertion0());
    scenarioManagerImpl.setCheckTerm(avallaScenario, AssertionUtil.getAssertion1());
    scenarioManagerImpl.setCheckTerm(avallaScenario, AssertionUtil.getAssertion2());

    AvallaTerm avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaCheckTerm);
    assertEquals(((AvallaCheckTerm) avallaTerm).getRightTerm(),"\"Scegli il tipo di pizza desiderata:\"");
    assertEquals(((AvallaCheckTerm) avallaTerm).getLeftTerm(),"outMess");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaCheckTerm);
    assertEquals(((AvallaCheckTerm) avallaTerm).getRightTerm(),"0");
    assertEquals(((AvallaCheckTerm) avallaTerm).getLeftTerm(),"totale");

    avallaTerm = avallaScenario.remove();
    assertTrue(avallaTerm instanceof AvallaCheckTerm);
    assertEquals(((AvallaCheckTerm) avallaTerm).getRightTerm(),"SCEGLI_TIPO_DI_PIZZA");
    assertEquals(((AvallaCheckTerm) avallaTerm).getLeftTerm(),"statoCassa");
  }

}

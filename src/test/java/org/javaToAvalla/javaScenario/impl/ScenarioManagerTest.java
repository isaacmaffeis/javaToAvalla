package org.javaToAvalla.javaScenario.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Queue;
import org.javaToAvalla.model.AvallaTerm;
import org.javaToAvalla.model.SetTerm;
import org.javaToAvalla.model.StepTerm;
import org.javaToAvalla.model.Variable;
import org.javaToAvalla.util.VariableUtil;
import org.junit.Test;

public class ScenarioManagerTest {

  @Test
  public void whenSetSetTerm_SetTermIsCreatedAndAddedToQueue(){
    List<Variable> variableList = VariableUtil.getVariableList();
    ScenarioManager scenarioManager = new ScenarioManager();
    scenarioManager.setSetTerm(variableList);
    Queue<AvallaTerm> setTermList = scenarioManager.getAvallaScenario();

    assertFalse(setTermList.isEmpty());

    AvallaTerm avallaTerm = setTermList.remove();
    assertTrue(avallaTerm instanceof SetTerm);
    assertEquals(((SetTerm) avallaTerm).getName(),"servizioSelezionato");
    assertEquals(((SetTerm) avallaTerm).getValue(),"NEWORDINE");

    avallaTerm = setTermList.remove();
    assertTrue(avallaTerm instanceof SetTerm);
    assertEquals(((SetTerm) avallaTerm).getName(),"pizzaInserita");
    assertEquals(((SetTerm) avallaTerm).getValue(),"margherita");

    avallaTerm = setTermList.remove();
    assertTrue(avallaTerm instanceof SetTerm);
    assertEquals(((SetTerm) avallaTerm).getName(),"sceltaDiAggiuntaPizza");
    assertEquals(((SetTerm) avallaTerm).getValue(),"SI");

    avallaTerm = setTermList.remove();
    assertTrue(avallaTerm instanceof SetTerm);
    assertEquals(((SetTerm) avallaTerm).getName(),"sceltaDelTipoPizza");
    assertEquals(((SetTerm) avallaTerm).getValue(),"STANDARD");

    avallaTerm = setTermList.remove();
    assertTrue(avallaTerm instanceof SetTerm);
    assertEquals(((SetTerm) avallaTerm).getName(),"insertQuantita");
    assertEquals(((SetTerm) avallaTerm).getValue(),"2");

    avallaTerm = setTermList.remove();
    assertTrue(avallaTerm instanceof SetTerm);
    assertEquals(((SetTerm) avallaTerm).getName(),"insertPrezzo");
    assertEquals(((SetTerm) avallaTerm).getValue(),"2");
  }

  @Test
  public void whenSetStepTerm_SetTermIsCreatedAndAddedToQueue(){
    ScenarioManager scenarioManager = new ScenarioManager();
    scenarioManager.setStepTerm();
    Queue<AvallaTerm> setTermList = scenarioManager.getAvallaScenario();

    AvallaTerm avallaTerm = setTermList.remove();
    assertTrue(avallaTerm instanceof StepTerm);
  }

}


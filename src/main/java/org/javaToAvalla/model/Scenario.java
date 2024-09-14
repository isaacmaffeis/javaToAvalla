package org.javaToAvalla.model;

import java.util.LinkedList;
import java.util.Queue;

public class Scenario {

  private Queue<AvallaTerm> scenario;

  public Scenario() {
    this.scenario = new LinkedList<>();
  }

  public Queue<AvallaTerm> getScenario() {
    return scenario;
  }

}

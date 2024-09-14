package org.javaToAvalla.model;

import java.util.LinkedList;
import java.util.Queue;
import org.javaToAvalla.model.terms.AvallaTerm;

public class Scenario {

  private Queue<AvallaTerm> scenario;

  public Scenario() {
    this.scenario = new LinkedList<>();
  }

  public Queue<AvallaTerm> getScenario() {
    return scenario;
  }

  public void add(AvallaTerm avallaTerm){
      this.scenario.add(avallaTerm);
  }

  public AvallaTerm remove(){
    return this.scenario.remove();
  }

  public AvallaTerm element(){
    return this.scenario.element();
  }

}

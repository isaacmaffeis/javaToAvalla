package org.javaToAvalla.model;

import java.util.LinkedList;
import java.util.Queue;
import org.javaToAvalla.model.terms.AvallaStepTerm;
import org.javaToAvalla.model.terms.AvallaTerm;

public class Scenario {

  private Queue<AvallaTerm> scenario;

  private boolean valid;

  public Scenario() {
    this.scenario = new LinkedList<>();
    this.valid = false;
  }

  public Queue<AvallaTerm> getScenario() {
    return scenario;
  }

  public void add(AvallaTerm avallaTerm){
      if(avallaTerm instanceof AvallaStepTerm) {
        this.valid = true;
      }
      this.scenario.add(avallaTerm);
  }

  public AvallaTerm remove(){
    return this.scenario.remove();
  }

  public AvallaTerm element(){
    return this.scenario.element();
  }

  public boolean isValid() {
    return valid;
  }

}

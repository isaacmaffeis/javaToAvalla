package org.javaToAvalla.model.terms;

public class AvallaHeaderTerm extends AvallaTerm{

  private String scenarioName;

  public AvallaHeaderTerm() {
  }

  public AvallaHeaderTerm(String scenarioName) {
    this.scenarioName = scenarioName;
  }

  public String getScenarioName() {
    return scenarioName;
  }

  public void setScenarioName(String scenarioName) {
    this.scenarioName = scenarioName;
  }

}

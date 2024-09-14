package org.javaToAvalla.model.terms;

public class AvallaLoadTerm extends AvallaTerm{

  private String asmName;

  public AvallaLoadTerm() {
  }

  public AvallaLoadTerm(String load) {
    this.asmName = load;
  }

  public String getLoad() {
    return asmName;
  }

  public void setLoad(String asmName) {
    this.asmName = asmName;
  }
}

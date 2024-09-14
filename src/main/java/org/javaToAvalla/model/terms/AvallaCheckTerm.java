package org.javaToAvalla.model.terms;

public class AvallaCheckTerm extends AvallaTerm {

  private String actual;

  private String expected;

  public AvallaCheckTerm() {
  }

  public AvallaCheckTerm(String actual, String expected) {
    this.actual = actual;
    this.expected = expected;
  }

  public String getActual() {
    return actual;
  }

  public void setActual(String actual) {
    this.actual = actual;
  }

  public String getExpected() {
    return expected;
  }

  public void setExpected(String expected) {
    this.expected = expected;
  }
}

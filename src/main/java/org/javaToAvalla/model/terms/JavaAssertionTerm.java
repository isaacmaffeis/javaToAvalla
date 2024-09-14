package org.javaToAvalla.model.terms;

public class JavaAssertionTerm extends JavaTerm{

  private String actual;

  private String expected;

  public JavaAssertionTerm() {
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

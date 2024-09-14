package org.javaToAvalla.model.terms;

public abstract class JavaTerm extends Term{

  /**
   * The type of the Java Term.
   */
  private String type;

  /**
   * Sets the type of the Java Term.
   *
   * @param type the type of the Java Term.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Returns the type of the Java Term.
   *
   * @return the Java Term's type as a {@code String}.
   */
  public String getType() {
    return type;
  }

}

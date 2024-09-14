package org.javaToAvalla.model.terms;

/**
 *
 */
public class JavaVariableTerm extends JavaArgumentTerm {

  /**
   * The value of the variable.
   */
  private String value;

  /**
   * Default constructor for the {@code Variable} class.
   * Initializes an empty variable.
   */
  public JavaVariableTerm() {
  }

  /**
   * Returns the type of the variable.
   *
   * @return the variable's value as a {@code String}.
   */
  public String getValue() {
    return value;
  }

  /**
   * Sets the value of the variable.
   *
   * @param value the value of the variable.
   */
  public void setValue(String value) {
    this.value = value;
  }

}

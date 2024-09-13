package org.javaToAvalla.model;

/**
 *
 */
public class Variable extends Argument {

  /**
   * The value of the variable.
   */
  private String value;

  /**
   * Default constructor for the {@code Variable} class.
   * Initializes an empty variable.
   */
  public Variable() {
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

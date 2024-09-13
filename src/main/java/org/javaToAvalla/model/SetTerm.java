package org.javaToAvalla.model;

public class SetTerm {

  /**
   * The name of the term.
   */
  private String name;

  /**
   * The value of the Avala Term.
   */
  private String value;

  /**
   * Default constructor for the {@code AvallaTerm} class.
   * Initializes an empty Avala Term.
   */
  public SetTerm() {
  }

  /**
   * Sets the name of the term.
   *
   * @param name the name of the term.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the value of the Avala Term.
   *
   * @param value the value of the Avala Term.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Returns the name of the term.
   *
   * @return the term's name as a {@code String}.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the type of the Avala Term.
   *
   * @return the Avala Term's value as a {@code String}.
   */
  public String getValue() {
    return value;
  }

}

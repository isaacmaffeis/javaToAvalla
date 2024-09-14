package org.javaToAvalla.model;

public class SetTerm extends AvallaTerm {

  /**
   * The name of the Set term.
   */
  private String name;

  /**
   * The value of the Set Term.
   */
  private String value;

  /**
   * Default constructor for the {@code SetTerm} class.
   * Initializes an empty Avala Term.
   */
  public SetTerm() {
  }

  /**
   * All Args constructor for the {@code SetTerm} class.
   * Initialize a new setTerm with name and value.
   *
   * @param name The name of the Set term.
   * @param value The value of the Set Term.
   */
  public SetTerm(String name, String value) {
    this.name = name;
    this.value = value;
  }

  /**
   * Sets the name of the Set term.
   *
   * @param name the name of the term.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the value of the Set Term.
   *
   * @param value the value of the Set Term.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Returns the name of the Set term.
   *
   * @return the term's name as a {@code String}.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the type of the Set Term.
   *
   * @return the Set Term's value as a {@code String}.
   */
  public String getValue() {
    return value;
  }

}

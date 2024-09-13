package org.javaToAvalla.model;

public abstract class JavaTerm extends Term{

  /**
   * The name of the term.
   */
  private String name;

  /**
   * The type of the Java Term.
   */
  private String type;

  /**
   * Sets the name of the term.
   *
   * @param name the name of the term.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the type of the Java Term.
   *
   * @param type the type of the Java Term.
   */
  public void setType(String type) {
    this.type = type;
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
   * Returns the type of the Java Term.
   *
   * @return the Java Term's type as a {@code String}.
   */
  public String getType() {
    return type;
  }

}

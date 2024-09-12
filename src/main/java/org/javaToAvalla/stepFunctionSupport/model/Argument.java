package org.javaToAvalla.stepFunctionSupport.model;

/**
 * The {@code Argument} class represents a function argument with a type, name,
 * and an indicator of whether it is a primitive type or not.
 */
public class Argument {

  /**
   * The type of the argument.
   */
  private String type;

  /**
   * The name of the argument.
   */
  private String name;

  /**
   * Flag indicating whether the argument is of a primitive type:
   * <ul>
   *   <li><b>Primitive:</b> 'int' | 'double' | 'float' | 'boolean' | 'char' | 'String'</li>
   *   <li><b>ComplexType:</b> Identifier '.' Identifier ('.' Identifier)*</li>
   * </ul>
   */
  private boolean isPrimitive;

  /**
   * Default constructor for the {@code Argument} class.
   * Initializes an empty argument.
   */
  public Argument() {
  }

  /**
   * Sets the type of the argument.
   *
   * @param type the type of the argument.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Sets the name of the argument.
   *
   * @param name the name of the argument.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets whether the argument is a primitive type.
   *
   * @param primitive {@code true} if the argument is a primitive type, {@code false} otherwise.
   */
  public void setPrimitive(boolean primitive) {
    isPrimitive = primitive;
  }

  /**
   * Returns the type of the argument.
   *
   * @return the argument's type as a {@code String}.
   */
  public String getType() {
    return type;
  }

  /**
   * Returns the name of the argument.
   *
   * @return the argument's name as a {@code String}.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns whether the argument is a primitive type.
   *
   * @return {@code true} if the argument is a primitive type, {@code false} otherwise.
   */
  public boolean isPrimitive() {
    return isPrimitive;
  }

}

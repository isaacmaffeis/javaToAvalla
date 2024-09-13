package org.javaToAvalla.model;

/**
 * The {@code Argument} class represents a function argument with a type, name,
 * and an indicator of whether it is a primitive type or not.
 */
public class Argument extends JavaTerm {

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
   * Sets whether the argument is a primitive type.
   *
   * @param primitive {@code true} if the argument is a primitive type, {@code false} otherwise.
   */
  public void setPrimitive(boolean primitive) {
    isPrimitive = primitive;
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

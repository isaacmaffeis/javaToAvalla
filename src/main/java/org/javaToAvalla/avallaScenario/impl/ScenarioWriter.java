package org.javaToAvalla.avallaScenario.impl;

import org.javaToAvalla.avallaScenario.ScenarioWriterIF;
import org.javaToAvalla.model.Scenario;
import org.javaToAvalla.model.ScenarioFile;
import org.javaToAvalla.model.terms.AvallaCheckTerm;
import org.javaToAvalla.model.terms.AvallaHeaderTerm;
import org.javaToAvalla.model.terms.AvallaLoadTerm;
import org.javaToAvalla.model.terms.AvallaSetTerm;
import org.javaToAvalla.model.terms.AvallaStepTerm;
import org.javaToAvalla.model.terms.AvallaTerm;

/**
 * The {@code ScenarioWriter} class is responsible for converting a {@link Scenario}
 * object into a textual format and returning it as a {@link ScenarioFile}.
 * It processes each {@link AvallaTerm} in the {@link Scenario} to generate the appropriate text,
 * including headers, loads, sets, steps, and checks.
 */
public class ScenarioWriter implements ScenarioWriterIF {

  private final char EQ = '=';

  private final char SEMI = ';';

  private final char WS = ' ';

  private final String LET = ":=";

  private final String ASM_EXTENSION = ".asm";

  private final String SCENARIO = "scenario";

  private final String LOAD = "load";

  private final String SET = "set";

  private final String STEP = "step";

  private final String CHECK = "check";

  private StringBuilder stringBuilder;

  /**
   * Default constructor.
   */
  public ScenarioWriter() {
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * It iterates over the terms in the scenario and generates the appropriate textual
   * representation based on the type of {@link AvallaTerm}.
   * </p>
   */
  @Override
  public ScenarioFile write(Scenario scenario) {

    this.stringBuilder = new StringBuilder();
    ScenarioFile scenarioFile = new ScenarioFile();

    for(AvallaTerm avallaTerm : scenario.getScenario()){

      if(avallaTerm instanceof AvallaHeaderTerm) {
        writeHeader((AvallaHeaderTerm) avallaTerm);
        scenarioFile.setName(((AvallaHeaderTerm) avallaTerm).getScenarioName());
      } else if (avallaTerm instanceof AvallaLoadTerm) {
        writeLoad((AvallaLoadTerm) avallaTerm);
      } else if (avallaTerm instanceof AvallaSetTerm) {
        writeSet((AvallaSetTerm) avallaTerm);
      } else if (avallaTerm instanceof AvallaStepTerm) {
        writeStep();
      } else if (avallaTerm instanceof AvallaCheckTerm) {
        writeCheck((AvallaCheckTerm) avallaTerm);
      }

    }

    scenarioFile.setText(stringBuilder.toString());
    return scenarioFile;
  }

  /**
   * Writes the header term to the {@link StringBuilder} with the scenario name.
   *
   * @param headerTerm the {@link AvallaHeaderTerm} containing the scenario name.
   */
  private void writeHeader(AvallaHeaderTerm headerTerm) {
    this.stringBuilder
        .append(SCENARIO)
        .append(WS)
        .append(headerTerm.getScenarioName())
        .append(SEMI)
        .append(System.lineSeparator())
        .append(System.lineSeparator());
  }

  /**
   * Writes the load term to the {@link StringBuilder} with the name of the load file.
   *
   * @param avallaLoadTerm the {@link AvallaLoadTerm} containing the load information.
   */
  private void writeLoad(AvallaLoadTerm avallaLoadTerm){
    this.stringBuilder
        .append(LOAD)
        .append(WS)
        .append(avallaLoadTerm.getLoad())
        .append(ASM_EXTENSION)
        .append(SEMI)
        .append(System.lineSeparator())
        .append(System.lineSeparator());
  }

  /**
   * Writes the set term to the {@link StringBuilder} with the variable name and value.
   *
   * @param avallaSetTerm the {@link AvallaSetTerm} containing the name and value of the variable.
   */
  private void writeSet(AvallaSetTerm avallaSetTerm) {
    this.stringBuilder
        .append(SET)
        .append(WS)
        .append(avallaSetTerm.getName())
        .append(WS)
        .append(LET)
        .append(WS)
        .append(avallaSetTerm.getValue())
        .append(SEMI)
        .append(System.lineSeparator());
  }

  /**
   * Writes the step term to the {@link StringBuilder}.
   */
  private void writeStep() {
    this.stringBuilder
        .append(System.lineSeparator())
        .append(STEP)
        .append(SEMI)
        .append(System.lineSeparator())
        .append(System.lineSeparator());
  }

  /**
   * Writes the check term to the {@link StringBuilder} with the left and right terms for
   * comparison.
   *
   * @param avallaCheckTerm the {@link AvallaCheckTerm} containing the terms to compare.
   */
  private void writeCheck(AvallaCheckTerm avallaCheckTerm) {
    this.stringBuilder
        .append(CHECK)
        .append(WS)
        .append(avallaCheckTerm.getLeftTerm())
        .append(WS)
        .append(EQ)
        .append(WS)
        .append(avallaCheckTerm.getRightTerm())
        .append(SEMI)
        .append(System.lineSeparator());
  }


}

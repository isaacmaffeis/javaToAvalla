package org.javaToAvalla.javaScenario;

import java.util.List;
import org.javaToAvalla.model.Variable;

public interface TermManagerIF {

  void setSetTerm(List<Variable> variablesList);

  void setStepTerm();

  void setCheckTerm(Object assertEquals);

}

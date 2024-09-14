package org.javaToAvalla.javaScenario;

import java.util.List;
import org.javaToAvalla.model.Scenario;
import org.javaToAvalla.model.Variable;

public interface ScenarioManagerIF {

  void setHeader();

  void setLoad();

  void setSetTerm(Scenario avallaScenario, List<Variable> variablesList);

  void setStepTerm(Scenario avallaScenario);

  void setCheckTerm(Scenario avallaScenario, Object assertEquals);

}

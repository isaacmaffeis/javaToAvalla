package org.javaToAvalla.javaScenario;

import java.util.List;
import org.javaToAvalla.model.Scenario;
import org.javaToAvalla.model.terms.JavaAssertionTerm;
import org.javaToAvalla.model.terms.JavaVariableTerm;

public interface ScenarioManagerIF {

  void setHeaderTerm(Scenario avallaScenario, String asmName, int scenarioName);

  void setLoadTerm(Scenario avallaScenario, String asmName);

  void setSetTerm(Scenario avallaScenario, List<JavaVariableTerm> variablesList);

  void setStepTerm(Scenario avallaScenario);

  void setCheckTerm(Scenario avallaScenario, JavaAssertionTerm javaAssertionTerm);

}

package org.javaToAvalla.util;

import org.javaToAvalla.model.Scenario;
import org.javaToAvalla.model.terms.AvallaCheckTerm;
import org.javaToAvalla.model.terms.AvallaHeaderTerm;
import org.javaToAvalla.model.terms.AvallaLoadTerm;
import org.javaToAvalla.model.terms.AvallaSetTerm;
import org.javaToAvalla.model.terms.AvallaStepTerm;

public class ScenarioAvallaUtil {

  public static Scenario getScenarioAvalla(){
    Scenario scenario = new Scenario();
    scenario.add(avallaHeaderTerm());
    scenario.add(avallaLoadTerm());
    scenario.add(avallaSetTerm_1());
    scenario.add(avallaSetTerm_2());
    scenario.add(avallaSetTerm_3());
    scenario.add(avallaSetTerm_4());
    scenario.add(avallaSetTerm_5());
    scenario.add(avallaSetTerm_6());
    scenario.add(avallaStepTerm());
    scenario.add(avallaCheckTerm_1());
    scenario.add(avallaCheckTerm_2());
    scenario.add(avallaCheckTerm_3());
    return scenario;
  }

  public static AvallaHeaderTerm avallaHeaderTerm(){
    AvallaHeaderTerm avallaheaderTerm = new AvallaHeaderTerm();
    avallaheaderTerm.setScenarioName("registroDiCassav3_0");
    return  avallaheaderTerm;
  }

  public static AvallaLoadTerm avallaLoadTerm(){
    AvallaLoadTerm avallaLoadTerm = new AvallaLoadTerm();
    avallaLoadTerm.setLoad("registroDiCassav3");
    return avallaLoadTerm;
  }

  public static AvallaSetTerm avallaSetTerm_1(){
    AvallaSetTerm avallaSetTerm = new AvallaSetTerm();
    avallaSetTerm.setName("servizioSelezionato");
    avallaSetTerm.setValue("NEWORDINE");
    return avallaSetTerm;
  }

  public static AvallaSetTerm avallaSetTerm_2(){
    AvallaSetTerm avallaSetTerm = new AvallaSetTerm();
    avallaSetTerm.setName("pizzaInserita");
    avallaSetTerm.setValue("margherita");
    return avallaSetTerm;
  }

  public static AvallaSetTerm avallaSetTerm_3(){
    AvallaSetTerm avallaSetTerm = new AvallaSetTerm();
    avallaSetTerm.setName("sceltaDiAggiuntaPizza");
    avallaSetTerm.setValue("SI");
    return avallaSetTerm;
  }

  public static AvallaSetTerm avallaSetTerm_4() {
    AvallaSetTerm avallaSetTerm = new AvallaSetTerm();
    avallaSetTerm.setName("sceltaDelTipoPizza");
    avallaSetTerm.setValue("STANDARD");
    return avallaSetTerm;
  }

  public static AvallaSetTerm avallaSetTerm_5() {
    AvallaSetTerm avallaSetTerm = new AvallaSetTerm();
    avallaSetTerm.setName("insertQuantita");
    avallaSetTerm.setValue("2");
    return avallaSetTerm;
  }

  public static AvallaSetTerm avallaSetTerm_6() {
    AvallaSetTerm avallaSetTerm = new AvallaSetTerm();
    avallaSetTerm.setName("insertPrezzo");
    avallaSetTerm.setValue("2");
    return avallaSetTerm;
  }

  public static AvallaStepTerm avallaStepTerm() {
    return new AvallaStepTerm();
  }

  public static AvallaCheckTerm avallaCheckTerm_1() {
    AvallaCheckTerm avallaCheckTerm = new AvallaCheckTerm();
    avallaCheckTerm.setLeftTerm("outMess");
    avallaCheckTerm.setRightTerm("\"Scegli il tipo di pizza desiderata:\"");
    return avallaCheckTerm;
  }

  public static AvallaCheckTerm avallaCheckTerm_2() {
    AvallaCheckTerm avallaCheckTerm = new AvallaCheckTerm();
    avallaCheckTerm.setLeftTerm("totale");
    avallaCheckTerm.setRightTerm("0");
    return avallaCheckTerm;
  }

  public static AvallaCheckTerm avallaCheckTerm_3() {
    AvallaCheckTerm avallaCheckTerm = new AvallaCheckTerm();
    avallaCheckTerm.setLeftTerm("statoCassa");
    avallaCheckTerm.setRightTerm("SCEGLI_TIPO_DI_PIZZA");
    return avallaCheckTerm;
  }

  public static String getAvallaScenario(){
    return "scenario registroDiCassav3_0;" + System.lineSeparator()
        + System.lineSeparator()
        + "load registroDiCassav3.asm;" + System.lineSeparator()
        + System.lineSeparator()
        + "set servizioSelezionato := NEWORDINE;" + System.lineSeparator()
        + "set pizzaInserita := margherita;" + System.lineSeparator()
        + "set sceltaDiAggiuntaPizza := SI;" + System.lineSeparator()
        + "set sceltaDelTipoPizza := STANDARD;" + System.lineSeparator()
        + "set insertQuantita := 2;" + System.lineSeparator()
        + "set insertPrezzo := 2;" + System.lineSeparator()
        + System.lineSeparator()
        + "step;" + System.lineSeparator()
        + System.lineSeparator()
        + "check outMess = \"Scegli il tipo di pizza desiderata:\";" + System.lineSeparator()
        + "check totale = 0;" + System.lineSeparator()
        + "check statoCassa = SCEGLI_TIPO_DI_PIZZA;"
        + System.lineSeparator();
  }

}

package org.javaToAvalla.util;

import java.util.LinkedList;
import java.util.List;
import org.javaToAvalla.model.Variable;

public class VariableUtil {

  public static List<Variable> getVariableList(){
    List<Variable> variableList = new LinkedList<>();
    variableList.add(getVariable0());
    variableList.add(getVariable1());
    variableList.add(getVariable2());
    variableList.add(getVariable3());
    variableList.add(getVariable4());
    variableList.add(getVariable5());
    return variableList;
  }

  public static Variable getVariable0(){
    Variable variable = new Variable();
    variable.setName("servizioSelezionato");
    variable.setType("RegistroDiCassa.Servizio");
    variable.setValue("RegistroDiCassav3Sig.Servizio.NEWORDINE");
    variable.setPrimitive(false);
    return variable;
  }

  public static Variable getVariable1(){
    Variable variable = new Variable();
    variable.setName("pizzaInserita");
    variable.setType("String");
    variable.setValue("margherita");
    variable.setPrimitive(true);
    return variable;
  }

  public static Variable getVariable2(){
    Variable variable = new Variable();
    variable.setName("sceltaDiAggiuntaPizza");
    variable.setType("RegistroDiCassa.AggiungiPizza");
    variable.setValue("RegistroDiCassav3Sig.AggiungiPizza.SI");
    variable.setPrimitive(false);
    return variable;
  }

  public static Variable getVariable3(){
    Variable variable = new Variable();
    variable.setName("sceltaDelTipoPizza");
    variable.setType("RegistroDiCassa.SelezioneTipoDiPizza");
    variable.setValue("RegistroDiCassav3Sig.SelezioneTipoDiPizza.STANDARD");
    variable.setPrimitive(false);
    return variable;
  }

  public static Variable getVariable4(){
    Variable variable = new Variable();
    variable.setName("insertQuantita");
    variable.setType("int");
    variable.setValue("2");
    variable.setPrimitive(true);
    return variable;
  }

  public static Variable getVariable5(){
    Variable variable = new Variable();
    variable.setName("insertPrezzo");
    variable.setType("int");
    variable.setValue("2");
    variable.setPrimitive(true);
    return variable;
  }


}

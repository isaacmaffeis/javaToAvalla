package org.javaToAvalla.javaScenario;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.javaToAvalla.antlr.JavaScenarioLexer;
import org.javaToAvalla.antlr.JavaScenarioParser;
import org.javaToAvalla.model.Argument;
import org.javaToAvalla.javaScenario.JavaScenarioListener;
import org.junit.Test;

public class JavaScenarioListenerTest {

  @Test
  public void whenStepFunctionSupportContainsArguments_thenListOfArgumentsIsReturned() {


    String stepFunctionArguments =
            "package org.evoservice.RegistroDiCassa;\n"
                + "\n"
                + "import org.junit.Test;\n"
                + "import static org.junit.Assert.*;\n"
                + "\n"
                + "import org.evosuite.runtime.EvoRunner;\n"
                + "import org.evosuite.runtime.EvoRunnerParameters;\n"
                + "import org.junit.runner.RunWith;\n"
                + "\n"
                + "@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) \n"
                + "public class RegistroDiCassav3_ASM_ESTest extends RegistroDiCassav3_ASM_ESTest_scaffolding {\n"
                + "\n"
                + "  @Test(timeout = 4000)\n"
                + "  public void test0()  throws Throwable  {\n"
                + "      RegistroDiCassav3_ASM registroDiCassav3_ASM0 = new RegistroDiCassav3_ASM();\n"
                + "      assertNotNull(registroDiCassav3_ASM0);\n"
                + "      assertFalse(registroDiCassav3_ASM0.isFinalState());\n"
                + "      assertEquals(RegistroDiCassav3Sig.Stati.ATTENDI_ORDINAZIONI, registroDiCassav3_ASM0.get_statoCassa());\n"
                + "      assertNull(registroDiCassav3_ASM0.get_outMess());\n"
                + "      assertEquals(0, registroDiCassav3_ASM0.get_totale());\n"
                + "      \n"
                + "      RegistroDiCassav3Sig.Servizio registroDiCassav3Sig_Servizio0 = RegistroDiCassav3Sig.Servizio.NEWORDINE;\n"
                + "      RegistroDiCassav3Sig.AggiungiPizza registroDiCassav3Sig_AggiungiPizza0 = RegistroDiCassav3Sig.AggiungiPizza.NO;\n"
                + "      RegistroDiCassav3Sig.SelezioneTipoDiPizza registroDiCassav3Sig_SelezioneTipoDiPizza0 = RegistroDiCassav3Sig.SelezioneTipoDiPizza.OTHER;\n"
                + "      RegistroDiCassav3Sig.AggiungiPizza registroDiCassav3Sig_AggiungiPizza1 = RegistroDiCassav3Sig.AggiungiPizza.SI;\n"
                + "      RegistroDiCassav3Sig.SelezioneTipoDiPizza registroDiCassav3Sig_SelezioneTipoDiPizza1 = RegistroDiCassav3Sig.SelezioneTipoDiPizza.STANDARD;\n"
                + "      registroDiCassav3_ASM0.step(registroDiCassav3Sig_Servizio0, 2, registroDiCassav3Sig_AggiungiPizza1, registroDiCassav3Sig_SelezioneTipoDiPizza1, 2, 2);\n"
                + "      assertFalse(registroDiCassav3Sig_AggiungiPizza1.equals((Object)registroDiCassav3Sig_AggiungiPizza0));\n"
                + "      assertFalse(registroDiCassav3Sig_SelezioneTipoDiPizza1.equals((Object)registroDiCassav3Sig_SelezioneTipoDiPizza0));\n"
                + "      assertNotSame(registroDiCassav3Sig_AggiungiPizza1, registroDiCassav3Sig_AggiungiPizza0);\n"
                + "      assertNotSame(registroDiCassav3Sig_SelezioneTipoDiPizza1, registroDiCassav3Sig_SelezioneTipoDiPizza0);\n"
                + "      assertFalse(registroDiCassav3_ASM0.isFinalState());\n"
                + "      assertEquals(\"Scegli il tipo di pizza desiderata:\", registroDiCassav3_ASM0.get_outMess());\n"
                + "      assertEquals(0, registroDiCassav3_ASM0.get_totale());\n"
                + "      assertEquals(RegistroDiCassav3Sig.Stati.SCEGLI_TIPO_DI_PIZZA, registroDiCassav3_ASM0.get_statoCassa());\n"
                + "     \n"
                + "        }\n"
                + "      }";

    List<Argument> stepFunctionArgsList = getArgumentList();

    JavaScenarioLexer javaScenarioLexer = new JavaScenarioLexer(
        CharStreams.fromString(stepFunctionArguments));
    CommonTokenStream tokens = new CommonTokenStream(javaScenarioLexer);
    JavaScenarioParser javaScenarioParser = new JavaScenarioParser(tokens);
    ParseTreeWalker walker = new ParseTreeWalker();
    JavaScenarioListener javaScenarioWalker = new JavaScenarioListener(stepFunctionArgsList);
    walker.walk(javaScenarioWalker, javaScenarioParser.scenario());

    assertThat(javaScenarioWalker.getCurrentVariablesList().size(), is(6));

    System.out.println(javaScenarioWalker.getCurrentVariablesList());


  }

  private static List<Argument> getArgumentList() {
    List<Argument> stepFunctionArgsList = new ArrayList<>();

    Argument argument0 = new Argument();
    argument0.setName("servizioSelezionato");
    argument0.setType("RegistroDiCassa.Servizio");
    argument0.setPrimitive(false);
    stepFunctionArgsList.add(argument0);

    Argument argument1 = new Argument();
    argument1.setName("pizzaInserita");
    argument1.setType("String");
    argument1.setPrimitive(true);
    stepFunctionArgsList.add(argument1);

    Argument argument2 = new Argument();
    argument2.setName("sceltaDiAggiuntaPizza");
    argument2.setType("RegistroDiCassa.AggiungiPizza");
    argument2.setPrimitive(false);
    stepFunctionArgsList.add(argument2);

    Argument argument3 = new Argument();
    argument3.setName("sceltaDelTipoPizza");
    argument3.setType("RegistroDiCassa.SelezioneTipoDiPizza");
    argument3.setPrimitive(false);
    stepFunctionArgsList.add(argument3);

    Argument argument4 = new Argument();
    argument4.setName("insertQuantita");
    argument4.setType("int");
    argument4.setPrimitive(true);
    stepFunctionArgsList.add(argument4);

    Argument argument5 = new Argument();
    argument5.setName("insertPrezzo");
    argument5.setType("int");
    argument5.setPrimitive(true);
    stepFunctionArgsList.add(argument5);
    return stepFunctionArgsList;
  }


}

package at.uibk.dps.ee.deploy.run;

import com.google.gson.JsonObject;
import at.uibk.dps.ee.core.exception.FailureException;
import at.uibk.dps.ee.guice.EeCoreInjectable;

/**
 * The {@link ImplementationRunBare} runs an implementation as a method.
 * 
 * @author Fedor Smirnov
 *
 */
public class ImplementationRunBare extends ImplementationRunAbstract {

  /**
   * Implements the application as specified by the provided strings. Returns the
   * resulting Json object.
   * 
   * @param inputString the string specifying the application input
   * @param specString the string specifying the spec graph
   * @param configString the string specifying the module configuration
   * @return the result of the application
   */
  public JsonObject implement(String inputString, String specString, String configString) {
    JsonObject input = readInputString(inputString);
    EeCoreInjectable core = buildEeCore(specString, configString);
    try {
      JsonObject result = core.enactWorkflow(input);
      return result;
    } catch (FailureException e) {
      throw new IllegalArgumentException("failure exception: " + e.getMessage());
    }
  }
}

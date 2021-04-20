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
public class ImplementationRunBare extends ImplementationRunAbstract{

  @Override
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

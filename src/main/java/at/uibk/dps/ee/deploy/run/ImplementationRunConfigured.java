package at.uibk.dps.ee.deploy.run;

import java.util.Optional;
import com.google.gson.JsonObject;
import at.uibk.dps.ee.core.exception.FailureException;
import at.uibk.dps.ee.guice.EeCoreInjectable;

/**
 * The {@link ImplementationRunConfigured} is used to implement an application
 * with provided data. The spec and the apollo configuration are provided
 * previously.
 * 
 * @author Fedor Smirnov
 *
 */
public class ImplementationRunConfigured extends ImplementationRunAbstract {

  protected Optional<EeCoreInjectable> eeCore = Optional.empty();

  /**
   * Configures the eeCore to be used for the implementation.
   * 
   * @param specString the string with the specification
   * @param configString the string with the module configuration
   */
  public void configureEeCore(final String specString, final String configString) {
    eeCore = Optional.of(buildEeCore(specString, configString));
  }

  /**
   * Implements the configured application with the given input.
   * 
   * @param inputString the given input (string in JSON format)
   * @return the output as {@link JsonObject}
   */
  public JsonObject implementInput(final String inputString) throws FailureException {
    final JsonObject input = readInputString(inputString);
    final EeCoreInjectable core =
        eeCore.orElseThrow(() -> new IllegalStateException("The core was not yet initialized."));
    return core.enactWorkflow(input);
  }

  /**
   * Returns true iff the specification and the guice modules are configured.
   * 
   * @return true iff the specification and the guice modules are configured
   */
  public boolean isConfigured() {
    return eeCore.isPresent();
  }
}

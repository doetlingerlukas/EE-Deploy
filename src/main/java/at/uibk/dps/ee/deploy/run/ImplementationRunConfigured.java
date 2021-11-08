package at.uibk.dps.ee.deploy.run;

import java.util.Optional;
import com.google.gson.JsonObject;
import at.uibk.dps.ee.deploy.spec.SpecFromString;
import at.uibk.dps.ee.guice.EeCoreInjectable;
import at.uibk.dps.sc.core.ScheduleModel;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

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
   * The standard constructor
   * 
   * @param vertx the VertX instance used by the server triggerring the execution
   */
  public ImplementationRunConfigured(final Vertx vertx) {
    super(vertx);
  }

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
  public JsonObject implementInput(final String inputString) {
    resetEnactment();
    final JsonObject input = readInputString(inputString);
    final EeCoreInjectable core =
        eeCore.orElseThrow(() -> new IllegalStateException("The core was not yet initialized."));
    final Future<JsonObject> futureResult = core.enactWorkflow(input);
    return getResult(futureResult);
  }

  /**
   * Restores the original state, removing the annotation done as part of a
   * previous enactment.
   */
  protected void resetEnactment() {
    final SpecFromString specWrapper = specOpt
        .orElseThrow(() -> new IllegalStateException("Specification wrapper not configured."));
    specWrapper.renewCurrentSpec();
    final ScheduleModel scheduleModel = scheduleOpt
        .orElseThrow(() -> new IllegalStateException("Schedule model not yet configured."));
    scheduleModel.resetSchedule();
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

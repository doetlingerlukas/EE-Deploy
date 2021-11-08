package at.uibk.dps.ee.deploy.run;

import com.google.gson.JsonObject;
import at.uibk.dps.ee.deploy.FileStringConverter;
import at.uibk.dps.ee.guice.EeCoreInjectable;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

/**
 * The {@link ImplementationRunBare} runs an implementation as a method.
 * 
 * @author Fedor Smirnov
 *
 */
public class ImplementationRunBare extends ImplementationRunAbstract {

  /**
   * Standard constructor.
   * 
   * @param vertx the VertX instance used by the triggerring server
   */
  public ImplementationRunBare(final Vertx vertx) {
    super(vertx);
  }

  /**
   * Implements the application as specified by the provided strings. Returns the
   * resulting Json object.
   * 
   * @param inputString the string specifying the application input
   * @param specString the string specifying the spec graph
   * @param configString the string specifying the module configuration
   * @return the result of the enactment
   */
  public JsonObject implement(final String inputString, final String specString,
      final String configString) {
    final JsonObject input = readInputString(inputString);
    final EeCoreInjectable core = buildEeCore(specString, configString);
    final Future<JsonObject> futureResult = core.enactWorkflow(input);
    return getResult(futureResult);
  }

  /**
   * Implements the application as specified by the provided files. Returns the
   * resulting Json object.
   * 
   * @param afclFile path to the AFCL decription of the workflow.
   * @param typeMappingsFile path to the type mapping file
   * @param inputFile path to the input file
   * @param configFile path to the configuration file
   * @return the result of the enactment
   */
  public JsonObject implement(final String afclFile, final String typeMappingsFile,
      final String inputFile, final String configFile) {
    final String specString = FileStringConverter.readSpecString(afclFile, typeMappingsFile);
    final String inputString = FileStringConverter.readInputFile(inputFile);
    final String configString = FileStringConverter.readModuleConfiguration(configFile);
    return implement(inputString, specString, configString);
  }
}

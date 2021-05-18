package at.uibk.dps.ee.deploy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import at.uibk.dps.ee.deploy.client.ApolloClient;
import at.uibk.dps.ee.deploy.server.ApolloServer;
import at.uibk.dps.ee.deploy.server.ConstantsServer;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;

/**
 * Convenience class to configure an Apollo server and then run it with
 * different inputs.
 * 
 * @author Fedor Smirnov
 *
 */
public abstract class ImplementationExperiment {

  protected final Logger logger;

  protected final Vertx vertx;
  protected final ApolloServer server;
  protected final ApolloClient client;

  protected Future<HttpResponse<Buffer>> currentFuture;

  /**
   * The constructor.
   * 
   * @param afclPath path to afcl file
   * @param typeMappingsPath pyth to mappings file
   * @param moduleConfigPath path to config file
   */
  public ImplementationExperiment(String afclPath, String typeMappingsPath,
      String moduleConfigPath) {
    ApolloServer.configureLogging();
    this.logger = LoggerFactory.getLogger(ImplementationExperiment.class);
    this.vertx = Vertx.vertx();
    this.server = new ApolloServer(vertx);
    this.client = new ApolloClient(vertx, ConstantsServer.hostString);

    server.start();
    configureServer(afclPath, typeMappingsPath, moduleConfigPath);
  }

  /**
   * Configures the server with the spec and the module configuration read from
   * the files.
   * 
   * @param afclPath the afcl file describing the WF
   * @param typeMappingsPath the typemappings file
   * @param moduleConfigPath the module configuration file
   */
  protected void configureServer(String afclPath, String typeMappingsPath,
      String moduleConfigPath) {
    final String specString = FileStringConverter.readSpecString(afclPath, typeMappingsPath);
    final String configString = FileStringConverter.readModuleConfiguration(moduleConfigPath);
    currentFuture = client.configureServer(specString, configString);
  }

  /**
   * Implements the application with the given input.
   * 
   * @param inputPath the path to the input Json file.
   */
  protected void implementWithInput(String inputPath) {
    final String inputString = FileStringConverter.readInputFile(inputPath);
    currentFuture = currentFuture.compose(response -> {
      return client.runInput(inputString);
    });
  }

  /**
   * Method to be implemented by child classes to specify details of the
   * experiment.
   */
  public final void runExperiment() {
    actualRun();
    currentFuture.onComplete(res -> {
      logger.info("Experiment finished. Closing VertX.");
      server.stop().compose(future -> {
        return vertx.close();
      });
    });
  }


  /**
   * Method which defines what to do within the actual run.
   */
  protected abstract void actualRun();
}

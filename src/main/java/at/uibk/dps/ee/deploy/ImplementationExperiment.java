package at.uibk.dps.ee.deploy;

import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import at.uibk.dps.ee.deploy.client.ApolloClient;
import at.uibk.dps.ee.deploy.server.ApolloServer;
import at.uibk.dps.ee.deploy.server.ConstantsServer;
import io.vertx.core.Vertx;

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

  /**
   * The constructor.
   * 
   * @param afclPath path to afcl file
   * @param typeMappingsPath pyth to mappings file
   * @param moduleConfigPath path to config file
   */
  public ImplementationExperiment(final String afclPath, final String typeMappingsPath,
      final String moduleConfigPath) {
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
  protected final void configureServer(final String afclPath, final String typeMappingsPath,
      final String moduleConfigPath) {
    final String specString = FileStringConverter.readSpecString(afclPath, typeMappingsPath);
    final String configString = FileStringConverter.readModuleConfiguration(moduleConfigPath);
    client.configureServer(specString, configString);
  }

  /**
   * Implements the application with the given input.
   * 
   * @param inputPath the path to the input Json file.
   */
  protected void implementWithInput(final String inputPath) {
    final String inputString = FileStringConverter.readInputFile(inputPath);
    client.runInput(inputString);
  }

  /**
   * Method to be implemented by child classes to specify details of the
   * experiment.
   */
  public final void runExperiment() {
    actualRun();
    logger.info("Experiment finished. Closing VertX.");
    final CountDownLatch latch = new CountDownLatch(1);
    server.stop().compose(future -> {
      return vertx.close().onComplete(futureClose -> {
        latch.countDown();
      });
    });
    try {
      latch.await();
      logger.info("VertX closed.");
      System.exit(0);
    } catch (InterruptedException e) {
      throw new IllegalStateException("Interrupted while closing VertX", e);
    }
  }


  /**
   * Method which defines what to do within the actual run.
   */
  protected abstract void actualRun();
}

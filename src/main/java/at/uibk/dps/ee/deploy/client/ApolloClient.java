package at.uibk.dps.ee.deploy.client;

import java.util.concurrent.CountDownLatch;

import at.uibk.dps.ee.deploy.FileStringConverter;
import at.uibk.dps.ee.deploy.server.ApolloServer;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.VertxTracerFactory;
import io.vertx.core.tracing.TracingOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import at.uibk.dps.ee.deploy.server.ConstantsServer;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;

/**
 * The {@link ApolloClient} is used as a convenient way of triggering
 * implementation runs on a local Apollo instance.
 * 
 * @author Fedor Smirnov
 */
public class ApolloClient {

  protected final WebClient client;
  protected final String host;
  protected final Logger logger = LoggerFactory.getLogger(ApolloClient.class);

  /**
   * Standard constructor.
   * 
   * @param vertx the vertX context
   * @param host the host name (depends on the used server)
   */
  public ApolloClient(final Vertx vertx, final String host) {
    this.client = WebClient.create(vertx);
    this.host = host;
  }

  /**
   * Configure the server.
   * 
   * @param specString string containing the spec xml
   * @param configString string with the config xml
   * @return
   */
  public void configureServer(final String specString, final String configString) {
    final CountDownLatch latch = new CountDownLatch(1);
    client.post(ConstantsServer.apolloPort, host, ConstantsServer.routeConfigStrings)
        .sendJsonObject(new JsonObject().put(ConstantsServer.jsonKeyConfigs, configString)
            .put(ConstantsServer.jsonKeySpec, specString))
        .onSuccess(response -> {
          logger.info("Apollo Server Configured.");
        }).onFailure(throwable -> {
          logger.error("Error when configuring server: {}", throwable.getMessage());
        }).onSuccess(success -> {
          if (success.statusCode() == ConstantsServer.statusServerError) {
            throw new IllegalStateException("Error with server configuration. Aborting.");
          }
          latch.countDown();
        }).onFailure(failure -> {
          throw new IllegalStateException("Server configuration failed.", failure);
        });
    try {
      latch.await();
    } catch (InterruptedException e) {
      throw new IllegalStateException("Interruption exception experiment config", e);
    }
  }

  /**
   * Run the implementation with the given string.
   * 
   * @param inputString the Json string with the input.
   */
  public void runInput(final String inputString) {
    final CountDownLatch latch = new CountDownLatch(1);
    client.post(ConstantsServer.apolloPort, host, ConstantsServer.routeRunInputString)
        .sendJsonObject(new JsonObject().put(ConstantsServer.jsonKeyInput, inputString))
        .onSuccess(response -> {
          final int status = response.statusCode();
          if (status == ConstantsServer.statusOk) {
            logger.info("Request STATUS {} MESSAGE {}", status, response.bodyAsString());
          } else if (status == ConstantsServer.statusServerError) {
            logger.error("Error from server: STATUS {} MESSAGE {}", status,
                response.bodyAsString());
          } else {
            logger.error("Unknown STATUS code {} with MESSAGE {}: ", status,
                response.bodyAsString());
          }
        }).onSuccess(success -> {
          latch.countDown();
        }).onFailure(failure -> {
          throw new IllegalStateException("Input run failed.", failure);
        });
    try {
      latch.await();
    } catch (InterruptedException e) {
      throw new IllegalStateException("Interruption exception experiment input run", e);
    }
  }

  /**
   * Used for running a client on the localhost
   *
   * @param args no arguments
   */
  public static void main(final String[] args) {
    final String filePathConfig = "./src/test/resources/singleAtomicConfig.xml";
    final String afclFilePath = "./src/test/resources/singleAtomic.yaml";
    final String typeMappingsPath = "./src/test/resources/singleAtomic.json";
    final String inputFilePath = "./src/test/resources/inputSingleAtomic.json";

    String specString = FileStringConverter.readSpecString(afclFilePath, typeMappingsPath);
    String configString = FileStringConverter.readModuleConfiguration(filePathConfig);
    String inputString = FileStringConverter.readInputFile(inputFilePath);

    final Vertx vertx = Vertx.vertx(new VertxOptions().setTracingOptions(
      new TracingOptions().setFactory(VertxTracerFactory.NOOP)
    ));
    final ApolloClient client = new ApolloClient(vertx, "127.0.0.1");

    client.configureServer(specString, configString);
    client.runInput(inputString);
  }
}

package at.uibk.dps.ee.deploy.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import at.uibk.dps.ee.deploy.server.ConstantsServer;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
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
  public Future<HttpResponse<Buffer>> configureServer(String specString, String configString) {
    return client.post(ConstantsServer.apolloPort, host, ConstantsServer.routeConfigStrings)
        .sendJsonObject(new JsonObject().put(ConstantsServer.jsonKeyConfigs, configString)
            .put(ConstantsServer.jsonKeySpec, specString))
        .onSuccess(response -> {
          logger.info("Apollo Server Configured.");
        }).onFailure(throwable -> {
          logger.error("Error when configuring server: {}", throwable.getMessage());
        });
  }

  /**
   * Run the implementation with the given string.
   * 
   * @param inputString the Json string with the input.
   */
  public Future<HttpResponse<Buffer>> runInput(String inputString) {
    return client.post(ConstantsServer.apolloPort, host, ConstantsServer.routeRunInputString)
        .sendJsonObject(new JsonObject().put(ConstantsServer.jsonKeyInput, inputString))
        .onSuccess(response -> {
          int status = response.statusCode();
          if (status == 200) {
            logger.info("Request STATUS {} MESSAGE {}", status, response.bodyAsString());
          } else if (status == 500) {
            logger.error("Error from server: STATUS {} MESSAGE {}", status,
                response.bodyAsString());
          } else {
            logger.error("Unknown STATUS code {} with MESSAGE {}: ", status,
                response.bodyAsString());
          }
        }).onFailure(throwable -> {
          logger.error("Error when running with input: {}", throwable.getMessage());
        });
  }
}

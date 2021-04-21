package at.uibk.dps.ee.deploy.server;

import at.uibk.dps.ee.core.exception.FailureException;
import at.uibk.dps.ee.deploy.run.ImplementationRunBare;
import at.uibk.dps.ee.deploy.run.ImplementationRunConfigured;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * Main access point which starts up the server used for the enactment on a
 * request basis.
 * 
 * @author Fedor Smirnov
 */
public class ApolloServer {

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();
    Router router = Router.router(vertx);
    HttpServer server = vertx.createHttpServer();

    // route for the bare enactment
    Route bareRoute = router.route(ConstantsServer.routeRunBare).method(HttpMethod.POST)
        .handler(BodyHandler.create());
    bareRoute.blockingHandler(ctx -> {
      HttpServerResponse response = ctx.response();
      JsonObject json = ctx.getBodyAsJson();
      String inputString = json.getString(ConstantsServer.jsonKeyInput);
      String specString = json.getString(ConstantsServer.jsonKeySpec);
      String configString = json.getString(ConstantsServer.jsonKeyConfigs);
      String apolloResponse = enactConfig(inputString, specString, configString);
      response.end(apolloResponse);
    });

    // routes for the configured enactment
    ImplementationRunConfigured configuredRun = new ImplementationRunConfigured();

    // config route
    Route configRoute = router.route(ConstantsServer.routeRunConfigConfig).method(HttpMethod.POST)
        .handler(BodyHandler.create());
    configRoute.blockingHandler(ctx -> {
      HttpServerResponse response = ctx.response();
      JsonObject json = ctx.getBodyAsJson();
      String specString = json.getString(ConstantsServer.jsonKeySpec);
      String configString = json.getString(ConstantsServer.jsonKeyConfigs);
      configuredRun.configureEeCore(specString, configString);
      response.end("Apollo configured");
    });

    // config run route
    Route runRoute = router.route(ConstantsServer.routeRunConfigRun).method(HttpMethod.POST)
        .handler(BodyHandler.create());
    runRoute.blockingHandler(ctx -> {
      HttpServerResponse response = ctx.response();
      JsonObject json = ctx.getBodyAsJson();
      String inputString = json.getString(ConstantsServer.jsonKeyInput);

      try {
        response.end(configuredRun.implementInput(inputString).toString());
      } catch (FailureException failExc) {
        response.setStatusCode(500).end(failExc.getMessage());
      }
    });
    server.requestHandler(router).listen(ConstantsServer.apolloPort);
  }

  protected static String enactConfig(String inputString, String specString, String configString) {
    ImplementationRunBare implRun = new ImplementationRunBare();
    return implRun.implement(inputString, specString, configString).toString();
  }

}

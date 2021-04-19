package at.uibk.dps.ee.deploy.server;

import at.uibk.dps.ee.deploy.ImplementationRunConfigurable;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
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
    HttpServerOptions options = new HttpServerOptions().setLogActivity(true);
    HttpServer server = vertx.createHttpServer(options);

    Route fullConfigRoute = router.route(ConstantsServer.routeRunFullConfig).method(HttpMethod.POST)
        .handler(BodyHandler.create());

    fullConfigRoute.blockingHandler(ctx -> {

      HttpServerResponse response = ctx.response();

      JsonObject json = ctx.getBodyAsJson();

      String inputString = json.getString(ConstantsServer.jsonKeyInput);
      String specString = json.getString(ConstantsServer.jsonKeySpec);
      String configString = json.getString(ConstantsServer.jsonKeyConfigs);
      
      String apolloResponse = enactConfig(inputString, specString, configString);
      
      response.end(apolloResponse);
    });

    server.requestHandler(router).listen(ConstantsServer.apolloPort);


  }
  
  protected static String enactConfig(String inputString, String specString, String configString) {
    ImplementationRunConfigurable implRun = new ImplementationRunConfigurable();
    return implRun.implement(inputString, specString, configString).toString();
  }
}

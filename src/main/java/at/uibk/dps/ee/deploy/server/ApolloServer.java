package at.uibk.dps.ee.deploy.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import at.uibk.dps.ee.deploy.run.ImplementationRunConfigured;
import at.uibk.dps.ee.deploy.server.routes.RequestHandlerBareStrings;
import at.uibk.dps.ee.deploy.server.routes.RequestHandlerConfigStrings;
import at.uibk.dps.ee.deploy.server.routes.RequestHandlerInputString;
import at.uibk.dps.ee.deploy.server.routes.RequestHandlerRoutes;
import ch.qos.logback.classic.util.ContextInitializer;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
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

    configureLogging();
    Logger logger = LoggerFactory.getLogger(ApolloServer.class);

    Vertx vertx = Vertx.vertx();
    Router router = Router.router(vertx);
    HttpServer server = vertx.createHttpServer();

    // help message route
    Route routeHelp = router.route(ConstantsServer.routeHelpRoutes).method(HttpMethod.GET);
    RequestHandlerRoutes handlerHelp = new RequestHandlerRoutes();
    routeHelp.handler(handlerHelp::handle);

    // route for the bare enactment
    Route bareRoute = router.route(ConstantsServer.routeRunBareStrings).method(HttpMethod.POST)
        .handler(BodyHandler.create());
    RequestHandlerBareStrings handlerBareStrings = new RequestHandlerBareStrings();
    bareRoute.blockingHandler(handlerBareStrings::handle);

    // routes for the configured enactment
    ImplementationRunConfigured configuredRun = new ImplementationRunConfigured();

    // config route
    Route configRoute = router.route(ConstantsServer.routeConfigStrings).method(HttpMethod.POST)
        .handler(BodyHandler.create());
    RequestHandlerConfigStrings handlerConfigStrings =
        new RequestHandlerConfigStrings(configuredRun);
    configRoute.blockingHandler(handlerConfigStrings::handle);

    // config run route
    Route runRoute = router.route(ConstantsServer.routeRunInputString).method(HttpMethod.POST)
        .handler(BodyHandler.create());
    RequestHandlerInputString handlerInputString = new RequestHandlerInputString(configuredRun);
    runRoute.blockingHandler(handlerInputString::handle);

    logger.info("Apollo server listening to port {}.", ConstantsServer.apolloPort);
    logger.info("For a list of the possible requests, direct a GET request to {}.",
        ConstantsServer.routeHelpRoutes);
    server.requestHandler(router).listen(ConstantsServer.apolloPort);
  }

  /**
   * Configures the used loggers.
   */
  protected static void configureLogging() {
    System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY,
        ConstantsServer.filePathLogbackConfig);
  }
}

package at.uibk.dps.ee.deploy.server;

import java.util.Optional;

import io.vertx.core.VertxOptions;
import io.vertx.core.spi.VertxTracerFactory;
import io.vertx.core.tracing.TracingOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import at.uibk.dps.ee.deploy.run.ImplementationRunConfigured;
import at.uibk.dps.ee.deploy.server.routes.RequestHandlerBareStrings;
import at.uibk.dps.ee.deploy.server.routes.RequestHandlerConfigStrings;
import at.uibk.dps.ee.deploy.server.routes.RequestHandlerInputString;
import at.uibk.dps.ee.deploy.server.routes.RequestHandlerRoutes;
import ch.qos.logback.classic.util.ContextInitializer;
import io.vertx.core.Future;
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

  protected final Logger logger = LoggerFactory.getLogger(ApolloServer.class);

  protected final Router router;
  protected final HttpServer server;

  protected final ImplementationRunConfigured configuredRun;
  protected Optional<String> host = Optional.empty();

  /**
   * Constructor with explicit host.
   * 
   * @param vertx the VertX entrypoint
   */
  public ApolloServer(final Vertx vertx, final String host) {
    this.router = Router.router(vertx);
    this.server = vertx.createHttpServer();
    this.configuredRun = new ImplementationRunConfigured();
    this.host = Optional.of(host);
    configureRoutes();
  }

  /**
   * Constructor without explicit host.
   * 
   * @param vertx the VertX entrypoint
   */
  public ApolloServer(final Vertx vertx) {
    this.router = Router.router(vertx);
    this.server = vertx.createHttpServer();
    this.configuredRun = new ImplementationRunConfigured();
    configureRoutes();
  }

  /**
   * Method used to start the server (probably to be changed)
   *
   */
  public void start() {
    logger.info("Apollo server listening to port {}.", ConstantsServer.apolloPort);
    logger.info("For a list of the possible requests, direct a GET request to {}.",
        ConstantsServer.routeHelpRoutes);
    if (host.isEmpty()) {
      server.requestHandler(router).listen(ConstantsServer.apolloPort);
    } else {
      server.requestHandler(router).listen(ConstantsServer.apolloPort, host.get());
    }
  }

  /**
   * Used for running a server on the localhost
   * 
   * @param args no arguments
   */
  public static void main(final String[] args) {
    configureLogging();
    final Vertx vertx = Vertx.vertx(new VertxOptions().setTracingOptions(
      new TracingOptions().setFactory(VertxTracerFactory.NOOP)
    ));
    final ApolloServer server = new ApolloServer(vertx);
    server.start();
  }

  /**
   * Configures the routes of the server.
   */
  protected final void configureRoutes() {
    // help message route
    final Route routeHelp = router.route(ConstantsServer.routeHelpRoutes).method(HttpMethod.GET);
    final RequestHandlerRoutes handlerHelp = new RequestHandlerRoutes();
    routeHelp.handler(handlerHelp::handle);

    // route for the bare enactment
    final Route bareRoute = router.route(ConstantsServer.routeRunBareStrings)
        .method(HttpMethod.POST).handler(BodyHandler.create());
    final RequestHandlerBareStrings handlerBareStrings = new RequestHandlerBareStrings();
    bareRoute.blockingHandler(handlerBareStrings::handle);

    // routes for the configured enactment
    final ImplementationRunConfigured configuredRun = new ImplementationRunConfigured();

    // config route
    final Route configRoute = router.route(ConstantsServer.routeConfigStrings)
        .method(HttpMethod.POST).handler(BodyHandler.create());
    final RequestHandlerConfigStrings handlerConfigStrings =
        new RequestHandlerConfigStrings(configuredRun);
    configRoute.blockingHandler(handlerConfigStrings::handle);

    // config run route
    final Route runRoute = router.route(ConstantsServer.routeRunInputString).method(HttpMethod.POST)
        .handler(BodyHandler.create());
    final RequestHandlerInputString handlerInputString =
        new RequestHandlerInputString(configuredRun);
    runRoute.blockingHandler(handlerInputString::handle);
  }

  /**
   * Configures the used loggers.
   */
  public static final void configureLogging() {
    System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY,
        ConstantsServer.filePathLogbackConfig);
  }

  /**
   * Asynchronously stops the server.
   *
   * @return void future, completed when the server is stopped
   */
  public Future<Void> stop() {
    return server.close();
  }
}

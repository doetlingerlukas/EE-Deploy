package at.uibk.dps.ee.deploy.server.routes;

import at.uibk.dps.ee.core.exception.FailureException;
import at.uibk.dps.ee.deploy.run.ImplementationRunConfigured;
import at.uibk.dps.ee.deploy.server.ConstantsServer;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * The request handler which runs implementations with the given input on a
 * previously configured Apollo instance.
 * 
 * @author Fedor Smirnov
 *
 */
public class RequestHandlerInputString implements Handler<RoutingContext> {

  protected final ImplementationRunConfigured configuredRun;

  /**
   * Basic constructor
   * 
   * @param configuredRun the reference to the configured implementation run
   *        instance
   */
  public RequestHandlerInputString(final ImplementationRunConfigured configuredRun) {
    this.configuredRun = configuredRun;
  }

  @Override
  public void handle(final RoutingContext ctx) {
    final HttpServerResponse response = ctx.response();
    final JsonObject json = ctx.getBodyAsJson();
    final String inputString = json.getString(ConstantsServer.jsonKeyInput);
    if (!configuredRun.isConfigured()) {
      response.setStatusCode(412).end("Implementation run not yet configured. See the help route ("
          + ConstantsServer.routeHelpRoutes + ") for configuration possibilities.");
    }
    try {
      response.end(configuredRun.implementInput(inputString).toString());
    } catch (FailureException failExc) {
      response.setStatusCode(500).end(failExc.getMessage());
    }
  }
}

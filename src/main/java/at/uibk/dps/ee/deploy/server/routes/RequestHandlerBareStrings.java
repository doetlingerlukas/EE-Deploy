package at.uibk.dps.ee.deploy.server.routes;

import at.uibk.dps.ee.deploy.run.ImplementationRunBare;
import at.uibk.dps.ee.deploy.server.ConstantsServer;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * The {@link RequestHandlerBareStrings} handles the enactment request which
 * carries with it the complete configuration in form of three strings.
 * 
 * @author Fedor Smirnov
 */
public class RequestHandlerBareStrings implements Handler<RoutingContext> {

  @Override
  public void handle(final RoutingContext ctx) {
    final HttpServerResponse response = ctx.response();
    final JsonObject json = ctx.getBodyAsJson();
    final String inputString = json.getString(ConstantsServer.jsonKeyInput);
    final String specString = json.getString(ConstantsServer.jsonKeySpec);
    final String configString = json.getString(ConstantsServer.jsonKeyConfigs);
    final ImplementationRunBare implRun = new ImplementationRunBare();
    final String apolloResponse =
        implRun.implement(inputString, specString, configString).toString();
    response.end(apolloResponse);
  }
}

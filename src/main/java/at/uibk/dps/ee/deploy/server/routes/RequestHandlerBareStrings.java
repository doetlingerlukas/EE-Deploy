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
  public void handle(RoutingContext ctx) {
    HttpServerResponse response = ctx.response();
    JsonObject json = ctx.getBodyAsJson();
    String inputString = json.getString(ConstantsServer.jsonKeyInput);
    String specString = json.getString(ConstantsServer.jsonKeySpec);
    String configString = json.getString(ConstantsServer.jsonKeyConfigs);
    ImplementationRunBare implRun = new ImplementationRunBare();
    String apolloResponse = implRun.implement(inputString, specString, configString).toString();
    response.end(apolloResponse);
  }
}

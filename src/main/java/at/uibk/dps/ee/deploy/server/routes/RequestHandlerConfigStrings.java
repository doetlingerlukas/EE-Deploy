package at.uibk.dps.ee.deploy.server.routes;

import at.uibk.dps.ee.deploy.run.ImplementationRunConfigured;
import at.uibk.dps.ee.deploy.server.ConstantsServer;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * The {@link RequestHandlerConfigStrings} handles the config request where the
 * configuration is provided in the form of strings.
 * 
 * @author Fedor Smirnov
 */
public class RequestHandlerConfigStrings implements Handler<RoutingContext> {

  protected final ImplementationRunConfigured implementationRun;

  /**
   * Constructor setting the {@link ImplementationRunConfigured} reference.
   * 
   * @param implementationRun the reference to the implementation run which is
   *        being configured.
   */
  public RequestHandlerConfigStrings(final ImplementationRunConfigured implementationRun) {
    this.implementationRun = implementationRun;
  }

  @Override
  public void handle(RoutingContext ctx) {
    HttpServerResponse response = ctx.response();
    JsonObject json = ctx.getBodyAsJson();
    String specString = json.getString(ConstantsServer.jsonKeySpec);
    String configString = json.getString(ConstantsServer.jsonKeyConfigs);
    implementationRun.configureEeCore(specString, configString);
    response.end("Implementation run configured");
  }
}

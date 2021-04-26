package at.uibk.dps.ee.deploy.server.routes;

import at.uibk.dps.ee.deploy.server.ConstantsServer;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;


/**
 * The {@link RequestHandlerRoutes} serves a get request and provides the
 * information about the available routes.
 * 
 * @author Fedor Smirnov
 *
 */
public class RequestHandlerRoutes implements Handler<RoutingContext> {

  protected final String explanationString;

  public RequestHandlerRoutes() {
    this.explanationString = generateExplanationMessage();
  }

  @Override
  public void handle(RoutingContext ctx) {
    HttpServerResponse response = ctx.response();
    response.end(explanationString);
  }

  /**
   * Generates the explanation message which is returned to requests.
   * 
   * @return the explanation message
   */
  protected String generateExplanationMessage() {
    StringBuffer buffer = new StringBuffer();
    appendRouteEntry(buffer, ConstantsServer.routeHelpRoutes, ConstantsServer.messageRequestTypeGet,
        ConstantsServer.explRouteHelpRoutes, ConstantsServer.messageParamsNo);
    appendRouteEntry(buffer, ConstantsServer.routeRunBareStrings,
        ConstantsServer.messageRequestTypePost, ConstantsServer.explRouteRunBareString,
        ConstantsServer.messageParamsRunBareStrings);

    return buffer.toString();
  }

  /**
   * Appends the explanation entry for a route to a given string buffer.
   * 
   * @param buffer the given string buffer
   * @param routeName the name of the route
   * @param requestType the request type
   * @param routeExplanation the explanation of what the route is doing
   * @param routeParams a description of the route parameters
   */
  protected void appendRouteEntry(StringBuffer buffer, String routeName, String requestType,
      String routeExplanation, String routeParams) {
    buffer.append("\n-----\n").append(routeName).append("\n\n").append(requestType).append("\n\n")
        .append(routeExplanation).append("\n\n").append("Parameters:\n").append(routeParams)
        .append("\n-----\n");
  }
}

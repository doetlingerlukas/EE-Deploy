package at.uibk.dps.ee.deploy.server;

/**
 * Constants for the functionality of the server classes.
 * 
 * @author Fedor Smirnov
 */
public final class ConstantsServer {

  // File path information
  public static final String filePathLogbackConfig = "./logging/config/logback.xml";

  // The server routes
  public static final String routeHelpRoutes = "/help/routes/";

  public static final String routeRunBareStrings = "/run/bare/strings/";
  public static final String routeRunInputString = "/run/input/string/";

  public static final String routeConfigStrings = "/config/strings/";

  // The server configs
  public static final int apolloPort = 8888;
  public static final String hostString = "localhost"; // do not yet fully understand how this works

  // The json keys
  public static final String jsonKeyInput = "input";
  public static final String jsonKeySpec = "spec";
  public static final String jsonKeyConfigs = "configs";

  // The server messages
  public static final String explRouteHelpRoutes =
      "Displays a list of the routes available for server requests.";
  public static final String explRouteRunBareString =
      "Implements the application following the configuration provided in the form of strings within the request body.";
  public static final String explRouteConfigStrings =
      "Configures Apollo by setting the specification and the guice modules.";
  public static final String explRouteRunInputString =
      "Implements the currently configured application with the provided input.";


  public static final String messageRequestTypeGet = "GET";
  public static final String messageRequestTypePost = "POST";

  public static final String messageParamsNo = "-";
  public static final String messageParamsRunBareStrings =
      "Request body: JSON object containing the strings with the implementation configuration (specification and modules) and the input.";
  public static final String messageParamsConfigStrings =
      "Request body: JSON object containing the strings with the implementation configuration (specification and modules).";
  public static final String messageParamsRunInputString =
      "Request body: JSON object containing the input for the implementation.";

  /**
   * No constructor.
   */
  private ConstantsServer() {}
}

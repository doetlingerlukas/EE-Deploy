package at.uibk.dps.ee.deploy.server;

/**
 * Constants for the functionality of the server classes.
 * 
 * @author Fedor Smirnov
 */
public final class ConstantsServer {

  /**
   * No constructor.
   */
  private ConstantsServer() {
  }
  
  // The server routes
  public static final String routeRunFullConfig = "/run/fullConfig/";
  
  // The server configs
  public static final int apolloPort = 8888;
  
  
  // The json keys
  public static final String jsonKeyInput = "input";
  public static final String jsonKeySpec = "spec";
  public static final String jsonKeyConfigs = "configs";
  
}

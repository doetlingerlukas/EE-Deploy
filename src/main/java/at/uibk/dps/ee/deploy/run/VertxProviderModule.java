package at.uibk.dps.ee.deploy.run;

import org.opt4j.core.start.Opt4JModule;
import at.uibk.dps.ee.guice.starter.VertxProvider;
import io.vertx.core.Vertx;

/**
 * The VertX provider module is used to bind the {@link VertxProvider} to an
 * instance which is created with a provided VertX context, instead of letting
 * Guice create a new VertX instance when providing the VertXProvider singleton.
 * 
 * @author Fedor Smirnov
 */
public class VertxProviderModule extends Opt4JModule {

  protected final Vertx vertxInstance;

  /**
   * The constructor to be used (this module has to be instantiated manually).
   * 
   * @param vertxInstance the VertX instance which is to be used.
   */
  public VertxProviderModule(Vertx vertxInstance) {
    this.vertxInstance = vertxInstance;
  }

  @Override
  protected void config() {
    VertxProvider providerWithFixInstance = new VertxProvider(vertxInstance);
    bind(VertxProvider.class).toInstance(providerWithFixInstance);
  }
}

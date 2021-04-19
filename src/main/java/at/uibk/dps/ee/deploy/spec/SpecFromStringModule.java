package at.uibk.dps.ee.deploy.spec;

import org.opt4j.core.start.Constant;
import at.uibk.dps.ee.guice.modules.InputModule;
import at.uibk.dps.ee.model.graph.EnactmentGraphProvider;
import at.uibk.dps.ee.model.graph.ResourceGraphProvider;
import at.uibk.dps.ee.model.graph.SpecificationProvider;

/**
 * Module for the configuration of the {@link SpecFromString} spec wrapper.
 * 
 * @author Fedor Smirnov
 *
 */
public class SpecFromStringModule extends InputModule {

  @Constant(namespace = SpecFromString.class, value = "specString")
  public String specString = "";

  @Override
  protected void config() {
    bind(SpecificationProvider.class).to(SpecFromString.class);
    bind(EnactmentGraphProvider.class).to(SpecFromString.class);
    bind(ResourceGraphProvider.class).to(SpecFromString.class);
  }

  public String getSpecString() {
    return specString;
  }

  public void setSpecString(String specString) {
    this.specString = specString;
  }
}

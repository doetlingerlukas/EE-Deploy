package at.uibk.dps.ee.deploy.run;

import java.util.Optional;
import java.util.Set;
import org.opt4j.core.config.ModuleAutoFinder;
import org.opt4j.core.config.ModuleRegister;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.ProvisionException;
import at.uibk.dps.ee.deploy.spec.SpecFromString;
import at.uibk.dps.ee.deploy.spec.SpecFromStringModule;
import at.uibk.dps.ee.guice.EeCoreInjectable;
import at.uibk.dps.ee.guice.modules.InputModule;
import at.uibk.dps.ee.guice.modules.VisualizationModule;
import at.uibk.dps.ee.io.script.ModuleLoaderString;
import net.sf.opendse.io.SpecificationReader;
import net.sf.opendse.model.Specification;

/**
 * Parent class for implementation runs. Defines utility methods which are used
 * by its children.
 * 
 * @author Fedor Smirnov
 *
 */
public abstract class ImplementationRunAbstract {

  protected final SpecificationReader reader = new SpecificationReader();
  protected final ModuleLoaderString moduleLoader =
      new ModuleLoaderString(new ModuleRegister(new ModuleAutoFinder()));
  protected Optional<SpecFromString> specOpt = Optional.empty();

  /**
   * Builds the {@link EeCoreInjectable} of apollo based on the provided strings.
   * 
   * @param specString the xml string with the spec
   * @param configString the xml string with the configured modules
   * @return the core used for the enactment
   */
  protected EeCoreInjectable buildEeCore(final String specString, final String configString) {
    final Set<Module> modules = readModuleList(configString);
    final SpecFromStringModule specModule = new SpecFromStringModule();
    specModule.setSpecString(specString);
    modules.add(specModule);
    final Injector injector = Guice.createInjector(modules);
    try {
      specOpt = Optional.of(injector.getInstance(SpecFromString.class));
      return injector.getInstance(EeCoreInjectable.class);
    } catch (ProvisionException provisionException) {
      // TODO add proper error handling at this point
      throw new IllegalArgumentException("Configuration problem", provisionException);
    }
  }

  /**
   * Reads in the configuration file and returns a list of modules.
   * 
   * @param configString the configuration file.
   * @return the list of modules.
   */
  protected Set<Module> readModuleList(final String configString) {
    final Set<Module> modules = moduleLoader.loadModulesFromString(configString);
    // remove things we don't need in script mode
    modules.removeIf(module -> module instanceof InputModule);
    modules.removeIf(module -> module instanceof VisualizationModule);
    return modules;
  }

  /**
   * Reads the json input from a string.
   * 
   * @param inputString the input string
   * @return the jsonObject used as input
   */
  protected JsonObject readInputString(final String inputString) {
    return (JsonObject) JsonParser.parseString(inputString);
  }

  /**
   * Reads in the specification from an xml string.
   * 
   * @param specString the xml string
   * @return the {@link Specification}
   */
  protected Specification readSpecification(final String specString) {
    try {
      final nu.xom.Builder parser = new nu.xom.Builder();
      final nu.xom.Document doc = parser.build(specString, null);
      final nu.xom.Element eSpec = doc.getRootElement();
      return reader.toSpecification(eSpec);
    } catch (Exception ex) {
      throw new IllegalArgumentException(ex);
    }
  }
}

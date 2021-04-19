package at.uibk.dps.ee.deploy;

import java.util.Set;
import org.opt4j.core.config.ModuleAutoFinder;
import org.opt4j.core.config.ModuleRegister;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import at.uibk.dps.ee.core.ImplementationRun;
import at.uibk.dps.ee.core.exception.FailureException;
import at.uibk.dps.ee.deploy.spec.SpecFromStringModule;
import at.uibk.dps.ee.guice.EeCoreInjectable;
import at.uibk.dps.ee.guice.modules.InputModule;
import at.uibk.dps.ee.guice.modules.VisualizationModule;
import at.uibk.dps.ee.io.script.ModuleLoaderString;
import net.sf.opendse.io.SpecificationReader;
import net.sf.opendse.model.Specification;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * The {@link ImplementationRunConfigurable} runs an implementation as a method.
 * 
 * @author Fedor Smirnov
 *
 */
public class ImplementationRunConfigurable implements ImplementationRun {

  protected final SpecificationReader reader = new SpecificationReader();
  protected final ModuleLoaderString moduleLoader =
      new ModuleLoaderString(new ModuleRegister(new ModuleAutoFinder()));

  @Override
  public JsonObject implement(String inputString, String specString, String configString) {
    JsonObject input = (JsonObject) JsonParser.parseString(inputString);
    Set<Module> modules = readModuleList(configString);
    SpecFromStringModule specModule = new SpecFromStringModule();
    specModule.setSpecString(specString);
    modules.add(specModule);
    Injector injector = Guice.createInjector(modules);
    EeCoreInjectable core = injector.getInstance(EeCoreInjectable.class);
    try {
      JsonObject result = core.enactWorkflow(input);
      return result;
    } catch (FailureException e) {
      throw new IllegalArgumentException("failure exception: " + e.getMessage());
    }
  }

  /**
   * Reads in the configuration file and returns a list of modules.
   * 
   * @param configString the configuration file.
   * @return the list of modules.
   */
  protected Set<Module> readModuleList(String configString) {
    Set<Module> modules = moduleLoader.loadModulesFromString(configString);
    // remove things we don't need in script mode
    modules.removeIf(module -> (module instanceof InputModule));
    modules.removeIf(module -> (module instanceof VisualizationModule));
    return modules;
  }

  /**
   * Reads the json input from a string.
   * 
   * @param inputString the input string
   * @return the jsonObject used as input
   */
  protected JsonObject readInputString(String inputString) {
    return (JsonObject) JsonParser.parseString(inputString);
  }


  /**
   * Reads in the specification from an xml string.
   * 
   * @param specString the xml string
   * @return the {@link Specification}
   */
  protected Specification readSpecification(String specString) {
    try {
      nu.xom.Builder parser = new nu.xom.Builder();
      nu.xom.Document doc = parser.build(specString, null);
      nu.xom.Element eSpec = doc.getRootElement();
      return reader.toSpecification(eSpec);
    } catch (Exception ex) {
      throw new IllegalArgumentException(ex);
    }
  }
}

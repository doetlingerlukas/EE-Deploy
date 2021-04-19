package at.uibk.dps.ee.deploy.spec;

import org.opt4j.core.start.Constant;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import at.uibk.dps.ee.model.graph.EnactmentGraph;
import at.uibk.dps.ee.model.graph.EnactmentSpecification;
import at.uibk.dps.ee.model.graph.ResourceGraph;
import at.uibk.dps.ee.model.graph.SpecificationProvider;
import net.sf.opendse.io.SpecificationReader;
import net.sf.opendse.model.Mappings;
import net.sf.opendse.model.Resource;
import net.sf.opendse.model.Specification;
import net.sf.opendse.model.Task;
import net.sf.opendse.optimization.SpecificationWrapper;

/**
 * A {@link SpecificationWrapper} which creates the spec from a given string.
 * 
 * @author Fedor Smirnov
 */
@Singleton
public class SpecFromString implements SpecificationProvider {

  protected final EnactmentSpecification spec;

  /**
   * The injection constructor.
   * 
   * @param specString the specification string
   */
  @Inject
  public SpecFromString(
      @Constant(namespace = SpecFromString.class, value = "specString") String specString) {
    this.spec = readSpecification(specString);
  }


  /**
   * Reads in the specification from an xml string.
   * 
   * @param specString the xml string
   * @return the {@link Specification}
   */
  protected EnactmentSpecification readSpecification(String specString) {
    try {
      nu.xom.Builder parser = new nu.xom.Builder();
      nu.xom.Document doc = parser.build(specString, null);
      nu.xom.Element eSpec = doc.getRootElement();
      SpecificationReader reader = new SpecificationReader();
      Specification spec = reader.toSpecification(eSpec);
      EnactmentGraph eGraph = new EnactmentGraph(spec.getApplication());
      ResourceGraph rGraph = new ResourceGraph(spec.getArchitecture());
      Mappings<Task, Resource> mappings = spec.getMappings();
      EnactmentSpecification result = new EnactmentSpecification(eGraph, rGraph, mappings);
      spec.getAttributeNames()
          .forEach(attrName -> result.setAttribute(attrName, spec.getAttribute(attrName)));
      return result;
    } catch (Exception ex) {
      throw new IllegalArgumentException(ex);
    }
  }

  @Override
  public EnactmentGraph getEnactmentGraph() {
    return spec.getEnactmentGraph();
  }

  @Override
  public ResourceGraph getResourceGraph() {
    return spec.getResourceGraph();
  }


  @Override
  public Mappings<Task, Resource> getMappings() {
    return spec.getMappings();
  }


  @Override
  public EnactmentSpecification getSpecification() {
    return spec;
  }
}

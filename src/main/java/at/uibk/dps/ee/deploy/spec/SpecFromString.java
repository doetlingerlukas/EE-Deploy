package at.uibk.dps.ee.deploy.spec;

import org.opt4j.core.start.Constant;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import at.uibk.dps.ee.model.graph.EnactmentGraph;
import at.uibk.dps.ee.model.graph.EnactmentSpecification;
import at.uibk.dps.ee.model.graph.MappingsConcurrent;
import at.uibk.dps.ee.model.graph.ResourceGraph;
import at.uibk.dps.ee.model.graph.SpecificationProvider;
import at.uibk.dps.ee.model.persistance.EnactmentSpecTransformer;
import at.uibk.dps.ee.model.utils.UtilsCopy;
import net.sf.opendse.io.SpecificationReader;
import net.sf.opendse.model.Specification;
import net.sf.opendse.optimization.SpecificationWrapper;

/**
 * A {@link SpecificationWrapper} which creates the spec from a given string.
 * 
 * @author Fedor Smirnov
 */
@Singleton
public class SpecFromString implements SpecificationProvider {

  protected final EnactmentSpecification originalSpec;
  protected EnactmentSpecification specCopy;

  /**
   * The injection constructor.
   * 
   * @param specString the specification string
   */
  @Inject
  public SpecFromString(
      @Constant(namespace = SpecFromString.class, value = "specString") final String specString) {
    this.originalSpec = readSpecification(specString);
    this.specCopy = UtilsCopy.deepCopySpec(originalSpec, "");
  }


  /**
   * Reads in the specification from an xml string.
   * 
   * @param specString the xml string
   * @return the {@link Specification}
   */
  protected final EnactmentSpecification readSpecification(final String specString) {
    try {
      final nu.xom.Builder parser = new nu.xom.Builder();
      final nu.xom.Document doc = parser.build(specString, null);
      final nu.xom.Element eSpec = doc.getRootElement();
      final SpecificationReader reader = new SpecificationReader();
      final Specification spec = reader.toSpecification(eSpec);
      return EnactmentSpecTransformer.toApollo(spec);
    } catch (Exception ex) {
      throw new IllegalArgumentException(ex);
    }
  }

  /**
   * Clears the changes to the current spec by recreating it based on the
   * original.
   */
  public void renewCurrentSpec() {
    UtilsCopy.restoreSpecAttributes(originalSpec, specCopy);
  }

  @Override
  public EnactmentGraph getEnactmentGraph() {
    return specCopy.getEnactmentGraph();
  }

  @Override
  public ResourceGraph getResourceGraph() {
    return specCopy.getResourceGraph();
  }


  @Override
  public MappingsConcurrent getMappings() {
    return specCopy.getMappings();
  }


  @Override
  public EnactmentSpecification getSpecification() {
    return specCopy;
  }
}

package at.uibk.dps.ee.deploy;

import static org.junit.Assert.*;
import java.util.Set;
import org.junit.Test;
import com.google.gson.JsonObject;
import com.google.inject.Module;
import at.uibk.dps.ee.deploy.resources.ReadTestStrings;
import net.sf.opendse.model.Specification;

public class ImplementationRunConfigurableTest {

  @Test
  public void testRun() {
    ImplementationRunConfigurable tested = new ImplementationRunConfigurable();
    String configString = ReadTestStrings.configString;
    String specString = ReadTestStrings.specString;
    String inputString = ReadTestStrings.inputString;
    JsonObject result = tested.implement(inputString, specString, configString);
    assertTrue(result.has("result"));
    assertEquals(16, result.get("result").getAsInt());
  }

  @Test
  public void testReadConfig() {
    ImplementationRunConfigurable tested = new ImplementationRunConfigurable();
    String testString = ReadTestStrings.configString;
    Set<Module> result = tested.readModuleList(testString);
    assertEquals(4, result.size());
  }

  @Test
  public void testReadInput() {
    ImplementationRunConfigurable tested = new ImplementationRunConfigurable();
    String testString = ReadTestStrings.inputString;
    JsonObject result = tested.readInputString(testString);

    assertEquals(3, result.get("input1").getAsInt());
  }

  @Test
  public void testReadSpec() {
    ImplementationRunConfigurable tested = new ImplementationRunConfigurable();
    String testString = ReadTestStrings.specString;
    Specification result = tested.readSpecification(testString);

    assertEquals(1, result.getArchitecture().getVertexCount());
    assertEquals(18, result.getApplication().getVertexCount());
    assertEquals(5, result.getMappings().size());
  }
}

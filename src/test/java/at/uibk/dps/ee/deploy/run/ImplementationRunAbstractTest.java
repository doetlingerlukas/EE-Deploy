package at.uibk.dps.ee.deploy.run;

import static org.junit.Assert.*;
import java.util.Set;
import org.junit.Test;
import com.google.gson.JsonObject;
import com.google.inject.Module;
import at.uibk.dps.ee.deploy.resources.ReadTestStrings;
import net.sf.opendse.model.Specification;

public class ImplementationRunAbstractTest {

  protected class ImplementationRunMock extends ImplementationRunAbstract{

    @Override
    public JsonObject implement(String inputString, String specString, String configString) {
      return new JsonObject();
    }
    
  }
  
  @Test
  public void testReadConfig() {
    ImplementationRunMock tested = new ImplementationRunMock();
    String testString = ReadTestStrings.configString;
    Set<Module> result = tested.readModuleList(testString);
    assertEquals(3, result.size());
  }

  @Test
  public void testReadInput() {
    ImplementationRunAbstract tested = new ImplementationRunMock();
    String testString = ReadTestStrings.inputString;
    JsonObject result = tested.readInputString(testString);
    assertEquals(3, result.get("input1").getAsInt());
  }

  @Test
  public void testReadSpec() {
    ImplementationRunAbstract tested = new ImplementationRunMock();
    String testString = ReadTestStrings.specString;
    Specification result = tested.readSpecification(testString);
    assertEquals(1, result.getArchitecture().getVertexCount());
    assertEquals(18, result.getApplication().getVertexCount());
    assertEquals(5, result.getMappings().size());
  }

}

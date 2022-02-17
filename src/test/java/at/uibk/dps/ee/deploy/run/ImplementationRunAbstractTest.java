package at.uibk.dps.ee.deploy.run;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.gson.JsonObject;
import com.google.inject.Module;
import at.uibk.dps.ee.deploy.resources.ReadTestStrings;
import io.vertx.core.Vertx;
import net.sf.opendse.model.Specification;

public class ImplementationRunAbstractTest {

  protected class ImplementationRunMock extends ImplementationRunAbstract {
    public ImplementationRunMock(Vertx vertx) {
      super(vertx);
    }
  }

  protected Vertx vertx;

  @Test
  public void testReadConfig() {
    ImplementationRunMock tested = new ImplementationRunMock(vertx);
    String testString = ReadTestStrings.configString;
    Set<Module> result = tested.readModuleList(testString);
    assertEquals(4, result.size());
  }

  @Test
  public void testReadInput() {
    ImplementationRunAbstract tested = new ImplementationRunMock(vertx);
    String testString = ReadTestStrings.inputString;
    JsonObject result = tested.readInputString(testString);
    assertEquals(3, result.get("input1").getAsInt());
  }

  @Test
  public void testReadSpec() {
    ImplementationRunAbstract tested = new ImplementationRunMock(vertx);
    String testString = ReadTestStrings.specString;
    Specification result = tested.readSpecification(testString);
    assertEquals(1, result.getArchitecture().getVertexCount());
    assertEquals(18, result.getApplication().getVertexCount());
    assertEquals(5, result.getMappings().size());
  }

  @BeforeEach
  void setup() {
    vertx = Vertx.vertx();
  }

  @AfterEach
  void cleanUp() {
    vertx.close();
  }
}

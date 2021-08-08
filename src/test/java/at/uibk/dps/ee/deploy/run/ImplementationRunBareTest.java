package at.uibk.dps.ee.deploy.run;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.google.gson.JsonObject;
import at.uibk.dps.ee.deploy.resources.ReadTestStrings;

public class ImplementationRunBareTest {

  @Test
  public void testRun() {
    ImplementationRunBare tested = new ImplementationRunBare();
    String configString = ReadTestStrings.configString;
    String specString = ReadTestStrings.specString;
    String inputString = ReadTestStrings.inputString;
    JsonObject result = tested.implement(inputString, specString, configString);
    assertTrue(result.has("result"));
    assertEquals(16, result.get("result").getAsInt());
  }
}

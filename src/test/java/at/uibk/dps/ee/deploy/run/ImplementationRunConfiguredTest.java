package at.uibk.dps.ee.deploy.run;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import com.google.gson.JsonObject;
import at.uibk.dps.ee.deploy.resources.ReadTestStrings;

public class ImplementationRunConfiguredTest {

  @Test
  @Timeout(value = 5, unit = TimeUnit.SECONDS)
  public void testRun() {
    ImplementationRunConfigured tested = new ImplementationRunConfigured();
    String configString = ReadTestStrings.configString;
    String specString = ReadTestStrings.specString;
    String inputString = ReadTestStrings.inputString;
    tested.configureEeCore(specString, configString);
    JsonObject result;
    result = tested.implementInput(inputString);
    assertTrue(result.has("result"));
    assertEquals(16, result.get("result").getAsInt());
  }

  @Test
  public void testNoConfig() {
    assertThrows(IllegalStateException.class, () -> {
      ImplementationRunConfigured tested = new ImplementationRunConfigured();
      String inputString = ReadTestStrings.inputString;
      tested.implementInput(inputString);
    });
  }
}

package at.uibk.dps.ee.deploy.run;

import static org.junit.Assert.*;
import org.junit.Test;
import com.google.gson.JsonObject;
import at.uibk.dps.ee.core.exception.FailureException;
import at.uibk.dps.ee.deploy.resources.ReadTestStrings;

public class ImplementationRunConfiguredTest {

  @Test
  public void testRun() {
    ImplementationRunConfigured tested = new ImplementationRunConfigured();
    String configString = ReadTestStrings.configString;
    String specString = ReadTestStrings.specString;
    String inputString = ReadTestStrings.inputString;
    tested.configureEeCore(specString, configString);
    JsonObject result;
    try {
      result = tested.implementInput(inputString);
      assertTrue(result.has("result"));
      assertEquals(16, result.get("result").getAsInt());
    } catch (FailureException e) {      
      fail();
    }
  }
  
  @Test(expected = IllegalStateException.class)
  public void testNoConfig() {
    ImplementationRunConfigured tested = new ImplementationRunConfigured();
    String inputString = ReadTestStrings.inputString;
    try {
      tested.implementInput(inputString);
    } catch (FailureException e) {
      fail();
    }
  }
}

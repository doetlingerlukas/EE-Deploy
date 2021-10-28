package at.uibk.dps.ee.deploy.run;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import com.google.gson.JsonObject;
import at.uibk.dps.ee.deploy.resources.ReadTestStrings;
import io.vertx.core.Vertx;

public class ImplementationRunConfiguredTest {

  protected Vertx vertx;

  @Test
  @Timeout(value = 5, unit = TimeUnit.SECONDS)
  public void testRun() {
    ImplementationRunConfigured tested = new ImplementationRunConfigured(vertx);
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
      ImplementationRunConfigured tested = new ImplementationRunConfigured(vertx);
      String inputString = ReadTestStrings.inputString;
      tested.implementInput(inputString);
    });
  }

  @BeforeEach
  void setup() {
    this.vertx = Vertx.vertx();
  }

  @AfterEach
  void cleanUp() {
    vertx.close();
  }
}

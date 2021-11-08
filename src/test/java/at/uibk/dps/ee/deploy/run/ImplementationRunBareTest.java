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

public class ImplementationRunBareTest {

  protected Vertx vertx;
  
  @Test
  @Timeout(value = 10, unit = TimeUnit.SECONDS)
  public void testRun() {
    ImplementationRunBare tested = new ImplementationRunBare(vertx);
    String configString = ReadTestStrings.configString;
    String specString = ReadTestStrings.specString;
    String inputString = ReadTestStrings.inputString;
    JsonObject result = tested.implement(inputString, specString, configString);
    assertTrue(result.has("result"));
    assertEquals(16, result.get("result").getAsInt());
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

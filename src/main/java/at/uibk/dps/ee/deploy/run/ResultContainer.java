package at.uibk.dps.ee.deploy.run;

import java.util.Optional;
import com.google.gson.JsonObject;

/**
 * Utility class to provide an optional result (mainly to be used as a final
 * object instance which is accessible by the completion handler).
 * 
 * @author Fedor Smirnov
 *
 */
public class ResultContainer {
  protected Optional<JsonObject> optResult = Optional.empty();

  public void setResult(JsonObject result) {
    optResult = Optional.of(result);
  }

  public JsonObject getResult() {
    return optResult.get();
  }
}

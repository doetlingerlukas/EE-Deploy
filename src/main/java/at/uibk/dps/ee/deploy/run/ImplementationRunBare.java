package at.uibk.dps.ee.deploy.run;

import java.util.concurrent.CountDownLatch;
import com.google.gson.JsonObject;
import at.uibk.dps.ee.guice.EeCoreInjectable;
import io.vertx.core.Future;

/**
 * The {@link ImplementationRunBare} runs an implementation as a method.
 * 
 * @author Fedor Smirnov
 *
 */
public class ImplementationRunBare extends ImplementationRunAbstract {

  /**
   * Implements the application as specified by the provided strings. Returns the
   * resulting Json object.
   * 
   * @param inputString the string specifying the application input
   * @param specString the string specifying the spec graph
   * @param configString the string specifying the module configuration
   * @return the result of the application
   * @throws InterruptedException
   */
  public JsonObject implement(final String inputString, final String specString,
      final String configString) {
    final JsonObject input = readInputString(inputString);
    final EeCoreInjectable core = buildEeCore(specString, configString);
    final CountDownLatch latch = new CountDownLatch(1);
    final ResultContainer resContainer = new ResultContainer();
    final Future<JsonObject> futureResult = core.enactWorkflow(input);
    futureResult.onComplete(wfRes -> {
      resContainer.setResult(wfRes.result());
      latch.countDown();
    });
    try {
      latch.await();
      return resContainer.getResult();
    } catch (InterruptedException e) {
      throw new IllegalArgumentException("Interrupted while waiting for the wf completion");
    }
  }
}

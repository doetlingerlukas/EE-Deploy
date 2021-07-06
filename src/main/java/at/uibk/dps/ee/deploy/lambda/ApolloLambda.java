package at.uibk.dps.ee.deploy.lambda;

import at.uibk.dps.ee.deploy.run.ImplementationRunBare;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.Map;

public class ApolloLambda implements RequestHandler<Map<String, String>, String> {

    @Override public String handleRequest(Map<String, String> event, Context context) {
        ImplementationRunBare implementationRunBare = new ImplementationRunBare();

        String configString = event.get("config");
        String specString = event.get("spec");
        String inputString = event.get("input");

        return implementationRunBare.implement(inputString, specString, configString).toString();
    }
}

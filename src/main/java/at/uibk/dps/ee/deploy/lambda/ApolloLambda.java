package at.uibk.dps.ee.deploy.lambda;

import at.uibk.dps.ee.deploy.run.ImplementationRunBare;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * {@link ApolloLambda} is used to run Apollo as an AWS Lambda function,
 * getting an Apollo config, spec and input.
 *
 * @author Stefan Pedratscher
 */
public class ApolloLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    /**
     * Runner to run he workflow.
     */
    private ImplementationRunBare runner;

    /**
     * Default constructor.
     */
    public ApolloLambda(){
       runner = new ImplementationRunBare();
    }

    /**
     * The main entry point of the Lambda function representing
     * the Apollo engine.
     *
     * @param input to the lambda function.
     * @param context to access data within the lambda execution environment.
     *
     * @return result of the workflow execution.
     */
    @Override public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input,
        Context context) {

        JsonObject json = new Gson().fromJson(input.getBody(), JsonObject.class);
        String configString = json.get("config").getAsString();
        String specString = json.get("spec").getAsString();
        String inputString = json.get("input").getAsString();

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
            .withStatusCode(200)
            .withBody(run(inputString, specString, configString).toString());

        return response;
    }

    /**
     * Execute the actual workflow.
     *
     * @param inputString workflow input.
     * @param specString workflow specification.
     * @param configString workflow configuration.
     *
     * @return workflow execution result.
     */
    public JsonObject run(String inputString, String specString, String configString){
        return runner.implement(inputString, specString, configString);
    }
}

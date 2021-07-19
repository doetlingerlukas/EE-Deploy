package at.uibk.dps.ee.deploy.lambda;

import at.uibk.dps.ee.deploy.resources.ReadTestStrings;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test class for the Apollo lambda function.
 */
public class ApolloLambdaTest {

    /**
     * Test the main entry of the lambda function.
     */
    @Test
    public void testLambdaFunction() {
        ApolloLambda apolloLambda = spy(ApolloLambda.class);

        JsonObject json = new JsonObject();
        json.addProperty("config", ReadTestStrings.configString);
        json.addProperty("spec", ReadTestStrings.specString);
        json.addProperty("input", ReadTestStrings.inputString);
        APIGatewayProxyRequestEvent input = new APIGatewayProxyRequestEvent().withBody(json.toString());

        Mockito.doReturn(new JsonObject()).when(apolloLambda).run(ReadTestStrings.inputString, ReadTestStrings.specString, ReadTestStrings.configString);
        APIGatewayProxyResponseEvent response = apolloLambda.handleRequest(input, null);

        assertEquals(Integer.valueOf(200), response.getStatusCode());
        assertEquals(response.getBody(), new JsonObject().toString());
    }
}

package testtrail;

/**
 * Created by eyal.neumann on 2/18/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
 *
 */
public class RunAPIIOS {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project17";
    protected Client client = null;

    @Before
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "RunAPIIOS");
    }

    @Test
    public void testRunAPIIOS(){
        client.setDevice("ios_app:navot’s iPad");
        if(client.install("com.experitest.ExperiBank", true, false)){
            // If statement
        }
        client.launch("com.experitest.ExperiBank", true, true);
        String str0 = client.runNativeAPICall("NATIVE", "xpath=//*[@text='Login']", 0, "invokeMethod:'{\"selector\":\"setText:\",\"arguments\":[\"LOGIN\"]}'");
        client.verifyElementFound("NATIVE", "text=LOGIN", 0);
    }

    @After
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future.
        client.releaseClient();
    }
}

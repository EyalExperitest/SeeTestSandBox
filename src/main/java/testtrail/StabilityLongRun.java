package testtrail;

/**
 * Created by eyal.neumann on 2/19/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class StabilityLongRun {
    private String host = "localhost";
    private int port = 8889;
    //private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project17";
    protected Client client = null;

    @BeforeMethod (alwaysRun = true)
    public void setUp(){
        client = new Client(host, port, true);
        //client.setProjectBaseDirectory(projectBaseDirectory);
        //client.setReporter("xml", "reports", "StabilityLongRun");
    }

    @Test(invocationCount = 1000,alwaysRun = true)
    public void testStabilityLongRun(){
        //client.setDevice("ios_app:navot’s iPad");
        client.setDevice("ios_app:iPad");
        client.launch("com.experitest.ExperiBank", true, false);
        String str0 = client.getVisualDump("Native");
        String str1 = client.capture("Capture");
    }

    @AfterMethod
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        //client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future.
        client.releaseClient();
    }
}

package testtrail;

/**
 * Created by eyal.neumann on 2/14/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class NonInstrumentedPropertyCheck {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project15";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "NonInstrumentedPropertyCheck");
    }

    @Test(groups = {"seetest"})
    public void testNonInstrumentedPropertyCheck(){
        client.setDevice("ios_app:navot’s iPad");
        client.launch("com.experitest.ExperiBank", true, true);
        client.verifyElementFound("NATIVE", "xpath=//*[@accessibilityLabel='Username']", 0);
        client.setProperty("ios.native.nonInstrumented", "true");
        client.verifyElementNotFound("NATIVE", "xpath=//*[@accessibilityLabel='Username']", 0);
    }

    @AfterMethod
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future.
        client.releaseClient();
    }
}

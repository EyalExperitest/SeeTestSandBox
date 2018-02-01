package issues;

/**
 * Created by eyal.neumann on 1/17/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class SA20929 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project15";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA20929");
    }

    @Test(groups = {"seetest"})
    public void testSA20929(){
        client.setDevice("adb:Sony SGP771");
        if(client.getNetworkConnection("airplane_mode")){
            // If statement
        }
        client.setNetworkConnection("airplane_mode", true);
        if(client.getNetworkConnection("airplane_mode")){
            // If statement
        }
        client.setNetworkConnection("airplane_mode", false);
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

package issues;

/**
 * Created by eyal.neumann on 1/31/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class SA21670 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project15";
    protected Client client = null;

    @BeforeMethod (alwaysRun = true)
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA21670");
    }

    @Test(invocationCount = 100,alwaysRun = true)
    public void testSA21670(){
        client.setDevice("adb:SM-G930F");
        client.openDevice();
        String str0 = client.getVisualDump("Native");
        if(client.reboot(180000)){
            // If statement
        }
        client.closeDevice();
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

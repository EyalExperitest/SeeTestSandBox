package testtrail;

/**
 * Created by Mac10 on 18/02/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
import static org.junit.Assert.*;
/**
 *
 */
public class AutomatorVersion {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "/Users/Mac10/workspace/project6";
    protected Client client = null;

    @Before
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "AutomatorVersion");
    }

    @Test
    public void testAutomatorVersion(){
        client.setDevice("ios_app:navot’s iPad");
        String automatorVersion = client.getDeviceProperty("automator.version");
        System.out.println("Version ="+automatorVersion);
        assertEquals("1811",automatorVersion);
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

package testtrail;

/**
 * Created by eyal.neumann on 3/18/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
 *
 */
public class MonitorTest {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project20";
    protected Client client = null;

    @Before
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "MonitorTest");
    }

    @Test
    public void testMonitorTest(){
        client.setDevice("ios_app:yotam.gamliels iPhone");
        client.launch("cloud:com.experitest.ExperiBank", true, true);
        client.startMonitor("com.experitest.ExperiBank");
        if(client.waitForElement("NATIVE", "placeholder=Username", 0, 120000)){
            // If statement
        }
        client.elementSendText("NATIVE", "placeholder=Username", 0, "company");
        if(client.waitForElement("NATIVE", "placeholder=Password", 0, 10000)){
            // If statement
        }
        client.elementSendText("NATIVE", "placeholder=Password", 0, "company");
        client.click("NATIVE", "accessibilityLabel=loginButton", 0, 1);
        client.click("NATIVE", "accessibilityLabel=Expense Report", 0, 1);
        for (int i=0;i<20;i++) {
            client.click("NATIVE", "xpath=//*[@text='Add']", 0, 1);
        }
    }

    @After
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        String monitorsData = client.getMonitorsData("");
        System.out.println("Monitor Data :"+monitorsData);
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future.
        client.releaseClient();
    }
}

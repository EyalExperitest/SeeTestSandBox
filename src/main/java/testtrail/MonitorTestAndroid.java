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
public class MonitorTestAndroid {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project20";
    protected Client client = null;

    @Before
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "MonitorTestAndroid");
    }

    @Test
    public void testMonitorTestAndroid(){
        client.setDevice("adb:asus Nexus 7");
        //client.setDevice("adb:A0001");
        client.launch("com.experitest.ExperiBank/.LoginActivity", true, true);
        client.startMonitor("com.experitest.ExperiBank");
        if(client.waitForElement("NATIVE", "hint=Username", 0, 30000)){
            // If statement
        }
        client.elementSendText("NATIVE", "hint=Username", 0, "company");
        if(client.waitForElement("NATIVE", "hint=Password", 0, 10000)){
            // If statement
        }
        client.elementSendText("NATIVE", "hint=Password", 0, "company");
        client.click("NATIVE", "text=Login", 0, 1);
        client.click("NATIVE", "text=Expense Report", 0, 1);
        for (int i=0;i<10;i++) {
            client.click("NATIVE", "text=Add", 0, 10);
        }
    }

    @After
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        String str0 = client.getMonitorsData("");
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future.
        client.releaseClient();
    }
}

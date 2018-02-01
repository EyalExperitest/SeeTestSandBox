package exported;

/**
 * Created by eyal.neumann on 1/10/2018.
 */
//package <set your test package>;
import clients.MonitoredClient;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class LoginLogoutIOS {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project15";
    protected MonitoredClient client = null;


    @BeforeClass
    public void oneTimeSetUp() {
        MonitoredClient.startClientMonitor();
    }
    @AfterClass
    public void oneTimeTearDown() {
        MonitoredClient.printClientMonitor();
    }


    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        client = new MonitoredClient(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "LoginLogoutIOS");
    }

    @Test(invocationCount = 1000,alwaysRun = true)
    public void testLoginLogout(){
        client.setDevice("ios_app:SE pink");
        client.launch("com.experitest.ExperiBank", true, true);
        client.deviceAction("Portrait");
        client.elementSendText("NATIVE", "placeholder=Username", 0, "company");
        if(client.waitForElement("NATIVE", "placeholder=Password", 0, 30000)){
            // If statement
        }
        client.elementSendText("NATIVE", "placeholder=Password", 0, "company");
        if(client.waitForElement("NATIVE", "accessibilityLabel=loginButton", 0, 30000)){
            // If statement
        }
        client.click("NATIVE", "accessibilityLabel=loginButton", 0, 1);
        client.click("NATIVE", "accessibilityLabel=logoutButton", 0, 1);
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

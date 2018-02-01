package exported;

/**
 * Created by eyal.neumann on 1/10/2018.
 */
//package <set your test package>;
import clients.MonitoredClient;
import org.testng.annotations.*;

/**
 *
 */
public class LoginLogoutAndroid {
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
        client.setReporter("xml", "reports", "LoginLogout Android");
    }

    @Test(invocationCount = 1000,alwaysRun = true)
    public void testLoginLogout(){
        client.setDevice("adb:samsung GT-I9301I");
        client.launch("com.experitest.ExperiBank/.LoginActivity", true, true);
        client.elementSendText("NATIVE", "hint=Username", 0, "company");
        client.elementSendText("NATIVE", "hint=Password", 0, "company");
        client.click("NATIVE", "id=loginButton", 0, 1);
        client.click("NATIVE", "xpath=//*[@id='logoutButton']", 0, 1);
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

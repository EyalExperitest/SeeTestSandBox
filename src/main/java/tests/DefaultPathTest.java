package tests;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class DefaultPathTest {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;
    String setReporter;
    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
         setReporter = client.setReporter("xml", "reports", "DefaultPathTest");
    }

    @Test(groups = {"seetest"})
    public void testDefaultPathTest(){
        client.setDevice("ios_app:iPadMini B0013");
/*      String str0 = client.getMonitorsData("");
        String str1 = client.getDeviceLog();*/
        String str2 = client.getDeviceProperty("device.name");
        client.collectSupportData(setReporter,"",str2,"Scenario","Expected","Actual");
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

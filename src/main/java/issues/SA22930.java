package issues;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class SA22930 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA22930");
    }

    @Test(groups = {"seetest"})
    public void testSA22930(){
        client.setDevice("ios_app:iPhone 6s plus #B0134");
        //client.launch("com.good.gcs.g3.enterprise", true, false);
        //client.elementSendText("NATIVE", "accessibilityLabel=Enter Password", 0, "good123");
        //client.deviceAction("Enter");
        client.elementSendText("NATIVE", "text=Location", 0, "Calendar_1084_");
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

package video;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class EriBankTestCaptureIOS {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml,html2", "reports", "EriBankTestCaptureIOS");
    }

    @Test(groups = {"seetest"})
    public void testEriBankTestCaptureIOS(){
        client.setDevice("ios_app:iPhone 6s #B0133");
        client.startStepsGroup("G1");
        client.launch("com.experitest.ExperiBank", true, true);
        if(client.waitForElement("NATIVE", "placeholder=Username", 0, 10000)){
            // If statement
        }
        client.elementSendText("NATIVE", "placeholder=Username", 0, "company");
        client.elementSendText("NATIVE", "placeholder=Password", 0, "company");
        client.click("NATIVE", "accessibilityLabel=loginButton", 0, 1);
        if(client.waitForElement("NATIVE", "accessibilityLabel=Advanced Actions", 0, 10000)){
            // If statement
        }
        client.stopStepsGroup();
        client.startStepsGroup("G2");
        client.click("NATIVE", "accessibilityLabel=Advanced Actions", 0, 1);
        client.setShowReport(false);
        if(client.waitForElement("NATIVE", "accessibilityLabel=Scan Check", 0, 10000)){
            // If statement
        }
        client.click("NATIVE", "accessibilityLabel=Scan Check", 0, 1);
        client.simulateCapture("C:\\Users\\eyal.neumann\\Pictures\\login.png");
        client.setShowReport(true);
        client.click("NATIVE", "text=Scan check", 0, 1);
        client.sleep(10000);
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

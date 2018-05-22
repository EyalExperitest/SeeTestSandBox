package tests;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class Add10000 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "Add10000");
    }

    @Test(groups = {"seetest"})
    public void testAdd10000(){
        client.setDevice("ios_app:iPhone 6s plus #B0134");
        client.launch("com.experitest.ExperiBank", true, true);
        client.startMonitor("com.experitest.ExperiBank");

        if(client.waitForElement("NATIVE", "placeholder=Username", 0, 120000)){
            // If statement
        }
        client.elementSendText("NATIVE", "placeholder=Username", 0, "company");
        client.elementSendText("NATIVE", "placeholder=Password", 0, "company");
        client.click("NATIVE", "accessibilityLabel=loginButton", 0, 1);
        client.click("NATIVE", "accessibilityLabel=Expense Report", 0, 1);
        for (int i=0;i<1000;i++) {
            client.click("NATIVE", "xpath=//*[@text='Add']", 0, 10);
        }
        String str0 = client.getMonitorsData();

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

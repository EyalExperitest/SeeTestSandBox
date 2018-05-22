package issues;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class SA22376 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA22376");
    }

    @Test(groups = {"seetest"})
    public void testSA22376(){
        client.setDevice("adb:SGP611");
        client.addMobileListener("NATIVE", "xpath=//*[@text='Incoming call'",
                new MobileListener() {
                    @Override
                    public boolean recover(String type, String xpath) {
                        client.click("NATIVE", "xpath=//*[@text='Decline']", 0, 1);
                        return true;
                    }
                }
        );

        String str0 = client.getVisualDump("Native");
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

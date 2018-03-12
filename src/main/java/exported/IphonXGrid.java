package exported;

/**
 * Created by eyal.neumann on 3/11/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class IphonXGrid {
    private String host = "localhost";
    private int port = 8889;
    private String accessKey = "eyJ4cC51IjoyMTc2LCJ4cC5wIjoyLCJ4cC5tIjoiTVRRNU9USXpPRE0zTkRrek9RIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjQ2NzIxMzI4NTQsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.na315fDIXdpqkRVem7V1vbiHnST4I7Ee3vYMIpBb11A";
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project17";
    protected Client client = null;
    protected GridClient grid = null;

    @BeforeMethod
    public void setUp(){
        // In case your user is assign to a single project you can provide an empty string,
        // otherwise please specify the project name
        grid = new GridClient(accessKey, "qacloud.experitest.com",443, true);
        client = grid.lockDeviceForExecution("IphonXGrid","@serialnumber='e2abf4b7b81fecf85e8ab40e80adb282eaa73753'", 10, 50000);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "IphonXGrid");
    }

    @Test(groups = {"seetest"})
    public void testIphonXGrid(){
        // This command "setDevice" is not applicable for GRID execution
        client.launch("com.apple.mobiletimer", true, false);
        /*client.click("NATIVE", "xpath=/*//*[@text='Stopwatch']", 0, 1);
        client.click("NATIVE", "xpath=/*//*[@text='Start']", 0, 1);
        String str0 = client.elementGetText("NATIVE", "xpath=/*//*[@text and @class='UIAView' and ./parent::*[@class='UIAView' and ./parent::*[@class='UIAScrollView']]]", 0);
*/    }

    @AfterMethod
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future.
        client.releaseClient();
    }
}

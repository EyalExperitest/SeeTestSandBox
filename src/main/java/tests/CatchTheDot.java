package tests;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class CatchTheDot {
    private String host = "localhost";
    private int port = 8889;
    private String accessKey = "eyJ4cC51IjoyMTc2LCJ4cC5wIjoyLCJ4cC5tIjoiTVRRNU9USXpPRE0zTkRrek9RIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjQ2NzIxMzI4NTQsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.na315fDIXdpqkRVem7V1vbiHnST4I7Ee3vYMIpBb11A";
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;
    protected GridClient grid = null;

    @BeforeMethod
    public void setUp(){
        // In case your user is assign to a single project you can provide an empty string,
        // otherwise please specify the project name
        grid = new GridClient(accessKey, "qacloud.experitest.com",443, true);
        client = grid.lockDeviceForExecution("CatchTheDot","@serialnumber='HT51HWV00455'", 10, 50000);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "CatchTheDot");
    }

    @Test(groups = {"seetest"})
    public void testCatchTheDot(){
        // This command "setDevice" is not applicable for GRID execution
        //client.install("cloud:com.game.arielhazan.gameapplication/arielhazan.com.game.gameapplication.MainActivity",true,true);
        client.launch("cloud:com.game.arielhazan.gameapplication/arielhazan.com.game.gameapplication.MainActivity", true, true);
        client.elementSendText("NATIVE", "hint=Enter a user name", 0, "company");
        client.elementSendText("NATIVE", "hint=Enter a password", 0, "company");
        client.click("NATIVE", "text=Sign in", 0, 1);
        client.click("NATIVE", "text=Start new game", 0, 1);

        try {
            while (true) {
                client.click("NATIVE", "xpath=//*[@id='Dot']", 0, 1);
            }
        } catch (Exception e) {
        }

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

package issues;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class SA22282_STA {
    private String host = "localhost";
    private int port = 8889;
    private String accessKey = "eyJ4cC51IjoyMTc2LCJ4cC5wIjoyLCJ4cC5tIjoiTVRRNU9USXpPRE0zTkRrek9RIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjQ2NzgwNjY4OTYsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.nG6BiQ2fswkSxWQDnTkIVEDKw0v0f-rYYffcSQdPsGE";
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;
    protected GridClient grid = null;

    @BeforeMethod
    public void setUp(){
        // In case your user is assign to a single project you can provide an empty string,
        // otherwise please specify the project name
        grid = new GridClient(accessKey, "qacloud.experitest.com",443, true);
        long t0=System.currentTimeMillis();
        try {
            client = grid.lockDeviceForExecution("SA22282_STA","@serialnumber='3801a15c53ba3300 00000'", 10, 20000);
        } catch (Exception e) {
            long t1=System.currentTimeMillis();
            long dt=t1-t0;
            System.out.println("Time :"+dt+"\n");
            throw e;
        }
        long t1=System.currentTimeMillis();
        long dt=t1-t0;
        System.out.println("Time :"+dt);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA22282_STA");
    }

    @Test(groups = {"seetest"})
    public void testSA22282_STA(){

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

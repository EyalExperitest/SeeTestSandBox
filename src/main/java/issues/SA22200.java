package issues;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class SA22200 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    private String accessKey = "eyJ4cC51Ijo3LCJ4cC5wIjoyLCJ4cC5tIjoiTUEiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE4Mzk5MTMxMjEsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.NDxf8DjL1TrXuFw6RpbLOBxTn0hVVPUCo3xc7xWknEw";

    protected Client client = null;
    private String setReporter;
    protected GridClient grid = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        setReporter=client.setReporter("xml", "reports", "SA22200");

/*        grid = new GridClient(accessKey, "mastercloud",80, false);
        client = grid.lockDeviceForExecution("SA-222000","@serialnumber='60ab9979d3fbef1c2692ac9b2b0aa766cb3efb44'", 30, 50000);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA-222000");*/

    }

    @Test(groups = {"seetest"})
    public void testSA22200(){
        client.setDevice("ios_app:iPhone 6s - #B0076");
        client.startCaptureNetworkDump("C:\\PCAP\\my001.pcap");
        for (int i=0;i<3;i++) {
            client.launch("https://www.youtube.com/watch?v=TrLnMs89P5E", true, false);
            client.sleep(10000);
            client.launch("https://www.youtube.com/watch?v=xmUuFGFnQ-E", true, false);
            client.sleep(10000);
            client.launch("https://www.youtube.com/watch?v=O5RdMvgk8b0&t=2834s", true, false);
            client.sleep(10000);
            client.launch("https://www.youtube.com/watch?v=8t3XYNxnUBs", true, false);
            client.sleep(10000);
        }


        client.stopCaptureNetworkDump();
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

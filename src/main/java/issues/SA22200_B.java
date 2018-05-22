package issues;

//package <set your test package>;

import com.experitest.client.Client;
import com.experitest.client.GridClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/**
 *
 */
public class SA22200_B {
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

      /*grid = new GridClient(accessKey, "mastercloud",80, false);
        client = grid.lockDeviceForExecution("SA-222000","@serialnumber='f759ec5d8343175b2c68f856c9c47559aa1fc0fc'", 30, 50000);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA-222000");*/

    }

    @Test(groups = {"seetest"})
    public void testSA22200(){
        client.setDevice("ios_app:iPhone se #B0094");
        client.startCaptureNetworkDump("C:\\PCAP\\my002.pcap");
        for (int i=0;i<5;i++) {
            client.launch("https://en.wikipedia.org/wiki/Main_Page", true, false);
            client.sleep(10000);
            client.launch("https://en.wikipedia.org/wiki/Jerusalem", true, false);
            client.sleep(10000);
            client.launch("https://en.wikipedia.org/wiki/Israel", true, false);
            client.sleep(10000);
            client.launch("https://en.wikipedia.org/wiki/Hebrew_language", true, false);
            client.sleep(10000);
        }

        client.stopCaptureNetworkDump();
    }
    @Test@Ignore
    public void testSA22200_Local(){
        //client.setDevice("ios_app:navot 5s#B0070");
//        client.launch("http://eyalneumann.experitest.local:8000/Example.html", true, false);
        client.startCaptureNetworkDump("C:\\PCAP\\my002.pcap");
        client.launch("http://192.168.2.39:8000/Example.html", true, true);
        client.hybridWaitForPageLoad(100000);
        client.sleep(10000);
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

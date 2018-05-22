package issues;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class SA20129 {
    private String host = "localhost";
    private int port = 8889;
    private String accessKey = "eyJ4cC51Ijo3LCJ4cC5wIjoyLCJ4cC5tIjoiTUEiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE4Mzk5MTMxMjEsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.NDxf8DjL1TrXuFw6RpbLOBxTn0hVVPUCo3xc7xWknEw";
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;
    protected GridClient grid = null;

    @BeforeMethod
    public void setUp(){
        // In case your user is assign to a single project you can provide an empty string,
        // otherwise please specify the project name
        grid = new GridClient(accessKey, "mastercloud",80, false);
        //client = grid.lockDeviceForExecution("SA20129","@serialnumber='HGAFJ9TG'", 10, 50000);
        //client = grid.lockDeviceForExecution("SA20129","@serialnumber='016fd8f1dd4f22bd'", 10, 50000);
        client = grid.lockDeviceForExecution("SA20129","@serialnumber='MWS0216817004401'", 10, 50000);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA20129");
    }

    @Test(groups = {"seetest"})
    public void testSA20129(){
        // This command "setDevice" is not applicable for GRID execution
        //client.launch("https://www.google.com/", false, false);
        client.startMonitor("com.android.chrome");
        for (int i=0;i<10;i++) {
            //client.launch("https://www.google.com/", false, false);
            //client.hybridWaitForPageLoad(10000);
            //client.launch("https://www.wikipedia.org/", false, false);
            //client.hybridWaitForPageLoad(10000);
            client.sleep(1000);

        }
        String str0 = client.getMonitorsData("C:\\Path\\ChromeMonitor004.csv");
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

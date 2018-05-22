package tests;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class GetDevicesInformationGrid {
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
        System.out.println(grid.getDevicesInformation());

        client = grid.lockDeviceForExecution("GetDevicesInformationGrid","", 10, 50000);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "GetDevicesInformationGrid");
    }

    @Test(groups = {"seetest"})
    public void testGetDevicesInformationGrid(){
        // This command "setDevice" is not applicable for GRID execution
        // This command "getDevicesInformation" is not applicable for GRID execution
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

package tests;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class ReportSTA {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "ReportSTA");
    }

    @Test(groups = {"seetest"})
    public void testReportSTA(){
        client.setDevice("adb:SM-T550");
        String path = "C:\\Pic\\x.png";
        client.report(path,"Reporting String :"+ path,true);

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

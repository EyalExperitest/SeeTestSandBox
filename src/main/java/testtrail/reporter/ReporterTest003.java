package testtrail.reporter;

/**
 * Created by eyal.neumann on 3/11/2018.
 */
//package <set your test package>;

import com.experitest.client.Client;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 */
public class ReporterTest003 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project17";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports\\reports001", "ReporterTest002");
        client.setShowImageInReport(true);
    }

    @Test(groups = {"seetest"})
    public void testReporterTest001(){
        client.setDevice("ios_app:iPad(3)");
        client.verifyElementFound("Native","xpath=//*",0);
        client.verifyElementFound("Native","xpath=//*[@id='Nope']",0);



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

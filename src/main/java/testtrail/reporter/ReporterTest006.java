package testtrail.reporter;

/**
 * Created by eyal.neumann on 3/11/2018.
 */
//package <set your test package>;

import com.experitest.client.Client;
import com.experitest.client.GridClient;
import com.experitest.client.InternalException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 */
public class ReporterTest006 {
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
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "ReporterTest006");

    }

    @Test(groups = {"seetest"})
    public void testReporterTest006(){
        client.setDevice("ios_app:iPad(3)");
        client.launch("com.apple.springboard", true, false);
        client.verifyElementFound("Native","xpath=//*",0);
        client.report("Failure Report",false);
        try {client.verifyElementFound("Native","xpath=//*[@id='Nope']",0);} catch (InternalException e) {        }
        try {client.verifyElementFound("Native","xpath=//*[@id='NopeA']",0);} catch (InternalException e) {        }
        try {client.verifyElementFound("Native","xpath=//*[@id='NopeB']",0);} catch (InternalException e) {        }
        client.stopStepsGroup();

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

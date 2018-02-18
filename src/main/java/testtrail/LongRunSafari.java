package testtrail;

/**
 * Created by eyal.neumann on 2/14/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class LongRunSafari {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project15";
    protected Client client = null;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "LongRunSafari");
    }

    @Test(invocationCount = 50,alwaysRun = true)
    public void testLongRunSafari(){
        client.setDevice("ios_app:navot’s iPad");
        client.hybridClearCache(true, true);
        for(int i=0;i<10;i++)
        {
            System.out.println(i);
            client.launch("safari:www.google.com", true, true);
            client.verifyElementFound("Web","xpath=//*[@nodeName='DIV' and @width>0 and ./parent::*[@id='tsbb']]", 0);
            client.launch("safari:m.ebay.com", true, true);
            client.verifyElementFound("Web", "xpath=//*[@id='ghs-submit']", 0);
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

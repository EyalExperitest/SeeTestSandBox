package issues;

/**
 * Created by eyal.neumann on 3/1/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class SA21832 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project17";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA21832");
    }

    @Test(groups = {"seetest"})
    public void testSA21832(){
        //client.setDevice("adb:samsung SM-T560");
        client.setDevice("ios_app:iPhone 8plus B0144");

        for (int i=0;i<100;i++) {
            client.sleep(1000);
            String str0 = client.getVisualDump("Native");
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

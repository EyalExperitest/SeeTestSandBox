package issues;

/**
 * Created by eyal.neumann on 3/26/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class SA21249 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA21249");
    }

    @Test(groups = {"seetest"})
    public void testSA21249(){
        client.setDevice("ios_app:iPhone b0019");
        boolean instrument = true;
        if(client.install("cloud:uniqueName=Eyal", instrument, false)){
            // If statement
        }
        client.launch("com.experitest.UICatalog", instrument, true);
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

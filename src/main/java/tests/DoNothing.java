package tests;

/**
 * Created by eyal.neumann on 3/19/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
 *
 */
public class DoNothing {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project20";
    protected Client client = null;

    @Before
    public void setUp(){
        client = new Client(host, port, true);
        //client.setProjectBaseDirectory(projectBaseDirectory);
       // client.setReporter("xml", "reports", "DoNothing");
    }

    @Test
    public void testDoNothing(){
        client.setDevice("ios_app:6 plus");
        client.sleep(120000);
    }

    @After
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
       // client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future.
        client.releaseClient();
    }
}

package issues;

/**
 * Created by eyal.neumann on 1/28/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
 *
 */
public class SA21309 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project15";
    protected Client client = null;

    @Before
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA21309");
    }

    @Test
    public void testSA21309(){
        client.setDevice("adb:LENOVO Lenovo TB-8504F");
        client.launch("http://imdb.com/", true, false);
        client.elementSendText("WEB", "//*[@name='q']", 0, "<");
        String str0 = client.getVisualDump("Web");
    }

    @After
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future.
        client.releaseClient();
    }
}

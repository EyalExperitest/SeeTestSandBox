package testtrail;

/**
 * Created by eyal.neumann on 3/12/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
 *
 */
public class TransactionTest1000 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project20";
    protected Client client = null;

    @Before
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "TransactionTest1000");
    }

    @Test
    public void testTransactionTest1000(){
        client.setDevice("ios_app:iPad(3)");


        for (int j=0;j<10;j++) {
            for (int i=0;i<10;i++) {
                String name = "" + i * 1000;
                client.startTransaction(name);
                client.sleep(i * 1000);
                client.endTransaction(name);
            }
        }

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

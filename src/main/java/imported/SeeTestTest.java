package imported;

/**
 * Created by eyal.neumann on 1/30/2018.
 */
// Insert your package here
import com.experitest.client.*;
import org.junit.*;
import java.util.concurrent.TimeUnit;

public class SeeTestTest {

    protected Client client = null;
    protected GridClient gridClient = null;

    @Before
    public void setUp() {
        // In case your user is assigned to a single project leave projectName as empty string, otherwise please specify the project name
        gridClient = new GridClient("admin", "Aa123456", "", "https://192.168.2.30:443");
        client = gridClient.lockDeviceForExecution("SA-21696", "@serialnumber='0e1f7f2fcdc5b47b1c76d07bac240d09a99fd68d'", 120, TimeUnit.MINUTES.toMillis(2));
        client.setReporter("xml", "reports" , "SA-21696");
    }

    @Test
    public void testSeeTestTest() {
        String visualDump = client.getVisualDump("Native");
        System.out.println(visualDump);

    }

    @After
    public void tearDown() {
        client.generateReport(false);
        client.releaseClient();
    }
}
package grid.imported;

/**
 * Created by eyal.neumann on 1/18/2018.
 */
// Insert your package here
import com.experitest.client.*;
import org.junit.*;
import java.util.concurrent.TimeUnit;

public class SA21301 {

    protected Client client = null;
    protected GridClient gridClient = null;

    @Before
    public void setUp() {
        // In case your user is assigned to a single project leave projectName as empty string, otherwise please specify the project name
        gridClient = new GridClient("admin","Experitest2012", "", "http://localhost:8090");
        client = gridClient.lockDeviceForExecution("SA21301", "@serialnumber='0123456789ABCDEF'", 120, TimeUnit.MINUTES.toMillis(2));
        client.setReporter("xml", "reports" , "SA 21301");
    }

    @Test
    public void testSA21301() {
        client.deviceAction("Power");
    }

    @After
    public void tearDown() {
        client.generateReport(false);
        client.releaseClient();
    }
}

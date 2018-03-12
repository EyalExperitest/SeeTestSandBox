package imported;

/**
 * Created by eyal.neumann on 1/30/2018.
 */
// Insert your package here
import com.experitest.client.*;
import org.json.JSONObject;
import org.junit.*;
import java.util.concurrent.TimeUnit;

public class SeeTestTest {

    protected Client client = null;
    protected GridClient gridClient = null;

    @Before
    public void setUp() {
        // In case your user is assigned to a single project leave projectName as empty string, otherwise please specify the project name
        gridClient = new GridClient("admin", "Experitest2012", "", "https://eyalneumann.experitest.local:8091");
        client = gridClient.lockDeviceForExecution("SA-21575", "@serialnumber='87345845f042238c45d80e66c7b95b48766337eb'", 120, TimeUnit.MINUTES.toMillis(2));
        client.setReporter("xml", "reports" , "SA-21575");
    }

    @Test
    public void testSeeTestTest() {
        String visualDump = client.getVisualDump("Native");
        System.out.println(visualDump);
        //client.install("cloud:com.experitest.ExperiBank", true, true);
        client.launch("com.experitest.ExperiBank", true, true);
        client.deviceAction("Portrait");
        client.elementSendText("NATIVE", "placeholder=Username", 0, "company");
        if(client.waitForElement("NATIVE", "placeholder=Password", 0, 30000)){
            // If statement
        }
        client.elementSendText("NATIVE", "placeholder=Password", 0, "company");
        if(client.waitForElement("NATIVE", "accessibilityLabel=loginButton", 0, 30000)){
            // If statement
        }
        client.click("NATIVE", "accessibilityLabel=loginButton", 0, 1);

    }

    @After
    public void tearDown() {
        client.generateReport(false);
        client.releaseClient();
    }
}
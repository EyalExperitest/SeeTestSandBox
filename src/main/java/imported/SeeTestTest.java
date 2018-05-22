package imported;

// Insert your package here
import com.experitest.client.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class SeeTestTest {

    protected Client client = null;
    protected GridClient gridClient = null;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // In case your user is assigned to a single project leave projectName as empty string, otherwise please specify the project name
        gridClient = new GridClient("admin", "Experitest2012", "", "https://eyalneumann.experitest.local:8091");
        client = gridClient.lockDeviceForExecution("Untitled", "@serialnumber='b386670b67fa917c2a65a9f2d70470347457678b'", 120, TimeUnit.MINUTES.toMillis(2));
        client.setReporter("xml", "reports" , "LongRun");
    }

    @Test(invocationCount = 30,alwaysRun = true)
    public void testSeeTestTest() {
        client.getVisualDump("Native");
        client.launch("com.experitest.ExperiBank", true, true);
        client.elementSendText("NATIVE", "text=Username", 0, "company");
        client.elementSendText("NATIVE", "text=Password", 0, "company");
        client.click("NATIVE", "text=Login", 0, 1);
    }

    @AfterMethod
    public void tearDown() {
        client.generateReport(false);
        client.releaseClient();
    }
}
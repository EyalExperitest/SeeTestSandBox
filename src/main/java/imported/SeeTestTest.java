package imported;

/**
 * Created by Mac10 on 22/05/2018.
 */
// Insert your package here
import com.experitest.client.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class SeeTestTest {

    protected Client client = null;
    protected GridClient gridClient = null;
    protected String setReporter;
    @BeforeMethod
    public void setUp() {
        // In case your user is assigned to a single project leave projectName as empty string, otherwise please specify the project name
        gridClient = new GridClient("eyal", "Experitest2012", "", "http://mastercloud:80");
        client = gridClient.lockDeviceForExecution("Remote File Test", "@serialnumber='HT51HWV00455'", 120, TimeUnit.MINUTES.toMillis(2));
        setReporter = client.setReporter("xml", "reports", "Remote File Test");
        System.out.println("setReporter:"+setReporter);
    }

    @Test
    public void testSeeTestTest() throws Exception {
        String capture = client.capture();
        System.out.println("Capture:"+capture);
        client.getRemoteFile(capture,10000,setReporter);

    }

    @AfterMethod
    public void tearDown() {
        client.generateReport(false);
        client.releaseClient();
    }
}
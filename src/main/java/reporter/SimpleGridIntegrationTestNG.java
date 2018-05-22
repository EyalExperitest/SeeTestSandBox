package reporter;

import com.experitest.client.Client;
import com.experitest.client.GridClient;
import com.experitest.manager.api.ManagerPublisher;
import com.experitest.manager.client.PManager;
import com.experitest.manager.testng.ManagerTestNGListener;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

@Listeners(ManagerTestNGListener.class)
public class SimpleGridIntegrationTestNG {

    private GridClient gridClient = new GridClient("eyal", "Experitest2012", "", "https://qacloud.experitest.com");
    private Client client = null;

    private ManagerPublisher managerPublisher = null;

    @BeforeMethod
    public void setup(Method method) {
        client = gridClient.lockDeviceForExecution(method.getName(), "@os='ios'", 10, 60*1000);
        String reportPath = client.setReporter("xml", "reports", "Simple Report Test");
        // Init the managerPublisher in the before section
        managerPublisher = gridClient.createPublisher(method.getName(), client);

        // Provide PManager with the latest managerPublisher
        PManager.setPublisher(managerPublisher);
    }

    @Test
    public void simpleTest() {
        client.report("Message",true);
        // Test goes here

        // Add key value tag
        managerPublisher.addProperty("StreamName", "Eyal");

        managerPublisher.addProperty("team", "automation");
    }

    @AfterMethod
    public void tearDown() {
        String generateReport = client.generateReport(false);

        // Add custom report folder
        managerPublisher.addReportFolder(new File(generateReport));
    }
}
package reporter;

/**
 * Created by eyal.neumann on 3/8/2018.
 */

import com.experitest.client.Client;
import com.experitest.client.GridClient;
import com.experitest.manager.api.ManagerPublisher;
import com.experitest.manager.api.TestSession;
import com.experitest.manager.client.PManager;
import com.experitest.manager.junit.ManagerTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.concurrent.TimeUnit;

@RunWith(ManagerTestRunner.class)
public class SimpleGridIntegrationJUnit {
    private String accessKey = "eyJ4cC51IjozLCJ4cC5wIjoyLCJ4cC5tIjoiTVRVeE9UZ3dOamd4TmpReE9BIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4MzUxNjY4MTYsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.SZE2N4mR-7EKb3spKTXJjtBr5Zg1msqpe9sc6QNcGdw";
    private GridClient gridClient =   new GridClient(accessKey, "https://eyalneumann.experitest.local:8091");

    private Client client = null;

    private ManagerPublisher managerPublisher = null;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setup() {
        client = gridClient.lockDeviceForExecution(testName.getMethodName(), "", 10, 60*1000);
        managerPublisher = gridClient.createPublisher(testName.getMethodName(), client);
        PManager.setPublisher(managerPublisher);

        client.setReporter("xml", "reports" , "Reporter Test 001");

    }

    @Test
    public void simpleTest() {

        client.report("Reporter Line 1",true);
        TestSession testSession = managerPublisher.getTestSession();
        String project = testSession.getProject();
        System.out.println("project:"+project);
        String managerTestId = testSession.getManagerTestId();
        System.out.println("managerTestId:["+managerTestId+"]");
        String url = testSession.getUrl();
        System.out.println("url:"+url);

        client.report("url:"+url,true);

        managerPublisher.addProperty("team", "automation");
    }

    @After
    public void tearDown() throws Exception {
        String generateReport = client.generateReport(false);
        // Add custom report folder
        managerPublisher.addReportFolder(new File(generateReport));
    }
}


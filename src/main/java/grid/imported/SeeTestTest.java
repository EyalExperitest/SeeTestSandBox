package grid.imported;

/**
 * Created by eyal.neumann on 1/18/2018.
 */
// Insert your package here
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
        gridClient = new GridClient("eyal","Experitest2012", "", "https://qacloud.experitest.com");

        client = gridClient.lockDeviceForExecution("Report With Local Image", "", 120, TimeUnit.MINUTES.toMillis(2));
        client.setReporter("xml", "reports" , "Report With Local Image");
    }

    @Test
    public void testReportWithLocalImage() throws InterruptedException {

/*        for(int time=10000;time<1000000;time+=100000){
            System.out.println("Trying "+time+" mSeconds");
            Thread.sleep(time);
            client.getVisualDump("Native");
            System.out.println(time+" Passed");
        }*/


    }

    @After
    public void tearDown() {
        client.generateReport(false);
        client.releaseClient();
    }
}
package video;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class VideoReportTest {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml,html2", "reports", "VideoReportTest");
    }

    @Test(groups = {"seetest"})
    public void testVideoReportTest(){
        client.setDevice("ios_app:iPhone 6s - #B0076");
        String str0 = client.capture("Capture");
        String str1 = client.getVisualDump("Native");

        client.report("Success Report",true);
        client.report("Failure Report",false);

        client.report("C:\\Users\\eyal.neumann\\Pictures\\login.png","C:\\Users\\eyal.neumann\\Pictures\\login.png",true);
        client.report("C:\\Users\\eyal.neumann\\Pictures\\login000.png","C:\\Users\\eyal.neumann\\Pictures\\login000.png",false);

        try {
            client.verifyElementFound("Native","xpath=//*[@text='NOP'])",1);
        } catch (InternalException e) {
            e.printStackTrace();
        }


    }

    @AfterMethod
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future.
        client.releaseClient();
    }
}

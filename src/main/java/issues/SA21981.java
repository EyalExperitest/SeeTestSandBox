package issues;

/**
 * Created by eyal.neumann on 3/25/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class SA21981 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    private String accessKey = "eyJ4cC51Ijo3LCJ4cC5wIjoyLCJ4cC5tIjoiTVRVd09UVXlPVEl3TWpreE53IiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4MjQ4OTIwNzAsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.icG-vUduTupsyGxjkOqK82j3yEa5f6311NZo6gHnmDM";
    protected Client client = null;
    protected GridClient grid = null;

    @BeforeMethod
    public void setUp(){
        //client = new Client(host, port, true);
        grid = new GridClient(accessKey, "mastercloud",80, false);
        client = grid.lockDeviceForExecution("SA21981","@os='android'", 10, 50000);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA21981");
    }

    @Test(groups = {"seetest"})
    public void testSA21981(){
        //client.setDevice("adb:HUAWEI ALE-L02");
        client.launch("http://192.168.4.85:8060/html-tests/RegressionHibridApplication/HibridApplicationHtml.html", true, true);
        String text_to_send = "eXnEgITEkT";
        client.elementSendText("WEB", "xpath=//*[@id='firstName']", 0, text_to_send);
        client.click("Web", "xpath=//*[@id='lastName']", 0, 1);
        client.sleep(1500);
        client.closeKeyboard();
        String str = client.getTextIn("WEB", "xpath=//*[@id='firstName']", 0, "TEXT", "Inside", 0, 0);
        System.out.println("String:["+str+"]");
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

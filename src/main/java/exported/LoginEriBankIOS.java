package exported;

/**
 * Created by eyal.neumann on 3/4/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class LoginEriBankIOS {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project17";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "LoginEriBankIOS");
    }

    @Test(groups = {"seetest"})
    public void testLoginEriBankIOS(){
        client.setDevice("ios_app:iPhone 8plus B0144");
        client.launch("com.experitest.ExperiBank", true, false);
        client.elementSendText("NATIVE", "text=Username", 0, "company");
        client.elementSendText("NATIVE", "text=Password", 0, "company");
        client.click("NATIVE", "text=Login", 0, 1);
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

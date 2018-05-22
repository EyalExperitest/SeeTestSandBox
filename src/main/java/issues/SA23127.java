package issues;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class SA23127 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA23127");
    }

    @Test(groups = {"seetest"})
    public void testSA23127(){
        String text = "ENCRYPT:3272454672327251412F5A4F792F2F67704667592F513D3D";
        client.setDevice("adb:SM-T550");
        client.launch("cloud:com.experitest.ExperiBank/.LoginActivity", true, false);
        client.elementSendText("NATIVE", "id=usernameTextField", 0, "company");
        client.elementSendText("NATIVE", "id=passwordTextField", 0, "ENCRYPT:3272454672327251412F5A4F792F2F67704667592F513D3D");
/*        client.click("NATIVE", "id=passwordTextField", 0 ,1);
        client.sendText(text);*/
        //client.sendWhileNotFound(text,"Native","id=nop",1000,0);



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

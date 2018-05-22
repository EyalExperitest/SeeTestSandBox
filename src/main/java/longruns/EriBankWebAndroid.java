package longruns;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class EriBankWebAndroid {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "EriBankWebAndroid");
    }

    @Test(invocationCount = 10,alwaysRun = true)
    public void testEriBankWebAndroid(){
        client.setDevice("adb:HUAWEI WAS-LX1");
        client.launch("com.experitest.eribank/com.experitest.ExperiBank.LoginActivity", true, true);
        client.elementSendText("NATIVE", "hint=Username", 0, "company");
        client.elementSendText("NATIVE", "hint=Password", 0, "company");
        client.click("NATIVE", "text=Login", 0, 1);
        if(client.waitForElement("NATIVE", "partial_text=Make Payment", 0, 10000)){
            // If statement
        }
        client.click("NATIVE", "text=Make Payment", 0, 1);
        client.click("NATIVE", "text=Cancel", 0, 1);
        client.click("NATIVE", "text=Logout", 0, 1);
        client.launch("chrome:https://www.wikipedia.org/", true, true);
        if(client.waitForElement("WEB", "text=English", 0, 10000)){
            // If statement
        }
        client.click("WEB", "text=English", 0, 1);
        client.click("WEB", "id=mw-mf-main-menu-button", 0, 1);
        client.click("WEB", "text=Random", 0, 1);
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

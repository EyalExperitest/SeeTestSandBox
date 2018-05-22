package longruns;
//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class EriBankAndWeb {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "EriBankAndWeb");
    }

    @Test(invocationCount = 1000,alwaysRun = true)
    public void testEriBankAndWeb(){
        client.setDevice("ios_app:iPad");
        client.launch("com.experitest.ExperiBank", true, true);
        client.click("NATIVE", "placeholder=Username", 0, 1);
        if(client.waitForElement("NATIVE", "placeholder=Username", 0, 30000)){
            // If statement
        }
        client.elementSendText("NATIVE", "placeholder=Username", 0, "company");
        client.elementSendText("NATIVE", "placeholder=Password", 0, "company");
        if(client.waitForElement("NATIVE", "accessibilityLabel=loginButton", 0, 10000)){
            // If statement
        }
        client.click("NATIVE", "accessibilityLabel=loginButton", 0, 1);
        client.click("NATIVE", "accessibilityLabel=makePaymentButton", 0, 1);
        client.elementSendText("NATIVE", "placeholder=Phone", 0, "0987654");
        client.elementSendText("NATIVE", "placeholder=Name", 0, "Eyal");
        client.elementSendText("NATIVE", "placeholder=Amount", 0, "100");
        client.click("NATIVE", "accessibilityLabel=countryButton", 0, 1);
        client.click("NATIVE", "xpath=//*[@accessibilityLabel='USA']", 0, 1);
        client.click("NATIVE", "accessibilityLabel=sendPaymentButton", 0, 1);
        client.click("NATIVE", "xpath=//*[@text='Yes']", 0, 1);
        client.click("NATIVE", "accessibilityLabel=logoutButton", 0, 1);
        client.launch("safari:https://www.wikipedia.org/", true, false);
        client.click("WEB", "text=English", 0, 1);
        if(client.waitForElement("WEB", "id=mw-mf-main-menu-button", 0, 30000)){
            // If statement
        }
        client.click("WEB", "id=mw-mf-main-menu-button", 0, 1);
        if(client.waitForElement("WEB", "text=Random", 0, 10000)){
            // If statement
        }
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

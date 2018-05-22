package tests;

//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
 *
 */
public class EriBankIOSTest {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project23";
    private String accessKey = "eyJ4cC51Ijo3LCJ4cC5wIjoyLCJ4cC5tIjoiTUEiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE4Mzk5MTMxMjEsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.NDxf8DjL1TrXuFw6RpbLOBxTn0hVVPUCo3xc7xWknEw";
    protected GridClient grid = null;

    protected Client client = null;

    @Before
    public void setUp(){
    /*    client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "EriBankIOSTest");*/
        grid = new GridClient(accessKey, "mastercloud",80, false);
        client = grid.lockDeviceForExecution("EriBankIOSTest","@serialnumber='817c13817e7e131bdbb0fe8ce94f944051dd3422'", 10, 50000);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "EriBankIOSTest");

    }

    @Test
    public void testEriBankIOSTest(){
        //client.setDevice("ios_app:iPhone se #B0094");
        client.launch("com.experitest.ExperiBank", true, true);
        client.elementSendText("NATIVE", "placeholder=Username", 0, "company");
        client.elementSendText("NATIVE", "placeholder=Password", 0, "company");
        client.click("NATIVE", "accessibilityLabel=loginButton", 0, 1);
        client.click("NATIVE", "accessibilityLabel=makePaymentButton", 0, 1);
        client.elementSendText("NATIVE", "placeholder=Phone", 0, "09876465465421");
        client.elementSendText("NATIVE", "placeholder=Name", 0, "Eyal");
        client.elementSendText("NATIVE", "placeholder=Amount", 0, "100");
        client.click("NATIVE", "accessibilityLabel=countryButton", 0, 1);
        client.click("NATIVE", "xpath=//*[@accessibilityLabel='USA']", 0, 1);
        client.click("NATIVE", "accessibilityLabel=sendPaymentButton", 0, 1);
        client.click("NATIVE", "xpath=//*[@text='Yes']", 0, 1);
        client.click("NATIVE", "accessibilityLabel=Advanced Actions", 0, 1);
        if(client.waitForElement("NATIVE", "accessibilityLabel=Eribank Website", 0, 30000)){
            // If statement
        }
        client.click("NATIVE", "accessibilityLabel=Eribank Website", 0, 1);
        if(client.waitForElement("NATIVE", "accessibilityLabel=GO", 0, 10000)){
            // If statement
        }
        client.click("NATIVE", "accessibilityLabel=GO", 0, 1);
        if(client.waitForElement("WEB", "text=Perform continuous web and mobile app testing to accelerate release cycles and increase quality. Integrate with industry open source tools such as Appium and Selenium and execute against a large inventory of mobile devices and web browsers for quick resul...", 0, 30000)){
            // If statement
        }
        client.click("WEB", "text=Perform continuous web and mobile app testing to accelerate release cycles and increase quality. Integrate with industry open source tools such as Appium and Selenium and execute against a large inventory of mobile devices and web browsers for quick resul...", 0, 1);
    }

    @After
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future.
        client.releaseClient();
    }
}

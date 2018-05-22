package tests;

//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
 *
 */
public class LargeAndroidUICatalogTest {
    private String host = "localhost";
    private int port = 8889;
    private String accessKey = "eyJ4cC51Ijo3LCJ4cC5wIjoyLCJ4cC5tIjoiTUEiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE4Mzk5MTMxMjEsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.NDxf8DjL1TrXuFw6RpbLOBxTn0hVVPUCo3xc7xWknEw";
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project23";
    protected Client client = null;
    protected GridClient grid = null;

    @Before
    public void setUp(){
        // In case your user is assign to a single project you can provide an empty string,
        // otherwise please specify the project name
       grid = new GridClient(accessKey, "mastercloud",80, false);
        client = grid.lockDeviceForExecution("LargeAndroidUICatalogTest", "@serialnumber='0123456789ABCDEF'", 10, 50000);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "LargeAndroidUICatalogTest");
        /*
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "LargeAndroidUICatalogTest");
        */
    }

    @Test
    public void testLargeAndroidUICatalogTest(){
        //This command "setDevice" is not applicable for GRID execution
        //client.setDevice("adb:Ulefone Gemini Pro");
        client.launch("com.experitest.uicatalog/.MainActivity", true, true);
        client.click("NATIVE", "text=Buttons", 0, 1);
        if(client.waitForElement("NATIVE", "partial_text=Long Click Button", 0, 10000)){
            // If statement
        }
        client.click("NATIVE", "text=Long Click Button", 0, 1);
        client.verifyElementFound("NATIVE", "text=button Pressed", 0);
        client.click("NATIVE", "text=CheckBox", 0, 1);
        client.verifyElementFound("NATIVE", "text=Nothing Pressed", 0);
        client.verifyElementFound("NATIVE", "text=CheckBox Checked", 0);
        client.click("NATIVE", "text=CheckBox", 0, 1);
        client.verifyElementFound("NATIVE", "text=CheckBox unChecked", 0);
        client.click("NATIVE", "text=RadioButton", 0, 1);
        client.verifyElementFound("NATIVE", "text=radio button Pressed", 0);
        client.click("NATIVE", "xpath=//*[@id='imageButton']", 0, 1);
        client.verifyElementFound("NATIVE", "text=Image button pressed", 0);
        client.longClick("NATIVE", "text=Long Click Button", 0, 1, 0, 0);
        client.verifyElementFound("NATIVE", "text=Long Button Click", 0);
        client.deviceAction("Back");
        client.click("NATIVE", "text=Controls", 0, 1);
        client.click("NATIVE", "text=Switch", 0, 1);
        client.deviceAction("Back");
        client.click("NATIVE", "text=Text Fields", 0, 1);
        client.elementSendText("NATIVE", "hint=Normal Text field", 0, "Normal");
        client.elementSendText("NATIVE", "hint=Number", 0, "1000");
        client.click("NATIVE", "hint=MultiLine Text", 0, 1);
        client.sendText("Line1");
        client.deviceAction("Enter");
        client.sendText("Line2");
        //client.verifyElementFound("NATIVE", "xpath=//*[@text='Line1Line2' and @hint='MultiLine Text']", 0);
        client.deviceAction("Back");
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

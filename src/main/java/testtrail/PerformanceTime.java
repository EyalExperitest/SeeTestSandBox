package testtrail;
import com.experitest.client.*;
import org.junit.*;
/**
 *
*/
public class PerformanceTime {
    private String host = "localhost";
    private int port = 8889;
   // private String projectBaseDirectory = "C:\\Users\\ori.makover\\workspace\\project2";
    protected Client client = null;

    @Before
    public void setUp(){
        client = new Client(host, port, true);
        //client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "new");
    }

    @Test
    public void testnew(){
    	client.waitForDevice("@os='ios'", 30000);

        //client.setDevice("ios_app:iPad");w
        if(client.install("com.experitest.ExperiBank", true, false)){
            // If statement
        }
        client.launch("com.experitest.ExperiBank", true, true);
        if(client.waitForElement("NATIVE", "placeholder=Username", 0, 120000)){
            // If statement
        }
        client.elementSendText("NATIVE", "placeholder=Username", 0, "company");
        if(client.waitForElement("NATIVE", "placeholder=Password", 0, 10000)){
            // If statement
        }
        client.elementSendText("NATIVE", "placeholder=Password", 0, "company");
        client.sendText("{ENTER}");
        client.click("NATIVE", "accessibilityLabel=makePaymentButton", 0, 1);
        if(client.waitForElement("NATIVE", "accessibilityLabel=countryButton", 0, 10000)){
            // If statement
        }
        client.click("TEXT", "Select", 0, 1);
        client.elementListSelect("", "text=Tanzania", 0, false);
        client.click("NATIVE", "xpath=//*[@accessibilityLabel='Tanzania']", 0, 1);
        if(client.waitForElement("NATIVE", "accessibilityLabel=cancelButton", 0, 120000)){
            // If statement
        }
        client.click("TEXT", "Cancel", 0, 1);
        if(client.waitForElement("TEXT", "Logout", 0, 10000)){
            // If statement
        }
        client.click("NATIVE", "accessibilityLabel=logoutButton", 0, 1);
        client.launch("safari:http://www.wikipedia.org", true, false);
        if(client.waitForElement("WEB", "id=searchInput", 0, 120000)){
            // If statement
        }
        client.elementSendText("WEB", "id=searchInput", 0, "expert");
        //client.click("WEB", "name=go", 0, 1);
        client.click("WEB", "xpath=//*[@nodeName='I' and ./parent::*[@nodeName='BUTTON']]", 0, 1);
        
        
    }

    @After
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - http://experitest.com/studio/help2/WebHelp/help.htm#Report_Of_Executed_Script.htm .
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future. 
        client.releaseClient();
    }
}

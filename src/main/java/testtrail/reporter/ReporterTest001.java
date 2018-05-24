package testtrail.reporter;

/**
 * Created by eyal.neumann on 3/11/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class ReporterTest001 {
    private String host = "localhost";
    private int port = 8889;
    //private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project17";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        //client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("pdf", "reports", "ReporterTest002");
        client.setShowImageInReport(true);
    }

    @Test(groups = {"seetest"})
    public void testReporterTest001(){
        client.setDevice("ios_app:iPad(1)");
        client.startVideoRecord();
        client.startStepsGroup("Set EriBank");
        if(client.uninstall("cloud:com.experitest.ExperiBank")){
            // If statement
        }
        if(client.install("cloud:com.experitest.ExperiBank", true, false)){
            // If statement
        }
        client.launch("cloud:com.experitest.ExperiBank", true, true);
        //client.startStepsGroup("Login");

        if(client.waitForElement("NATIVE", "placeholder=Username", 0, 60000)){
            // If statement
        }
        client.elementSendText("NATIVE", "placeholder=Username", 0, "company");
        client.elementSendText("NATIVE", "placeholder=Password", 0, "company");
        client.click("NATIVE", "accessibilityLabel=loginButton", 0, 1);
        //client.stopVideoRecord();

        client.startStepsGroup("MakePayment");
        //client.startVideoRecord();

        client.click("NATIVE", "accessibilityLabel=makePaymentButton", 0, 1);
        client.elementSendText("NATIVE", "placeholder=Phone", 0, "09876543");
        client.elementSendText("NATIVE", "placeholder=Name", 0, "Eyal");
        client.elementSendText("NATIVE", "placeholder=Amount", 0, "100");
        client.setShowImageInReport(false);

        client.click("NATIVE", "accessibilityLabel=countryButton", 0, 1);
        client.elementListSelect("", "text=United Kingdom", 0, false);
        client.click("NATIVE", "xpath=//*[@accessibilityLabel='United Kingdom']", 0, 1);
        client.click("NATIVE", "accessibilityLabel=sendPaymentButton", 0, 1);
        client.click("NATIVE", "xpath=//*[@text='Yes']", 0, 1);
        //client.stopVideoRecord();
        client.startStepsGroup("Mortgage Request");
        //client.startVideoRecord();

        client.click("NATIVE", "accessibilityLabel=Mortgage Request", 0, 1);
        client.elementSendText("NATIVE", "placeholder=First Name", 0, "Eyal");
        client.elementSendText("NATIVE", "placeholder=Last Name", 0, "Neumann");
        client.elementSendText("NATIVE", "placeholder=Age", 0, "1000");
        client.elementSendText("NATIVE", "placeholder=Address 1", 0, "Here");
        client.elementSendText("NATIVE", "placeholder=Address 2", 0, "There");
        client.click("NATIVE", "accessibilityLabel=countryButton", 0, 1);
        client.click("NATIVE", "xpath=/*//*[@accessibilityLabel='Brazil']", 0, 1);
        client.elementSendText("NATIVE", "placeholder=Loan Amount", 0, "-10000000000");
        if(client.waitForElement("NATIVE", "accessibilityLabel=nextButton", 0, 30000)){
            // If statement
        }
        client.click("NATIVE", "accessibilityLabel=nextButton", 0, 1);
        client.click("NATIVE", "xpath=/*//*[@accessibilityLabel='Home']", 0, 1);
        client.click("NATIVE", "xpath=/*//*[@accessibilityLabel='20']", 0, 1);
        client.click("NATIVE", "xpath=/*//*[@accessibilityLabel='Private Job']", 0, 1);
        client.click("NATIVE", "xpath=/*//*[@accessibilityLabel='500,000']", 0, 1);
        client.click("NATIVE", "xpath=/*//*[@text='Save']", 0, 1);
        //client.stopVideoRecord();
        //client.stopStepsGroup();

        //client.setShowPassImageInReport(false);
        client.click("NATIVE", "accessibilityLabel=Expense Report", 0, 1);
        client.click("NATIVE", "xpath=/*//*[@text='Detail goes here' and (./preceding-sibling::* | ./following-sibling::*)[@text='Expense 0']]", 0, 1);
        client.click("NATIVE", "xpath=/*//*[@text='Detail goes here' and (./preceding-sibling::* | ./following-sibling::*)[@text='Expense 1']]", 0, 1);
        client.click("NATIVE", "xpath=/*//*[@text='Add']", 0, 1);
        client.click("NATIVE", "xpath=/*//*[@text='Add']", 0, 1);
        client.click("NATIVE", "xpath=/*//*[@text='Back']", 0, 1);

        client.startStepsGroup("Logout");
        //client.startVideoRecord();

        client.click("NATIVE", "accessibilityLabel=logoutButton", 0, 1);
        client.click("NATIVE", "placeholder=Username", 0, 1);
        client.click("NATIVE", "class=UIButton", 0, 1);
        client.click("NATIVE", "placeholder=Password", 0, 1);
        client.click("NATIVE", "class=UIButton", 0, 1);
        if(client.applicationClose("cloud:com.experitest.ExperiBank")){
            // If statement
        }
        String str0 = client.getCurrentApplicationName();
        //client.setShowReport(false);
        client.stopVideoRecord();
       // client.report("Failure",false);
       // client.setShowReport(true);
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

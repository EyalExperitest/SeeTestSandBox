package issues;

/**
 * Created by eyal.neumann on 3/7/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.testng.Assert;
import org.testng.annotations.*;
/**
 *
 */
public class SA22023 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project17";
    protected Client client = null;

    @BeforeMethod (alwaysRun = true)
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA22023");
        //client.setDevice("adb:HTC One M9");
        client.setDevice("adb:A0001");
        client.install("http://192.168.1.213:8090/guestAuth/repository/download/bt4/.lastSuccessful/eribank.apk",false, false);
        client.launch("com.experitest.eribank/com.experitest.ExperiBank.LoginActivity",false,true);
    }

    @Test(invocationCount = 10,alwaysRun = true)
    public void testSA22023(){
        // it is recommended to start your script with SetDevice command:
        LoginUtil_ANDROID();
        Make50DollarsPayment_ANDROID();

//        client.install("C:\\Applications\\eribank.apk",false, true);
        client.install("http://192.168.1.213:8090/guestAuth/repository/download/bt4/.lastSuccessful/eribank.apk",false, true);

        client.launch("com.experitest.eribank/com.experitest.ExperiBank.LoginActivity",false,true);

        LoginUtil_ANDROID();
        String moneyAfterReset = getBalance();
        System.out.println("Payment was reset after noReset " + moneyAfterReset);
        Assert.assertTrue(!moneyAfterReset.contains("100.00$")  && !moneyAfterReset.isEmpty());
    }

    @AfterMethod
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future.
        client.releaseClient();
    }
    private void Make50DollarsPayment_ANDROID() {
        client.isElementFound("NATIVE", "xpath=//*[@text='Make Payment']", 0); // check if the user is log in
        client.click("NATIVE", "//*[@text='Make Payment']", 0, 1);
        client.elementSendText("NATIVE", "//*[@text='Phone']", 0, "0545743484");
        client.elementSendText("NATIVE", "//*[@text='Name']", 0, "Deddy");
        client.elementSendText("NATIVE", "//*[@text='Amount']", 0, "15");

        client.click("NATIVE", "//*[@text='Select']", 0, 1);
        client.click("NATIVE", "text=USA", 0, 1);
        client.click("NATIVE", "//*[@text='Send Payment']", 0, 1);
        client.click("NATIVE", "//*[@text='Yes']", 0, 1);
    }

    private void LoginUtil_ANDROID() {
        client.elementSendText("NATIVE", "xpath=//*[@id='usernameTextField']", 0, "company");
        client.elementSendText("NATIVE", "xpath=//*[@id='passwordTextField']", 0, "company");
        client.click("NATIVE", "xpath=//*[@text='Login']", 0, 1);

    }

    public String getBalance() {
        String getText = null;
        try {
            client.waitForElement("NATIVE", "xpath=//*[@text and @class='android.view.View']",0,100000);
            getText = client.elementGetText("NATIVE", "xpath=//*[@text and @class='android.view.View']", 0);
        } catch (Exception e) {
            try {
                //getText = client.elementGetText("NATIVE", "xpath=//*[contains(@content-desc,'Your balance is:')]", 0);
                getText =client.elementGetProperty("Native","xpath=//*[contains(@content-desc,'Your balance is:')]",0,"content-desc");
                System.out.println("getText :"+getText);
            } catch (Exception e1) {
                //e1.printStackTrace();
                client.getVisualDump("Non-Instrumented");

            }

        }
        return getText;
    }
}

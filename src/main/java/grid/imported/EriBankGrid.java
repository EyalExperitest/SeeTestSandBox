package grid.imported;

/**
 * Created by eyal.neumann on 1/18/2018.
 */
// Insert your package here

import com.experitest.client.Client;
import com.experitest.client.GridClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class EriBankGrid {

    protected Client client = null;
    protected GridClient gridClient = null;

    @Before
    public void setUp() {
        // In case your user is assigned to a single project leave projectName as empty string, otherwise please specify the project name
        gridClient = new GridClient("admin", "Experitest2012", "", "http://localhost:8090");
        client = gridClient.lockDeviceForExecution("Untitled", "@serialnumber='HT71C0200017'", 120, TimeUnit.MINUTES.toMillis(2));
        client.setReporter("xml", "reports" , "Untitled");
    }

    @Test
    public void testSeeTestTest() {

        client.launch("com.experitest.ExperiBank/.LoginActivity", true, true);
        if(client.waitForElement("NATIVE", "hint=Username", 0, 60000)){
            // If statement
        }
        client.elementSendText("NATIVE", "hint=Username", 0, "company");
        client.elementSendText("NATIVE", "hint=Password", 0, "company");
        client.click("NATIVE", "text=Login", 0, 1);
        client.click("NATIVE", "text=Make Payment", 0, 1);
        client.elementSendText("NATIVE", "hint=Phone", 0, "0987654346567");
        client.elementSendText("NATIVE", "hint=Name", 0, "Eyal");
        client.elementSendText("NATIVE", "hint=Amount", 0, "100");
        client.click("NATIVE", "text=Select", 0, 1);
        client.click("NATIVE", "text=Greenland", 0, 1);
        client.click("NATIVE", "text=Send Payment", 0, 1);
        client.click("NATIVE", "text=Yes", 0, 1);
        client.click("NATIVE", "text=Logout", 0, 1);

    }

    @After
    public void tearDown() {
        client.generateReport(false);
        client.releaseClient();
    }
}
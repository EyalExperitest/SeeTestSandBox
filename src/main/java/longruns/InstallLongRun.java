package longruns;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class InstallLongRun {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "InstallLongRun");
    }

    @Test(invocationCount = 50,alwaysRun = true)
    public void testInstallLongRun(){
        client.setDevice("adb:LGE Nexus 5X");
        if(client.install("http://192.168.1.213:8090/guestAuth/repository/download/bt4/.lastSuccessful/UICatalog.apk", true, false)){
            // If statement
        }
        client.launch("com.experitest.uicatalog/.MainActivity", true, false);
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

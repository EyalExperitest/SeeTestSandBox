package issues;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class SA22869 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA22869");
    }

    @Test(groups = {"seetest"})
    public void testSA22869(){
        client.setDevice("adb:samsung SM-A520F (1)");
        String str0 = client.getProperty("device.name");
        client.setDevice("ios_app:iPhone 6s plus #B0134");
        String str1 = client.getDeviceProperty("device.name");
        System.out.println(str0);
        System.out.println(str1);

        client.setDevice("adb:"+str0);
        String str3 = client.getDeviceProperty("device.name");
        System.out.println(str3);


        client.getProperty("device.name");

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

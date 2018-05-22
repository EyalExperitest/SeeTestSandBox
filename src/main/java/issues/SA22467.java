package issues;

/**
 * Created by eyal.neumann on 3/22/2018.
 */
//package <set your test package>;
//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class SA22467 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;

    public class DeviceSetter implements Runnable {
        Client client;
        String device;
        public DeviceSetter(Client client,String device){
            this.client=client;
            this.device=device;
        }


        @Override
        public void run() {
            client.setDevice(this.device);
        }
    }

    public class DeviceOpener implements Runnable {
        Client client;
        public DeviceOpener(Client client){
            this.client=client;
        }


        @Override
        public void run() {
            client.openDevice();
        }
    }


    @BeforeMethod (alwaysRun = true)
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA22467");
    }

    @Test(invocationCount = 1,alwaysRun = true)
    public void testSA22467() throws InterruptedException {
        //client.setDevice("adb:SM-N7505");
        DeviceSetter deviceSetter=new DeviceSetter(client,"ios_app:iPhone b0019");
        //DeviceOpener deviceOpener=new DeviceOpener(client);

        Thread setThread=new Thread(deviceSetter);
        //Thread openThread=new Thread(deviceOpener);

        //openThread.start();
        setThread.start();

        setThread.join();
        //openThread.join();
        client.closeDevice();

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

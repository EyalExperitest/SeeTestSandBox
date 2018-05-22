package imported;
import com.experitest.client.*;
import org.junit.*;
import java.util.concurrent.TimeUnit;

public class GetDevices {

    protected Client client = null;
    protected GridClient gridClient = null;

    @Before
    public void setUp() {
        // In case your user is assigned to a single project leave projectName as empty string, otherwise please specify the project name
        gridClient = new GridClient("eyJ4cC51IjozODM2MSwieHAucCI6MzgzNTksInhwLm0iOiJNVFV4Tnpjek1qazNPRGcyTnciLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE4MzMxODAxNTYsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.h-ouxwqabPPP22s2KGWN5vnq12Bx3q7Gn5oypJQnmJs", "https://cloud.seetest.io:443");
        client = gridClient.lockDeviceForExecution("Get Devices Information", "@serialnumber='6fbd4c77'", 120, TimeUnit.MINUTES.toMillis(2));
        client.setReporter("xml", "reports" , "Untitled");
    }

    @Test
    public void testGetDevices() {
        String devicesInformation = gridClient.getDevicesInformation();
        System.out.println(devicesInformation);
    }

    @After
    public void tearDown() {
        client.generateReport(false);
        client.releaseClient();
    }
}
package testtrail;

/**
 * Created by eyal.neumann on 2/19/2018.
 */
import com.experitest.client.*;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;

public class multiGesture {
    private static final String DEVICE_NAME ="ios_app:navot’s iPad";
    private String host = "localhost";
    private int port = 8889;
    private String 	projectBaseDirectory = new File(System.getProperty("user.dir"), "projectfolder").getAbsolutePath();
    protected Client client = null;
    @Before
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "gesture", "gestureTest");
        client.setDevice(DEVICE_NAME);
    }
    @Test
    public void pinchCoordTest()
    {
        client.launch("com.apple.Maps", false, false);
        client.startMultiGesture("test");
        // finger 0
        client.multiTouchDownCoordinate(200,600, 0);
        client.multiTouchMoveCoordinate(250, 600, 0);
        client.multiTouchUp(0);

        // finger 1
        client.multiTouchDownCoordinate(170,600, 1);
        client.multiTouchMoveCoordinate(140, 600, 1);
        client.multiTouchUp(1);

        client.performMultiGesture();
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
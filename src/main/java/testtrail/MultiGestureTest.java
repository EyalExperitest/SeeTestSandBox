package testtrail;

/**
 * Created by eyal.neumann on 2/19/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;

import static org.testng.FileAssert.fail;

/**
 *
 */
public class MultiGestureTest {
    private String host = "localhost";
    private int port = 8889;
    //private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project17";
    protected Client client = null;

    @Before
    public void setUp(){
        client = new Client(host, port, true);
        //client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "MultiGestureTest");
    }

    @Test
    public void testMultiGestureTest() throws Exception {


        // Set Device Name here :
        client.setDevice("ios_app:navot’s iPad");

        /*******************************
         *
         *
         *  The app "Test MultiTouch needs to be downloaded first from App Store.
         *
         *
        ********************************/

        int waitTime = 2000;
        client.launch("com.ferretking.testtouch", true, false);
        client.startStepsGroup("MultiGestureTest");
        client.startVideoRecord();
        client.startMultiGesture("MultiGestureTest");
        client.multiTouchDownCoordinate(1200, 1600, 0);

        client.multiWait(waitTime, 0);
        client.multiTouchMoveCoordinate(250, 600, 0);
        client.multiWait(waitTime, 0);
        client.multiTouchUp(0);
        client.multiTouchDownCoordinate(1170, 600, 1);
        client.multiWait(waitTime, 1);
        client.multiTouchMoveCoordinate(140, 600, 1);
        client.multiWait(waitTime, 1);
        client.multiTouchUp(1);
        client.multiTouchDownCoordinate(270, 1500, 2);
        client.multiWait(waitTime, 2);
        client.multiTouchMoveCoordinate(240, 400, 2);
        client.multiWait(waitTime, 2);
        client.multiTouchUp(2);
        client.performMultiGesture();
        client.stopVideoRecord();
        client.stopStepsGroup();

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

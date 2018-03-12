package testtrail;

import com.experitest.client.*;
import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

public class BatteryMonitor {
	private static final String DEVICE_NAME = "ios_app:navot’s iPad";
	private String host = "localhost";
	private int port = 8889;
	//private String 	projectBaseDirectory = new File(System.getProperty("user.dir"), "projectfolder").getAbsolutePath();
	protected Client client = null;

	@Before
	public void setUp() {
		client = new Client(host, port, true);
		//client.setProjectBaseDirectory(projectBaseDirectory);
		client.setReporter("xml", "monitor", "commandsTest");
		client.setDevice(DEVICE_NAME);
		//client.launch("com.experitest.ExperiBank", true, true);
	}

	@Test
	public void battery() {
		String file = "C:\\Users\\eyal.neumann\\monitor data\\monitor.csv";
		client.startMonitor("");
		client.deviceAction("Home");
		client.launch("com.apple.Maps", true, true);
		client.startMonitor("com.apple.Maps:battery");
		// pinch
		client.startMultiGesture("test");
		// finger 0
		client.multiTouchDownCoordinate(200, 600, 0);
		client.multiTouchMoveCoordinate(250, 600, 0);
		client.multiTouchUp(0);

		// finger 1
		client.multiTouchDownCoordinate(170, 600, 1);
		client.multiTouchMoveCoordinate(140, 600, 1);
		client.multiTouchUp(1);

		client.performMultiGesture();
		//swipe
		client.startMultiGesture("test");
		// finger 0
		client.multiTouchDownCoordinate(200, 600, 0);
		client.multiTouchMoveCoordinate(250, 600, 0);
		client.multiTouchUp(0);
		client.performMultiGesture();

		// swipe
		client.startMultiGesture("test");
		// finger 0
		client.multiTouchDownCoordinate(200, 600, 0);
		client.multiTouchMoveCoordinate(250, 600, 0);
		client.multiTouchUp(0);
		client.performMultiGesture();
		client.getMonitorsData(file);

		client.launch("com.apple.AppStore", true, true);
		client.startMonitor("com.apple.AppStore:battery");
		//client.swipe("Down", 10, 2000);
		client.click("Native", "xpath=//*[@text='Search']", 0, 1);
		client.click("Native", "xpath=//*[@text='Top Charts']", 0, 1);
		client.click("Native", "xpath=//*[@text='Search']", 0, 1);
		client.click("Native", "xpath=//*[@text='Top Charts']", 0, 1);
		client.click("Native", "xpath=//*[@text='Search']", 0, 1);
		client.click("Native", "xpath=//*[@text='Top Charts']", 0, 1);
		client.click("Native", "xpath=//*[@text='Search']", 0, 1);
		client.getMonitorsData(file);
	}

	@After
	public void tearDown() {
		// Generates a report of the test case.
		// For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
		client.generateReport(false);
		// Releases the client so that other clients can approach the agent in the near future.
		client.releaseClient();
	}
}
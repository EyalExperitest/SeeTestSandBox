package testtrail;

import com.experitest.client.*;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;
/**
 *
 */
public class SetAuthTest {
	private static final String DEVICE_NAME ="ios_app:navot’s iPad";
	private String host = "localhost";
	private int port = 8889;
	// private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
	private String 	projectBaseDirectory = new File(System.getProperty("user.dir"), "projectfolder").getAbsolutePath();;

	private String[] replies={
			"Success" ,
			"StopMock",
			"AuthenticationFailedError",
			"UserCancelError",
			"UserFallbackError",
			"SystemCancelError",
			"PasscodeNotSetError",
			"TouchIDNotAvailableError",
			"TouchIDNotEnrolledError",
			"TouchIDLockoutError",
			"AppCancelError",
	"InvalidContextError"};
	// private String[] uIReplays = new String[replays.length];
	private String[] expectedReplies={
			"Success",
			"StopMock have No Fixed Replay",
			"Error Code: -1. Application retry limit exceeded.",
			"Error Code: -2. Canceled by user.",
			"Error Code: -3. Fallback authentication mechanism selected.",
			"Error Code: -4. UI canceled by system.",
			"Error Code: -5. Passcode not set.",
			"Error Code: -6. Biometry is not available on this device.",
			"Error Code: -7. No fingers are enrolled with Touch ID.",
			"Error Code: -8. Biometry is locked out.",
			"Error Code: -9. kLAErrorAppCancel",
	"Error Code: -10. kLAErrorInvalidContext"};
	private int[] waitTimes = {0,1000,2000,5000,7000,8000,10000,13000,15000,17000,20000};
	private long[] measuredWaitTimes = new long[waitTimes.length];

	protected Client client = null;

	@Before
	public void setUp(){
		client = new Client(host, port, true);
		client.setProjectBaseDirectory(projectBaseDirectory);
		client.setReporter("xml", "reports", "SetAuthTest");
	}

	@Test
	public void testSetAuthTest_Replies(){
		client.setDevice(DEVICE_NAME);
		client.launch("com.experitest.UICatalog", true, true);
		client.elementListSelect("", "text=Authentication", 0, true);
		String uIReply;
		String reply;
		for (int i=0;i<replies.length;i++){
			reply=replies[i];
			client.startStepsGroup(reply);
			client.setAuthenticationReply(reply, 0);
			client.click("NATIVE", "text=Request Touch ID Authentication", 0, 1);
			uIReply = client.elementGetText("NATIVE", "xpath=//*[@class='UIView' and @height>0 and ./*[@text='TouchID']]//*[@class='UILabel']", 1);
			//System.out.println(uIReplay);
			if (!reply.equals("StopMock")){
				boolean correctResult=uIReply.equals(expectedReplies[i]);
				if (correctResult){
					client.report("Correct Result", true);	
				}
				else{
					client.report("Incorrect Result, should be "+expectedReplies[i], false);	
				}
				assertTrue(correctResult);
			}
			else{
				client.report("Result for StopMock is : "+uIReply, true);	

			}
			client.click("NATIVE", "xpath=//*[@class='_UIAlertControllerActionView']/*/*", 0, 1);
			client.stopStepsGroup();   		

		}
	}

	@Test
	public void testSetAuthTest_Times(){
		client.setDevice(DEVICE_NAME);
		client.launch("com.experitest.UICatalog", true, true);
		client.elementListSelect("", "text=Authentication", 0, true);	
		long time0;
		long measuredWaitTime;

		for (int i=0;i<waitTimes.length;i++){
			client.startStepsGroup("Wait "+(waitTimes[i]/1000)+" Seconds");
			client.setAuthenticationReply("Success", waitTimes[i]);
			client.click("NATIVE", "text=Request Touch ID Authentication", 0, 1);
			time0=System.currentTimeMillis();
			client.waitForElement("NATIVE", "xpath=//*[@class='_UIAlertControllerActionView']/*/*", 0, 100000);
			measuredWaitTime =System.currentTimeMillis()-time0;
			boolean correctResult=(Math.abs(measuredWaitTime-waitTimes[i])<2000);
			if (correctResult){
				client.report("Correct Result", true);	
			}
			else{
				client.report("Incorrect Result, should be less than "+(waitTimes[i]+1000), false);	
			}
			assertTrue(correctResult);
			System.out.println("Expected Wait Time   (in mSeconds) :"+waitTimes[i]);
			System.out.println("Measured Waited Time (in mSeconds) :"+measuredWaitTime);
			client.click("NATIVE", "xpath=//*[@class='_UIAlertControllerActionView']/*/*", 0, 1);


		}


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

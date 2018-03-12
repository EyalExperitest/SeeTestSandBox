package testtrail;
import com.experitest.client.*;
import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

public class MonitorCommands {
	private static final String DEVICE_NAME ="ios_app:navot’s iPad";
	private String host = "localhost";
	private int port = 8889;
	//private String 	projectBaseDirectory = new File(System.getProperty("user.dir"), "projectfolder").getAbsolutePath();
	protected Client client = null;

	@Before
	public void setUp(){
		client = new Client(host, port, true);
		//client.setProjectBaseDirectory(projectBaseDirectory);
		client.setReporter("xml", "monitor", "commandsTest");
		client.setDevice(DEVICE_NAME);
		client.launch("com.experitest.ExperiBank", true, true);
	}
	@Test
	public void getCPUTest()
	{
		String res = "";
		try{
			res = client.getCounter("cpu");
		}catch(Exception e)
		{
			if (res.equals(""))
			{
				client.report("get CPU Returned null", false);
				assertTrue(false);
			}
		}	
	}
	@Test
	public void getMemTest()
	{
		String res = "";
		try{
			res = client.getCounter("memory");
		}catch(Exception e)
		{
			if (res.equals(""))
			{
				client.report("get Memory Returned null", false);
				assertTrue(false);
			}
		}	
	}
	@Test
	public void getBatteryTest()
	{
		String res = "";
		try{
			res = 	client.getCounter("battery");
		}catch(Exception e)
		{
			if (res.equals(""))
			{
				client.report("get Battery Returned null", false);
				assertTrue(false);
			}
		}	
	}
	@Test
	public void dataTest(){
		String res;
        res = client.getMonitorsData("/users/mymac/Documents");
		if(res.equals(""))
		{
			client.report("file is empty", false);
		}
        client.elementSendText("NATIVE", "accessibilityLabel=Username", 0, "company");
	    client.elementSendText("NATIVE", "accessibilityLabel=Password", 0, "company");
	    client.startMonitor("");
	    client.click("NATIVE", "text=Login", 0, 1);
	    res = client.getMonitorsData("/users/mymac/Documents");	
		if(res.equals(""))
		{
			client.report("file is empty", false);
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

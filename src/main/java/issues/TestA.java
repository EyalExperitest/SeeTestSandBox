
import com.experitest.client.*;
import org.junit.*;

public class TestA {
  private String host = "localhost";
  private int port = 8889;
  private String projectBaseDirectory =  System.getProperty("user.dir") + "\\";
  protected Client client = null;

  @Before
  public void setUp(){
      client = new Client(host, port, true);
      client.setProjectBaseDirectory(projectBaseDirectory);
      client.setReporter("xml", projectBaseDirectory + "\\streports\\", "Untitled");
      
      
  }

  public void addListenerWithInvalidxPath() {
      client.addMobileListener("NATIVE", "xpath=//*[@text='Incoming call'",
	      new MobileListener() {
	          @Override
	          public boolean recover(String type, String xpath) {
	              client.click("NATIVE", "xpath=//*[@text='Decline']", 0, 1);
	              return true;
	          }
	      }
	  );
  }
  
  
  
  @Test
  public void test1_activeInvalidXPathListener(){
      System.out.println("Test 1 - When a listener with invalid xPath is active");
      addListenerWithInvalidxPath();
      client.setDevice("adb:SGP611");
      client.deviceAction("Unlock");
      client.deviceAction("Home");
      // ALL will be "Found: false. Invalid XPath expression"
      client.waitForElement("NATIVE", "xpath=//*[@text='Apps']", 0, 10000);
      client.waitForElement("NATIVE", "xpath=//*[@text='Settings']", 0, 10000);
      client.isElementFound("NATIVE", "xpath=//*[@text='Phone']", 0);
      client.isElementFound("NATIVE", "xpath=//*[@text='Messages']", 0);
  }
  
  @Test
  public void test2_noActiveInvalidXPathListener(){  
      System.out.println("Test 2 - no listeners");
      client.setDevice("adb:SGP611");
      client.deviceAction("Unlock");
      client.deviceAction("Home");
      client.waitForElement("NATIVE", "xpath=//*[@text='Apps']", 0, 10000);
      client.waitForElement("NATIVE", "xpath=//*[@text='Settings']", 0, 10000);
      client.isElementFound("NATIVE", "xpath=//*[@text='Phone']", 0);
      client.isElementFound("NATIVE", "xpath=//*[@text='Messages']", 0);
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

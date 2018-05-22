package issues;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
 *
 */
public class SA22846InstallEriBankOFromURL {
    private String host = "localhost";
    private int port = 8889;
//  private String accessKey = "eyJ4cC51IjoyMTc2LCJ4cC5wIjoyLCJ4cC5tIjoiTVRRNU9USXpPRE0zTkRrek9RIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjQ2NzIxMzI4NTQsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.na315fDIXdpqkRVem7V1vbiHnST4I7Ee3vYMIpBb11A";
    private String accessKey = "eyJ4cC51Ijo3LCJ4cC5wIjoyLCJ4cC5tIjoiTVRVd09UVXlPVEl3TWpreE53IiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4MjQ4OTIwNzAsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.icG-vUduTupsyGxjkOqK82j3yEa5f6311NZo6gHnmDM";
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project21";
    protected Client client = null;
    protected GridClient grid = null;

    @BeforeMethod
    public void setUp(){
        // In case your user is assign to a single project you can provide an empty string,
        // otherwise please specify the project name
//        grid = new GridClient(accessKey, "qacloud.experitest.com",443, true);
        grid = new GridClient(accessKey, "mastercloud",80, false);
        //client = grid.lockDeviceForExecution("SA22846InstallEriBankOFromURL","@serialnumber='7dab622e4213cc503ccca3fa4a213645d7f9e5da'", 10, 50000);
        client = grid.lockDeviceForExecution("SA22846InstallEriBankOFromURL","@serialnumber='b5e53830a00a854f3c820869a3feb2f38b4fc7d8'", 10, 50000);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "SA22846InstallEriBankOFromURL");
    }

    @Test(groups = {"seetest"})
    public void testSA22846InstallEriBankOFromURL(){
        // This command "setDevice" is not applicable for GRID execution
        if(client.uninstall("com.experitest.ExperiBankO")){
            // If statement
        }
        // Grid cannot install local applications. please upload the application to cloud or
        // provide the absolute path to the application package
        // https://docs.experitest.com/display/SC/Import+Applications
        if(client.install("http://192.168.1.213:8090/guestAuth/repository/download/bt4/.lastSuccessful/eribanko.ipa", true, false)){
            // If statement
        }
        client.launch("com.experitest.ExperiBankO", true, false);
        String str0 = client.getCurrentApplicationName();
        if(client.uninstall("com.experitest.ExperiBankO")){
            // If statement
        }
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

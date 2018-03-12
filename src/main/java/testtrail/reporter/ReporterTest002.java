package testtrail.reporter;

/**
 * Created by eyal.neumann on 3/11/2018.
 */
//package <set your test package>;

import com.experitest.client.Client;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 */
public class ReporterTest002 {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project17";
    protected Client client = null;

    @BeforeMethod
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports\\reports001", "ReporterTest002");
        client.setShowImageInReport(true);
    }

    @Test(groups = {"seetest"})
    public void testReporterTest001(){
        client.setDevice("ios_app:iPad(3)");
        String pictureURL="https://www.google.co.il/imgres?imgurl=https%3A%2F%2Fdocs.experitest.com%2Fdownload%2Fattachments%2F2688841%2FALLDOC%3Fversion%3D3%26modificationDate%3D1437923983010%26api%3Dv2&imgrefurl=https%3A%2F%2Fdocs.experitest.com%2F&docid=W5xzje-WuSt_rM&tbnid=gm7VdtTL2ZtbSM%3A&vet=10ahUKEwiKrInq9ePZAhWKlSwKHe2OC1oQMwg-KAUwBQ..i&w=200&h=200&bih=637&biw=1366&q=Experitest&ved=0ahUKEwiKrInq9ePZAhWKlSwKHe2OC1oQMwg-KAUwBQ&iact=mrc&uact=8";
        String pictureURL2 = "https://pp.vk.me/c11102/u167537414/-6/w_8fbae648.jpg";
        //"C:\Users\eyal.neumann\Pictures\login.png"
        //String capture = client.capture("Capture Image");
        //client.report(capture,"Captured Image",true);
        client.report(pictureURL,"URL Image",true);
        client.report("C:\\Users\\eyal.neumann\\Pictures\\login.png","Login Picture",true);



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

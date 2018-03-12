package testtrail;

/**
 * Created by eyal.neumann on 2/19/2018.
 */
//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class SetLocationIOS {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\eyal.neumann\\workspace\\project17";
    protected Client client = null;
    private static String setReporter;
    @Before
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        setReporter = client.setReporter("xml", "reports", "SetLocationIOS");
        System.out.println("setReport:"+setReporter);
    }

    @Test
    public void testSetLocationIOS(){
        // Xpaths in use :
        System.out.println("setReport:"+setReporter);
        String trackingButton = "xpath=//*[@text='Tracking']";
        String myLocation = "xpath=//*[@name='My Location']";
        String close = "xpath=//*[@text='Close']";
        String longitudeAndLatitude = "xpath=//*[contains(@text,'Latitude')]";
        //****************
        int timeout = 100000;
        double marginOfError = 0.00001;
        String setLatitudeString = "31.290174";
        String setLongitudeString = "33.863896";


        String locationString;
        String messuredLatitudeString;
        String messuredLongitudeString;
        String[] splitLocationString;
        double latitudeDifference;
        double longitudeDifference;










        client.setDevice("ios_app:navot’s iPad");
        String logPath = setReporter + "\\devicelog.log";
        System.out.println("Log Path :"+logPath);
        client.startLoggingDevice(logPath);

        client.launch("com.apple.Maps", true, true);
        client.clearLocation();
        client.click("NATIVE", trackingButton, 0, 1);
        assertTrue(client.waitForElementToVanish("NATIVE","xpath=//*[@value='Determining Location…']",0,timeout));

        client.waitForElement("NATIVE", myLocation, 0, timeout);

        client.click("NATIVE", myLocation, 0, 1);
        locationString = client.elementGetText("NATIVE", longitudeAndLatitude, 0);
        System.out.println(" After Clear Location :"+locationString);
        client.click("NATIVE", close, 0, 1);
        splitLocationString = locationString.replaceAll(" ", "").split(",");

        messuredLatitudeString = splitLocationString[2];
        messuredLongitudeString = splitLocationString[3];

        Double messuredStartLatitude =Double.parseDouble(messuredLatitudeString);
        Double messuredStartLongitude =Double.parseDouble(messuredLongitudeString);

        //**************************************

        client.setLocation(setLatitudeString, setLongitudeString);
        client.click("NATIVE", trackingButton, 0, 1);
        client.waitForElement("NATIVE", myLocation, 0, timeout);
        client.click("NATIVE", myLocation, 0, 1);
        locationString = client.elementGetText("NATIVE", longitudeAndLatitude, 0);
        client.click("NATIVE", close, 0, 1);
        System.out.println(" After Set Location :"+locationString);
        splitLocationString = locationString.replaceAll(" ", "").split(",");
        
        messuredLatitudeString = splitLocationString[2];
        messuredLongitudeString = splitLocationString[3];


        Double messuredLatitude =Double.parseDouble(messuredLatitudeString);
        Double messuredLongitude =Double.parseDouble(messuredLongitudeString);

        Double setLatitude= Double.parseDouble(setLatitudeString);
        Double setLongitude= Double.parseDouble(setLongitudeString);

        latitudeDifference = Math.abs(setLatitude - messuredLatitude);
        longitudeDifference = Math.abs(setLongitude - messuredLongitude);
        assertTrue((latitudeDifference*latitudeDifference+longitudeDifference*longitudeDifference)< marginOfError);

        //********************************************

        client.clearLocation();
        client.click("NATIVE", trackingButton, 0, 1);
        client.waitForElement("NATIVE", myLocation, 0, timeout);
        client.click("NATIVE", myLocation, 0, 1);
        locationString = client.elementGetText("NATIVE", longitudeAndLatitude, 0);
        System.out.println(" After Clear Location :"+locationString);
        client.click("NATIVE", close, 0, 1);
        splitLocationString = locationString.replaceAll(" ", "").split(",");

        messuredLatitudeString = splitLocationString[2];
        messuredLongitudeString = splitLocationString[3];

        Double messuredEndLatitude =Double.parseDouble(messuredLatitudeString);
        Double messuredEndLongitude =Double.parseDouble(messuredLongitudeString);


        latitudeDifference = Math.abs(setLatitude - messuredLatitude);
        longitudeDifference = Math.abs(setLongitude - messuredLongitude);
        assertTrue((latitudeDifference*latitudeDifference+longitudeDifference*longitudeDifference)< marginOfError);
        client.stopLoggingDevice();





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

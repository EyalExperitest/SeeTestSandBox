package testtrail;

import com.experitest.client.Client;
import org.junit.*;
import org.junit.Test; 


/**
 * Created by udi.valer on 9/27/2016.
 */
public class MultiTabTest2 {

    protected Client client = null;
    protected String iosVersion = "";
    protected String iosModel = "";
    protected boolean iphone = true; // Argument to understand if current test run oon device or on iphone
    protected String WebPage = "";

    @Before
    public void setUp(){

        client = new Client("localhost", 8889, true);
        client.setProjectBaseDirectory(System.getProperty("user.dir"));
        client.setReporter("xml", "reports", "Multi Tab Test");//
//        client.waitForDevice("@serialnumber='825a2e3c7bffb22a936f1b5c09a5a0612dd597cf'" , 100000);
//        client.waitForDevice("@model='ipad pro'" , 100000);
        client.waitForDevice("@os='ios'", 100000);
        //client.setDevice("ios_app:iPad air 2"); //If you want to use set device you are welcome
        iosVersion = client.getDeviceProperty("device.version");
        iosModel = client.getDeviceProperty("device.model");
        if(iosModel.toLowerCase().contains("ipad"))
            iphone = false;
        System.out.println("Finish setUp");
    }

    /** This test open the same web page 3 times, switch between them and in each page execute few different command to be sure the right dump is taken for each page.
     Passing between pages from switch application */
    @Test
    public void sameWindowMultipleTabs(){
    	//client.setReporter("xml", "reports", "Multi Tab Test");
        System.out.println("*********************************");
        System.out.println("Current test run on ios " + iosModel + " With version: " + iosVersion);
        System.out.println("*********************************");
        try {
        	client.hybridClearCache(true , true);
		} catch (Exception e) {
			e.printStackTrace();
		} 
        client.launch("com.apple.mobilesafari" , true , false);

        if(Double.valueOf(iosVersion) >= 9){
            String pagesXpath = "xpath=//*[@knownSuperClass='UIScrollView']";
            testForTheCorrectVersion(pagesXpath);

        }
        else{   // Version is lower than 9
            String pagesXpath = "xpath=(//*[@class='UIAScrollView'])[1]";
            testForTheCorrectVersion(pagesXpath);
        }
        System.out.println("Finish sameWindowMultipleTabs");
    }

    @After
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future.
        client.releaseClient();
    }

    private void testForTheCorrectVersion(String pagesXpath){

        int numberOfPagesToOpen = 3;
        int numberOfOpenPages = 0;
        
        String Pages = "";
        String newPageRecognition = "";
        String searchButtonGoogle = "";
       
       

        if(iphone) {
        	Pages = "xpath=//*[@text='Pages']";
            newPageRecognition = "xpath=//*[@text='New page']";
            searchButtonGoogle = "xpath=//*[@text='  ' and @nodeName='BUTTON']";
            System.out.println("Is iphone!!!");
            WebPage = "xpath=(//*[@class='UIAScrollView']/*[@text='Google'][1])";
        }
        else {
        	Pages = "xpath=//*[@text='Pages']";
            newPageRecognition = "xpath=//*[@text='New tab']";
            pagesXpath = "xpath=//*[@class='UIAView' and @knownSuperClass='UIView' and @enabled='true' and boolean(@text)]/..";
            searchButtonGoogle = "xpath=//*[@text='  ' and @nodeName='BUTTON']";
            WebPage = "xpath=((//*[@class='UIAView' and ./parent::*[@class='UIAView' and ./parent::*[@text='BrowserWindow']]]/*[@class='UIAView'])[2]/*[@text='Google'])[1]";
            WebPage = "xpath=//*[@text='Google' and @class='UIAButton'][1]";
             System.out.println("Is Ipad!!!");
    }

        /** Loop for opening 3 same web pages - www.google.com */
        System.out.println("Loop for opening 3 same web pages - www.google.com ");
        while(numberOfOpenPages<numberOfPagesToOpen){
            //client.elementSendText("NATIVE", "xpath=//*[@accessibilityLabel='URL' and (@class='UIAView' or @class='UIAElement')]", 0, "www.google.com");
            client.elementSendText("NATIVE", "xpath=//*[@accessibilityLabel='URL']", 0, "www.google.com");
            client.sendText("{ENTER}");
            numberOfOpenPages++;
            if(numberOfOpenPages!=numberOfPagesToOpen){
	            try {
	            	client.click("NATIVE", Pages, 0, 1);
	    		} catch (Exception e) {
	    			Pages = "xpath=//*[@text='Tabs']";
	    			client.click("NATIVE",Pages, 0, 1);
	    		} 
	            try {
	            	client.click("NATIVE", newPageRecognition, 0, 1);
				} catch (Exception e) {
					newPageRecognition = "xpath=//*[@text='New tab']";
					client.click("NATIVE", newPageRecognition, 0, 1);
				}
            }  
        }

//        client.elementSendText("NATIVE", "xpath=//*[@accessibilityLabel='URL' and (@class='UIAView' or @class='UIAElement')]", 0, "www.google.com");
//        client.sendText("{ENTER}");
//        client.click("NATIVE", Pages, 0, 1);
        /////////////////////////////////////////////////////////////////////
        
        
        // Move to next page
        System.out.println("Moving page!!!!!!!!!!!!!!!!!!!!!!!!");
        client.sleep(10000);
        if( client.waitForElement("NATIVE", Pages, 0, 10000)){
	        System.out.println("Found!!!");
	        client.click("NATIVE",Pages,0,1);
	        ////////////////////////////////////////////////////////////
        }
        else{
        	System.out.println("not found!!!!!!!!");
        }
        if( client.waitForElement("NATIVE", WebPage, 0, 10000)){
	        System.out.println("Found!!!");
	        client.click("NATIVE",WebPage,0,1);////////////////////////////////////////////////////////////
        }
        else{
        	System.out.println("not found!!!!!!!!");
        }
        
        
        /* First page load Wikipedia */
        System.out.println(10);
        client.click("WEB", "xpath=//*[@name='q']", 0, 1);
        client.sendText("wikipedia");
        client.click("WEB", searchButtonGoogle, 0, 1);
        System.out.println("1111111111111111111111111111");
        String element = "xpath=//*[@text='הישאר ב-']";
        String wallaElement = "xpath=//*[@text='וואלה!']";

        if(client.isElementFound("WEB" , "xpath=//*[@text='Looking for results in English?']" , 0)){
            client.click("WEB" , element, 0 , 1);
        }
        client.waitForElement("WEB" , "xpath=//*[@text='Wikipedia']" , 0 , 100000);
        client.click("WEB" , "xpath=//*[@text='Wikipedia']" , 0 , 1);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        // Move to next page
        client.click("NATIVE", Pages, 0, 1);
        if( client.waitForElement("NATIVE", WebPage, 0, 10000)){
	        System.out.println("Found!!!");
	        client.click("NATIVE",WebPage,0,1);////////////////////////////////////////////////////////////
        }
        else{
        	System.out.println("not found!!!!!!!!");
        }
        

        /* Second page load apple */
        client.waitForElement("WEB", "xpath=//*[@name='q']", 0, 3000);
        client.click("WEB", "xpath=//*[@name='q']", 0, 1);
        client.sendText("apple");
        client.click("WEB", searchButtonGoogle, 0, 1);
        if(client.isElementFound("WEB" , "xpath=//*[@text='Looking for results in English?']" , 0)){
            client.click("WEB" , element, 0 , 1);
        }
        if(client.isElementFound("WEB" , "xpath=//*[@text='Would Like To Use CURRENT LOCATION']" , 0)){
            client.click("WEB" , "xpath=//*[@text='OK']" , 0 , 1);
            System.out.println("YES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        if(client.isElementFound("NATIVE" , "xpath=//*[@text='ג€�https://www.google.co.ilג€� Would Like To Use Your Current Location']" , 0)){
        	System.out.println("YES222222222222222222222!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        	try {
        		client.click("NATIVE" , "xpath=//*[@text='Allow']" , 0 , 1);
			} catch (Exception e) {
				client.click("NATIVE" , "xpath=//*[@text='OK']" , 0 , 1);
			}
        	
        }
        client.waitForElement("WEB" , "xpath=//*[@text='Apple']" , 0 , 100000);
        client.click("WEB" , "xpath=//*[@text='Apple']" , 0 , 1);

        // Move to next page
        client.click("NATIVE", Pages, 0, 1);
        System.out.println("aHERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        client.waitForElement("NATIVE", WebPage, 0, 10000);
        client.click("NATIVE" ,WebPage ,0 , 1 );
        

        /* Third page load walla */
        client.waitForElement("WEB", "xpath=//*[@name='q']", 0, 3000);
        client.click("WEB", "xpath=//*[@name='q']", 0, 1);//id=lst-ib
        client.sendText("walla");
        client.click("WEB", searchButtonGoogle, 0, 1);
        if(client.isElementFound("WEB" , "xpath=//*[@text='Looking for results in English?']" , 0)){
            client.click("WEB" , element, 0 , 1);
        }
        client.waitForElement("WEB" , wallaElement, 0 , 100000);
        client.click("WEB" , wallaElement, 0 , 1);


    }

}




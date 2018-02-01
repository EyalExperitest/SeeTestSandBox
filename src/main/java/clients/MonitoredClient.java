package clients;

import com.experitest.client.Client;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

/**
 * Created by eyal.neumann on 1/10/2018.
 */
public class MonitoredClient extends ExClient {

    private static SortedMap<String,List<Double>> resultsMap;
    private static boolean monitorFlag =false;
    private long start;
    private long end;


    public static void startClientMonitor(){
        resultsMap = new TreeMap<String, List<Double>>();
        monitorFlag =true;
    }
    public static void printClientMonitor(){

        Set<String> commands = resultsMap.keySet();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        for(String command :commands){
            if (!command.equals("Total")) {
                double sum =0.0;
                double counter = 0.0;
                List<Double> durations = resultsMap.get(command);
                for (Double duration :durations){
                    sum+=duration;
                    counter+=1.0;
                }
                Double avg=0.0;
                if (counter!=0) {
                    avg =new Double(sum/counter);
                }
                System.out.println(command+" : "+decimalFormat.format(avg) +" [sec]" );
            }
        }
/*        double sum =0.0;
        double counter = 0.0;

        List<Double> durations = resultsMap.get("Total");
        for (Double duration :durations){
            sum+=duration;
            counter+=1.0;
        }
        Double avg=0.0;
        if (counter!=0) {
            avg =new Double(sum/counter);
        }
        System.out.println("Average Test Time  : "+decimalFormat.format(avg) +" [sec]" );*/



    }








    public MonitoredClient(String host, int port, boolean useSessionID) {
        super(host, port, useSessionID);
    }


    @Override
    public void click(String zone, String element, int index, int clickCount) {
        beforeAction();
        super.click(zone, element, index, clickCount);
        afterAction("click");
    }

    @Override
    public void elementSendText(String zone, String element, int index, String text) {
        beforeAction();
        super.elementSendText(zone, element, index, text);
        afterAction("elementSendText");

    }


    @Override
    public boolean waitForElement(String zone, String element, int index, int timeout) {
        beforeAction();
        boolean waitForElement = super.waitForElement(zone, element, index, timeout);
        afterAction("waitForElement");
        return waitForElement;
    }

    private void afterAction(String methodName) {
        end = System.currentTimeMillis();
        long time= end-start;
        double timeDouble = (double)time/1000.0;
        if(!MonitoredClient.resultsMap.containsKey(methodName)){
            resultsMap.put(methodName,new LinkedList<Double>());
        }
        MonitoredClient.resultsMap.get(methodName).add(timeDouble);
    }

    private void beforeAction() {
        start = System.currentTimeMillis();
    }
}

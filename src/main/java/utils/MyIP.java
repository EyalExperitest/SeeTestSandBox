package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class MyIP {

	public MyIP() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)throws MalformedURLException, IOException {


		
		System.out.println("My IP:"+getMyIP());
		



	}
	
	public static String getMyIP(){
		String ipReturn="";
		try {
		    Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		    while (interfaces.hasMoreElements()) {
		        NetworkInterface iface = interfaces.nextElement();
		        if (iface.isLoopback() || !iface.isUp() || iface.isVirtual() || iface.isPointToPoint())
		            continue;

		        Enumeration<InetAddress> addresses = iface.getInetAddresses();
		        while(addresses.hasMoreElements()) {
		            InetAddress addr = addresses.nextElement();

		            final String ip = addr.getHostAddress();
		            if(Inet4Address.class == addr.getClass()) {
		            	ipReturn= ip;
		            	break;
		            }
		        }
		    }
		} catch (SocketException e) {
		    throw new RuntimeException(e);
		}
		
		return ipReturn;
		
	}
	

}

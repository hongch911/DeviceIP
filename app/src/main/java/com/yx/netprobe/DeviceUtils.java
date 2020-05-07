package com.yx.netprobe;

import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Locale;

public class DeviceUtils {

    public static String getIPAddress() {
        StringBuilder stringBuilder = new StringBuilder("Get Java local IP!\n");
        try {
            for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements(); ) {
                NetworkInterface network = e.nextElement();

                for (Enumeration<InetAddress> enumeration = network.getInetAddresses(); enumeration.hasMoreElements(); ) {
                    InetAddress inetAddress = enumeration.nextElement();
                    Log.i("DeviceUtils", String.format("name:%s, address %s", network.getName(), inetAddress.toString()));
                    stringBuilder.append(String.format(Locale.ENGLISH, "address: [%s  %s]\n",network.getName(),inetAddress.getHostAddress()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String getIPAddress(String netInterface) {
        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                //Log.d("DeviceUtils", "getIpAddress,interface:"+ni.getName());
                if (ni.getName().equals(netInterface)) {
                    Enumeration<InetAddress> ias = ni.getInetAddresses();
                    while (ias.hasMoreElements()) {
                        ia = ias.nextElement();
                        hostIp = ia.getHostAddress();
                        Log.i("DeviceUtils", String.format("name:%s, hostIp %s", netInterface, hostIp));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hostIp;
    }
}

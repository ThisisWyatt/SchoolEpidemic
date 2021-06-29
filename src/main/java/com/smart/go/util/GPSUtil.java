package com.smart.go.util;


import com.smart.go.content.CountPeopleMessage;
import com.smart.go.content.PathInfo;

/**
 * 将百度坐标转换为高德地图坐标
 */
public class GPSUtil {
    static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    public static void bd_decryptNum(CountPeopleMessage c) {
        double bd_lat = Double.parseDouble(c.getLat());
        double bd_lng = Double.parseDouble(c.getLng());
        double x = bd_lng - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double gg_lng = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);

        c.setLat(String.valueOf(gg_lat));
        c.setLng(String.valueOf(gg_lng));
    }

    public static void forPathInfo(PathInfo c) {
        double bd_lat = Double.parseDouble(c.getLat());
        double bd_lng = Double.parseDouble(c.getLng());
        double x = bd_lng - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double gg_lng = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);

        c.setLat(String.valueOf(gg_lat));
        c.setLng(String.valueOf(gg_lng));

    }
}

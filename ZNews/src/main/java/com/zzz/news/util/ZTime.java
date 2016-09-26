package com.zzz.news.util;

/**
 * @创建者 zlf
 * @创建时间 2016/9/1 14:21
 */
public class ZTime {

    public static String getTime(long millisecond){
        long currentTime = System.currentTimeMillis();
        long dataTime = (currentTime - millisecond)/1000;//秒
        if (dataTime<60){
            return "刚刚";
        }else if (dataTime<60*60){
            int minute = (int) (dataTime/60);
            return minute+"分钟前";
        }else if(dataTime<60*60*24){
            int hour = (int) (dataTime/60/60);
            return hour +"小时前";
        }else {
            int day = (int) (dataTime/60/60/24);
            return  day +"天前";
        }

    }
}

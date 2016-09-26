package com.zzz.news.model.bean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:02
 */
public class CalendarBean {

    public CalendarBean(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private int year;
    private int month;
    private int day;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}

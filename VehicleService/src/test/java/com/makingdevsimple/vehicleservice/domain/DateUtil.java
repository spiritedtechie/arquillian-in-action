package com.makingdevsimple.vehicleservice.domain;

import java.util.Date;

import org.joda.time.DateTime;

public class DateUtil {

    public static Date dateWithYearMonthAndDay(final int year, final int month, final int day) {
        return new DateTime().withYear(year).withMonthOfYear(month).withDayOfMonth(day).toDate();
    }

    public static Date dateForYear(final int year) {
        return new DateTime().withYear(year).toDate();
    }

}

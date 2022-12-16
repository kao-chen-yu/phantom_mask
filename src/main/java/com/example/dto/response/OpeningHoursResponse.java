package com.example.dto.response;

import org.joda.time.DateTime;

public class OpeningHoursResponse {

    public enum DAY {
        TIME, Mon, Tue, Wed, Thur, Fri, Sat, Sun
    }

    public DAY day;
    public Integer from;
    public Integer to;
    public boolean isAllDay = false;

    public OpeningHoursResponse(DAY day, Integer from, Integer to) {
        this.day = day;
        this.from = from; // format time using 800 for 8:00am or 2300 for 23:00
        this.to = to;
    }

    @Override
    public String toString() {
        return "OpeningHours [day=" + day + ", from=" + from + ", to=" + to + ", isAllDay=" + isAllDay + "]";
    }

    public OpeningHoursResponse() {

    }

    public void isOpenx(DateTime start) {

    }

    public boolean isOpen(int open) {

        if (isAllDay)
            return true;

        if (open > from && open <= to) {
            return true;
        } else {
            return false;
        }

    }
}
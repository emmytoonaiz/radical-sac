/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radicalsac.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author emmanuel.idoko
 */
public class Util {

    /**
     * get the number of days between two dates
     *
     * @param date1 the first date
     * @param date2 the second date
     * @return
     */
    public long returnDateDiffInDays(Date date1, Date date2) {
        LocalDate ld1 = new Date(date1.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ld2 = new Date(date2.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long days = ChronoUnit.DAYS.between(ld1, ld2);
        return days;
    }

    /**
     * adds the number of days to the given date
     *
     * @param dateToIncrease the date to increase
     * @param numberOfDaysToAdd the number of days to add to the date
     * @return the increased date
     */
    public String addNumberOfDaysToDate(String dateToIncrease, long numberOfDaysToAdd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String newDate = "";
        try {
            //Setting the date to the given date
            c.setTime(sdf.parse(dateToIncrease));
            //Number of Days to add
            c.add(Calendar.DAY_OF_MONTH, (int) numberOfDaysToAdd);
            //Date after adding the days to the given date
            newDate = sdf.format(c.getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return newDate;
    }

}

package projecteulersolutions;

import java.time.Month;
import java.util.Date;
import java.util.ArrayList;
import java.time.YearMonth;

/*
The goal of problem 19 is to find the number of Sundays that fall on the 
first of a month during the twentieth century. (1 Jan 1901 to 31 Dec 2000)
 */
public class Problem0019 extends Problem {

    @Override
    public void printSolution() {
        ArrayList<Date> addDates = new ArrayList<>();
        
        for(int year = 1901; year <= 2000; year++) {
            for(int month = 1; month <= 12; month++) {
                int days = YearMonth.of(year, month).lengthOfMonth();
                for(int day = 1; day <= days; day++) {
                    addDates.add(new Date(year, month, day));
                    System.out.println(year + " " + month + " " + day);
                }
            }
        }
        int count = 0;
        for(Date d : addDates) {
            if(d.getDay() == 1 && d.getDate() == 1) {
                count++;
            }
        }
        
        System.out.println("There are " + count + " Sundays in the 20th century.");
    }
}

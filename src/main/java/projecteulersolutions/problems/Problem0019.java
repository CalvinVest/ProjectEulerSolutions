package main.java.projecteulersolutions.problems;

/*
The goal of problem 19 is to find the number of Sundays that fall on the 
first of a month during the twentieth century. (1 Jan 1901 to 31 Dec 2000)
 */
@SuppressWarnings("unused")
public class Problem0019 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int year = 1901;
        int day = 6; // first sunday in our search is 6 Jan 1901, so day = 6
        int count = 0; // count of all first-of-month sundays

        int[] firstDay = {1, 32, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
        int[] firstDayLeap = {1, 32, 61, 92, 122, 153, 183, 214, 245, 275, 306, 336};

        while (year < 2001) {
            if (year % 4 == 0) { // leap year
                if (day > 366) { // advanced a year
                    year++;
                    day -= 366;
                }
                for (int i : firstDayLeap) {
                    if (day == i) { // if the current day is the first of the month
                        count++;
                    }
                }
            } else { // not a leap year
                if (day > 365) { // advanced a year
                    year++;
                    day -= 365;
                }
                for (int i : firstDay) {
                    if (day == i) {
                        count++;
                    }
                }
            }
            day += 7; // advance a week
        }
        System.out.println("There are " + count + " Sundays in the 20th century.");
    }
}

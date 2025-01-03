
/**
 * Represents a Date
 * @author (Shimon Shriki)
 * @version (30/11/2022)
 */
public class Date
{
    // default values for day, month and year
    private int _day = 1; 
    private int _month = 1;
    private int _year = 2000;
    // four different options to last day in a month
    private final int _MAX_DAY_OP1 = 31;
    private final int _MAX_DAY_OP2 = 30;
    private final int _MAX_DAY_OP3 = 28;
    private final int _MAX_DAY_OP4 = 29;
    
    /**
     * Initialze a new Date object if the given date is valid, otherwise initialize the date 1/1/2000
     * @param day Day in the date
     * @param month Month in the date
     * @param year Year in the date
     */
    public Date(int day, int month, int year)
    {
        if (isValidDate(day, month, year))
        {   
            _day = day;
            _month = month; 
            _year = year;
        }
    }
    
    /**
     * Copy constructor
     * @param other Date to be copied
     */
    public Date(Date other)
    {
        _day = other._day;
        _month = other._month;
        _year = other._year;
    }
    
    /**
     * Checks that a date is a valid date
     * @param day Day in the date
     * @param month Month in the date
     * @param year Year in the date
     * @return True if the date is valid
     */
    private boolean isValidDate (int day, int month, int year)
    {
        if (year >= 1000 && year <= 9999) //check that the year value is 4 digid long
            if (month >= 1 && month <= 12) //check the month value
                if (day > 0) 
                {
                    //check if the day value is lower than 28, if so the date is valid
                    if (day <= _MAX_DAY_OP3) 
                        return true;
                        
                    //check if day value is highr from the last day in the given month
                    switch(month)
                    {
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12:
                             if (day <= _MAX_DAY_OP1) 
                                 return true; 
                            
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                             if (day <= _MAX_DAY_OP2)
                                 return true;
                            
                        case 2:
                            if (day == _MAX_DAY_OP4)
                                // check what is the last day of February in the given year
                                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
                                    return true;
                    }
                }
        
        return false;
    }
    
    /** 
     * Computes the day number since the beginning of the Christian counting of years
     * @param day Day in the date
     * @param month Month in the date
     * @param year Year in the date
     * @return Number of days that past since the beginning of the Christian counting of years
     */
    private int calculateDate (int day, int month, int year)
    {
        if (month < 3) 
        {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    } 
        
    /**
     * Returns the day
     * @return Day of date
     */
    public int getDay()
    {
        return _day;
    }
    
    /**
     * Returns the month
     * @return Month of date
     */
    public int getMonth()
    {
        return _month;
    }
    
    /**
     * Returns the year
     * @return Year of date
     */
    public int getYear()
    {
        return _year;
    }
    
    /**
     * Sets the day (only if date remains valid)
     * @param dayToSet New day value to be set
     */
    public void setDay(int dayToSet)
    {
         if (isValidDate(dayToSet, _month, _year))
             _day = dayToSet;
    }
    
    /**
     * Sets the month (only if date remains valid)
     * @param monthToSet New month value to be set
     */
    public void setMonth(int monthToSet)
    {
         if (isValidDate(_day, monthToSet, _year))
             _month = monthToSet;
    }
    
    /**
     * Sets the year (only if date remains valid)
     * @param yearToSet New year value to be set
     */
    public void setYear(int yearToSet)
    {
         if (isValidDate(_day, _month, yearToSet))
             _year = yearToSet;
    }
        
    /**
     * Checks if 2 dates are the same
     * @param other Date to compare this date to
     * @return True if the dates are the same, otherwise - false
     */
    public boolean equals(Date other)
    {
        return (_day == other._day && _month == other._month && _year == other._year);
    }
    
    /**
     * Checks if this date is before another date
     * @param other Date to compare this date to
     * @return True if this date is before the other date, otherwise - false
     */
    public boolean before (Date other)
    {
        if(_year < other._year)
            return true;
 
        if((_year > other._year))
            return false;
            
        if((_month < other._month))
            return true;
            
        if((_month > other._month))
            return false;

        if(_day < other._day)
            return true;
            
        return false;
    }
    
    /**
     * Checks if this date is after another date
     * @param other Date to compare this date to
     * @return True if this date is after the other date, otherwise - false
     */
    public boolean after (Date other)
    {
        return other.before(this);// checks that by using the "before" method on the other date.
    }
    
    /**
     * Calculates the difference in days between two dates
     * @param other Date to calculate the difference between this date and that date
     * @return Number of days between the dates (non negative value)
     */
    public int difference (Date other)
    {
        // compare the difference using the number of days that past since the beginning of counting and the 2 dates
        return Math.abs(((calculateDate(_day, _month, _year)) -
               (calculateDate(other._day, other._month, other._year))));
    }
    
    /**
     * Returns a String that represents this date
     * @return String that represents this date in the following format:
     * day (2 digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString ()
    {
        String day = String.valueOf(_day);
        String month = String.valueOf(_month);
        
        // checks if the day and month value are 2 digit long, if not add "0" before the value of its string representaion 
        if (_day < 10) 
            day = "0" + day;
        if (_month < 10)
            month = "0" + month;    
        
        return day + "/" + month + "/" + _year;
    }
    
    /**
     * Calculate the date of tomorrow
     * @return the date of tomorrow
     */
    public Date tomorrow()
    {
        // checks if the next day for the given date is in the given month
        if(isValidDate (_day + 1, _month, _year)) 
            return new Date(_day + 1, _month, _year);
        
        // if the next day to the given date is in the next month checks if this month is in 
        // the given year
        else
            if(isValidDate (1, _month + 1, _year)) 
                return new Date(1, _month + 1, _year);
            else
                return new Date(1, 1, _year + 1);
    }
}




/**
 * Represents a car rental
 * @author (Shimon Shriki)
 * @version (01/12/2022)
 */
public class Rent
{
    private String _name; // Customer name
    private Car _car;
    private Date _pickDate;
    private Date _returnDate;
    
    /**
     * Initialize a Rent object
     * The return date must be at least one day after the pickup date, 
     * otherwise set it to one day after the pick up date.
     * @param name Customer name
     * @param car Rented car
     * @param pick Rent pick up date
     * @param ret Rent return date
     */
    public Rent (String name, Car car, Date pick, Date ret)
    {
        _name = name;
        _car = new Car (car);
        _pickDate = new Date (pick);
        
        // check if the return date is after the pickup date
        // othewise assigns the day after the pickup day to the returnDate
        if (ret.after(_pickDate))
            _returnDate = new Date(ret);
        else
            _returnDate = _pickDate.tomorrow();
    }
    
    /**
     * Copy constructor
     * @other Rent object to be copied
     */
    public Rent (Rent other)
    {
        _name = other._name;
        _car = new Car (other._car);
        _pickDate = new Date (other._pickDate);
        _returnDate = new Date (other._returnDate);
    }
    
    /**
     * Returns the customer's name
     * @return Customer's name
     */
    public String getName ()
    {
        return _name;
    }
    
    /**
     * Returns the rented car
     * @return Car
     */
    public Car getCar ()
    {
        return new Car(_car);
    }
    
    /**
     * Returns the pickup date
     * @return Pickup date
     */
    public Date getPickDate ()
    {
        return new Date(_pickDate);
    }
    
    /**
     * Returns the return date
     * @return Return date
     */
    public Date getReturnDate ()
    {
        return new Date(_returnDate);
    }
    
    /**
     * Sets the customer name
     * @param name Customer name 
     */
    public void setName (String name)
    {
        _name = name;
    }
    
    /**
     * Sets the rented car
     * @param car New rented car
     */public void setCar (Car car)
    {
        _car = new Car (car);
    }
    
    /**
     * Sets the pickup date
     * The pickup date must be at least one day before the return date,
     * otherwise the new pickup date won't be assigned
     * @param pickDate New pickup date
     */
    public void setPickDate (Date pickDate)
    {
        if(pickDate.before(_returnDate))
            _pickDate = new Date (pickDate);
    }
    
    /**
     * Sets the return date
     * The return date must be at least one day after the pickup date,
     * otherwise the new return date won't be assigned
     * @param returnDate New return date
     */
    public void setReturnDate (Date returnDate)
    {
        if(returnDate.after(_pickDate))
            _returnDate = new Date (returnDate);
    }
    
    /**
     * Checks if 2 rents are the same
     * rents are considered the same if they have the same name, car, pickup and return date
     * @param other Rent to compare this rent to
     * @return True if the rents are the same
     */
    public boolean equals (Rent other)
    {
        return _name.equals(other._name) && _car.equals(other._car) &&
        _pickDate.equals(other._pickDate) && _returnDate.equals(other._returnDate);
    }
    
    /**
     * Returns the number of rent days
     * @return Number of rent days
     */
    public int howManyDays()
    {
        // using the "difference" to calculate the diference between 2 dates
        return _pickDate.difference (_returnDate);
    }
    
    /**
     * Returns the rent total price
     * @return Rent total price
     */
    public int getPrice()
    {
        // daily price for each car type
        final int DAILY_PRICE_A_TYPE = 100;
        final int DAILY_PRICE_B_TYPE = 150;
        final int DAILY_PRICE_C_TYPE = 180;
        final int DAILY_PRICE_D_TYPE = 240;
        final int WEEK_DISCOUNT_PERCENTAGE = 10; //10% off for a full week rental
        final int DAYS_PER_WEEK = 7;
        
        int rentDailyRate; // rent daily rate based on the type
        
        // assign the daily price to the rentDailyRate according to the type parameter 
        switch (_car.getType())
        {   
            case 'A': rentDailyRate = DAILY_PRICE_A_TYPE;
                      break;
            case 'B': rentDailyRate = DAILY_PRICE_B_TYPE;
                      break;
            case 'C': rentDailyRate = DAILY_PRICE_C_TYPE;
                      break;
            case 'D': rentDailyRate = DAILY_PRICE_D_TYPE;
                      break;
            
            default : rentDailyRate = DAILY_PRICE_A_TYPE;
        }
        // rent weekly rate based on the daily rate including the week discount
        int rentWeeklyRate = DAYS_PER_WEEK * rentDailyRate * (100 - WEEK_DISCOUNT_PERCENTAGE)/100;
        int numOfRentDays = howManyDays();
        
        // calculate the total price for the rental's days and weeks
        int totalPrice = (((numOfRentDays / DAYS_PER_WEEK) * rentWeeklyRate) + 
                           (numOfRentDays % DAYS_PER_WEEK) * rentDailyRate);
        return totalPrice;
    }
    
    /**
     * Tries to upgrade the car to a better car
     * If the given car is better than the current car of the rent, upgrades it and returns
     * the upgrade additional cost, otherwise won't upgrade and will return 0
     * @param car New car to upgrade to
     * @return upgrade cost
     */
    public int upgrade (Car newCar)
    {
        int basicPrice = this.getPrice();
        if (newCar.better(_car))
        {   
            _car = new Car (newCar);
            return  this.getPrice() - basicPrice;
        }
        return 0;
    }
    
    /**
     * Checks if there is a double listing of a rent for the same person and car with an overlap
     * in the rental days
     * If there is - returns a new rent object with the unified dates, otherwise - returns null
     * @param other Another rent object
     * @return Unified rent object or null - if there is no overlapping
     */
    public Rent overlap (Rent other)
    {
        if (_name.equals(other._name) && _car.equals(other._car))
        {    
            // checks if the dates are overlapping
            if (!_pickDate.after(other._returnDate) && !_returnDate.before(other._pickDate)) 
            {
                // assign to newPickDate the earlier pickup date
                Date newPickDate = (_pickDate.before (other._pickDate))?
                                    new Date (_pickDate) : new Date (other._pickDate) ;
                // assign to newReturnDate the later return date
                Date newReturnDate = (_returnDate.after (other._returnDate))?
                                      new Date (_returnDate) : new Date (other._returnDate) ;
                
                return new Rent (_name, _car, newPickDate, newReturnDate);
            }
        }
        return null;
    }
    
    /**
     * Returns a String that represents this rent
     * @return String that represents this rent in the following format: 
     * Name:Rama From:30/10/2022 To:12/11/2022 Type:B Days:13 Price:1845
     */
    public String toString()
    {
        return "Name:" + getName() + " From:" + _pickDate.toString() + " To:" + 
        _returnDate.toString() + " Type:" + _car.getType() + " Days:" + howManyDays()
        + " Price:" + getPrice();
    }
}

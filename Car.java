
/**
 * Represents a car,
 * @author (Shimon Shriki)
 * @version (29/11/2022)
 */
public class Car
{
    private int _id;  //license number
    private char _type;  //category type
    private String _brand;
    private boolean _isManual; //gear-type
    
    private final int _DEFAULT_ID = 9999999;
    private final char _DEFAULT_TYPE = 'A';
    
    /**
     * Initialize a car object
     * id should be a 7 digits number, otherwise would set it to 9999999.
     * type should be 'A','B','C' or 'D', otherwise would set it to 'A'
     * @param id Car license number
     * @param type Car category type
     * @param brand Car's brand
     * @param isManual Car gear-type, true if gear is manual
     */
    public Car(int id, char type, String brand, boolean isManual)
    {
        //Checking whether the received id is 7 digit long, otherwise set it to 9999999
        if (id >= 1000000 && id < 10000000) 
            _id = id;
        else
            _id = _DEFAULT_ID;
        
        //Checking whether the type value is valid, otherwise set it to 'A'
        if (type == 'A' || type == 'B' || type == 'C' || type == 'D') 
            _type = type;
        else
            _type = _DEFAULT_TYPE;
        
        _brand = brand;
        
        _isManual = isManual;    
    }
   
    /**
     * Copy constractor
     * @param other Car to be copied
     */
    public Car(Car other)
    {
        _id = other._id;
        _type = other._type;
        _brand = other._brand;
        _isManual = other._isManual;
    }
    
    /**
     * Returns car's id number
     * @return Car id number
     */
    public int getId()
    {
        return _id;
    }
    
    /**
     * Returns car's type
     * @return Car type
     */
    public char getType()
    {
        return _type;
    }
    
    /**
     * Returns car's brand
     * @return Car brand
     */
    public String getBrand()
    {
        return _brand;
    }
    
    /**
     * Returns whether car's gear is manual or not
     * @return True if gear is manual, otherwise - false 
     */
    public boolean isManual()
    {
        return _isManual;
    }
    
    /**
     * Sets the id (only if the new id is valid)
     * @param id New id number
     */
    public void setId(int id)
    {
        //Checking whether the received id is 7 digit long, otherwise id won't be changed
        if (id >= 1000000 && id < 10000000)
            _id = id;
    }
    
    /**
     * Sets the type (only if the new type is valid)
     * @param type New type value
     */
    public void setType(char type)
    {
        //Checking whether the type value is valid, otherwise type won't be changed
        if (type == 'A' || type == 'B' || type == 'C' || type == 'D')
            _type = type;
    }
    
    /**
     * Sets the brand of the car
     * @param brand New brand value
     */
    public void setBrand(String brand)
    {
        _brand = brand;
    }
    
    /**
     * Sets the gear type of the car
     * @param isManual New value of the isManual flag
     */
    public void setIsManual(boolean isManual)
    {
        _isManual = isManual;
    }
    
    /**
     * Returns a String object that represents this car 
     * @return String that represents a car in this format: id:1234567 type:A brand:Mazda gear:manual 
     */
    public String toString()
    {
        String gear = "auto"; // String that represents the gear type of the car
        if (_isManual)            
            gear = "manual";
            
        return "id:" + _id + " type:" + _type + " brand:" + _brand + " gear:" + gear; 
    }
    
    /**
     * Checks if two cars are the same
     * Cars are considered the same if they have the same type, brand and gear
     * @other Car to compare this car to
     * @return True if the cars are the same, otherwise - false
     */
    public boolean equals (Car other)
    {
        // returning true only if they have the same type, brand and gear 
        return (_type == other._type && _brand.equals(other._brand) 
                && _isManual == other._isManual);
    }
    
    /**
     * Checks if this car is better than another car
     * a car is considered better than another car if its type is higher.
     * If the types are the same, a car with an auto gear is better than a car with a manual gear
     * @param other Car to compare this car to
     * @return True if this car is better than the other car, otherwise - false
     */
    public boolean better (Car other)
    {
        if (_type == other._type)
            return _isManual == false && other._isManual == true;
            
        return (_type > other._type);
    }
    
    /**
     * Check if this car is worse than the another car.
     * The rules are the same of the "better" method
     * @param other Car to compare this car to
     * @return True if this car is worse than the other car, otherwise - false
     */
    public boolean worse (Car other)
    {
        return other.better(this);// checking that by using the better method on the other car                
    }
}















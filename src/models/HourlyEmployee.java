package models;

import java.time.LocalDate;

/**
 *
 * @author JWright
 */
public class HourlyEmployee extends Employee{
    private double payRate, hoursWorked;
    private static final double MINIMUM_WAGE = 11.40;
    

    public HourlyEmployee(String firstName, String lastName, String socialInsuranceNum, LocalDate dateOfBirth) {
        super(firstName, lastName, socialInsuranceNum, dateOfBirth);
        payRate = MINIMUM_WAGE;
        hoursWorked = 0;
    }

    public HourlyEmployee(double payRate, String firstName, String lastName, String socialInsuranceNum, LocalDate dateOfBirth) {
        super(firstName, lastName, socialInsuranceNum, dateOfBirth);
        setPayRate(payRate);
        hoursWorked=0;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        if (payRate >= MINIMUM_WAGE)
            this.payRate = payRate;
        else
            throw new IllegalArgumentException("The minimum hourly rate is " + MINIMUM_WAGE);
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked <= 24)
            this.hoursWorked = hoursWorked;
        else
            throw new IllegalArgumentException("You cannot log more than 24 hours in a day");
    }
    
    

    @Override
    public double calculatePay() {
        return payRate * hoursWorked;
    }
    
}

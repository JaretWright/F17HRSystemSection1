package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author JWright
 */
public class HourlyEmployee extends Employee{
    private double payRate, hoursWorked;
    private static final double MINIMUM_WAGE = 11.40;
    

    public HourlyEmployee(String firstName, String lastName, String socialInsuranceNum, LocalDate dateOfBirth) throws SQLException {
        super(firstName, lastName, socialInsuranceNum, dateOfBirth);
        payRate = MINIMUM_WAGE;
        hoursWorked = 0;
        insertIntoDB();
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
    
    
    /**
     * This method will create a new employee in the database
     */
    public void insertIntoDB() throws SQLException
    {
        Connection conn = null;
        PreparedStatement statement = null;
               
        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrSystem/employees?useSSL=false",
                                        "student", "student");

            //2. create a String that holds our sql query with ? for user inputs
            String sql = "INSERT INTO employees (firstName, lastName, socialInsuranceNumber, "
                                                + "dateOfBirth, hourlyRate) VALUES "+
                         "(?,?,?,?,?)";

            //3. prepare the query
            statement = conn.prepareStatement(sql);

            //4. convert the birthday into a SQL date
            Date dob = Date.valueOf(super.getDateOfBirth());

            //5. bind the values to the parameters
            statement.setString(1, super.getFirstName());
            statement.setString(2, super.getLastName());
            statement.setString(3, super.getSocialInsuranceNum());
            statement.setDate(4, dob);
            statement.setDouble(5, payRate);

            //6. execute the update/insert
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally
        {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
        }
    }
}

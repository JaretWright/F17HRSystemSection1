package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;



/**
 *
 * @author JWright
 */
public abstract class Employee {
    private int employeeNum;
    private String firstName, lastName, socialInsuranceNum, position;
    private LocalDate dateOfBirth, startDate, endDate;
    private boolean administrator;

    public Employee(String firstName, String lastName, String socialInsuranceNum, LocalDate dateOfBirth) {
        setFirstName(firstName);
        setLastName(lastName);
        this.socialInsuranceNum = socialInsuranceNum;
        setDateOfBirth(dateOfBirth);
        startDate = LocalDate.now();
    }

    public void setEmployeeNum(int employeeNum) {
        this.employeeNum = employeeNum;
    }

    public int getEmployeeNum() {
        return employeeNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.matches("[A-Z][a-zA-Z]*"))
            this.firstName = firstName;
        else
            throw new IllegalArgumentException("First name must start with an upper"
                                            + "case letter and only contain letters");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.matches("[A-Z][a-zA-Z\\-]*?"))
            this.lastName = lastName;
        else
            throw new IllegalArgumentException("Last names must start with an upper case"
                                    +      "letter, followed by letters or -");
        
    }

    public String getSocialInsuranceNum() {
        return socialInsuranceNum;
    }

    public void setSocialInsuranceNum(String socialInsuranceNum) {
        if (socialInsuranceNum.matches("\\d{3}\\s\\d{3}\\s\\d{3}"))
            this.socialInsuranceNum = socialInsuranceNum;
        else
            throw new IllegalArgumentException("Social insurance number number XXX XXX XXX");
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        
        if (age <= 100 && age >= 10)
            this.dateOfBirth = dateOfBirth;
        else
            throw new IllegalArgumentException("Employees can not be more than 100 years old");
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate.isAfter(LocalDate.now()) || startDate.isEqual(LocalDate.now()))
            this.startDate = startDate;
        else
            throw new IllegalArgumentException("Start date cannot be earlier than today");
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }
    
    public abstract double calculatePay(); 
    
    /**
     * This will update the hourly employee in the database
     */
    public void updateInDB() throws SQLException
    {
        Connection conn = null;
        PreparedStatement statement = null;
               
        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrSystem?useSSL=false",
                                        "student", "student");

            //2. create a String that holds our sql query with ? for user inputs
            String sql= "UPDATE employees " +
                        "SET firstName = ?, " +
                        "    lastName = ?, " +
                        "    socialInsuranceNumber = ?, " +
                        "    dateOfBirth = ? " +
                        "WHERE employeeNum = ?";

            //3. prepare the query
            statement = conn.prepareStatement(sql);

            //4. convert the birthday into a SQL date
            Date dob = Date.valueOf(getDateOfBirth());

            //5. bind the values to the parameters
            statement.setString(1, getFirstName());
            statement.setString(2, getLastName());
            statement.setString(3, getSocialInsuranceNum());
            statement.setDate(4, dob);
            statement.setInt(5, getEmployeeNum());

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

package models;

import java.time.LocalDate;
import java.time.Period;



/**
 *
 * @author JWright
 */
public abstract class Employee {
    private static int nextEmployeeNum = 1000001;
    private int employeeNum;
    private String firstName, lastName, socialInsuranceNum, position;
    private LocalDate dateOfBirth, startDate, endDate;
    private boolean administrator;

    public Employee(String firstName, String lastName, String socialInsuranceNum, LocalDate dateOfBirth) {
        setFirstName(firstName);
        setLastName(lastName);
        this.socialInsuranceNum = socialInsuranceNum;
        this.dateOfBirth = dateOfBirth;
        startDate = LocalDate.now();
        employeeNum = nextEmployeeNum;
        nextEmployeeNum++;
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
        int age = Period.between(LocalDate.now(), dateOfBirth).getYears();
        
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
}

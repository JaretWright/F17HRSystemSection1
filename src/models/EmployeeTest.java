package models;

import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author JWright
 */
public class EmployeeTest {
    public static void main(String[] args)
    {
        Employee emp1 = new HourlyEmployee("Joel", "Smith", "978 235 212", LocalDate.of(1956, Month.MARCH, 14));
        Employee emp2 = new HourlyEmployee(25.76,"Joel", "Smith-Hat", "978 235 212", LocalDate.of(1956, Month.MARCH, 14));
        System.out.printf("Employee 1 is: %s%n", emp1);
    }
}

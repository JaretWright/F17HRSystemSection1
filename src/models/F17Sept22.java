package models;

import java.util.Scanner;

/**
 *
 * @author JWright
 */
public class F17Sept22 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Please enter your area code or phone number: ");
        Scanner keyboard = new Scanner(System.in);
        String userInput = keyboard.nextLine();
        
        System.out.printf("That is a%s area code%n", checkAreaCode(userInput)?
                                                    " valid" : "n invalid");
        System.out.printf("That is a%s phone number%n", checkPhoneNumber(userInput)?
                                                    " valid" : "n invalid");

        System.out.print("enter your first name: ");
        String name = keyboard.nextLine();
        
        System.out.printf("Your first name is %s %n", checkName(name)?
                                                    "valid" : "invalid");
    }  
    
    public static boolean checkAreaCode(String areaCode)
    {
        return areaCode.matches("[2-9]\\d{2}");
    }
    
    public static boolean checkPhoneNumber(String phoneNum)
    {
        return phoneNum.matches("\\(?[2-9]\\d{2}\\)?[-.\\s]?[2-9]\\d{2}[-.\\s]?\\d{4}");
    }
    
    public static boolean checkName(String name)
    {
        return name.matches("[A-Z][a-zA-Z]*");
    }
}

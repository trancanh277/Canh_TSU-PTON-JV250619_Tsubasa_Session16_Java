package baitap05;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Validator {
    public static String getString(Scanner scanner, String suggest) {
        String input = "" ;
        do {
            System.out.println(suggest);
            input = scanner.nextLine();
            if(input.isEmpty()) {
                System.out.println("Can not blank !");
            }else {
                break;
            }
        }while (true);
        return input;
    }

    public static int getInt(Scanner scanner, String suggest) {
        String input = "" ;
        do {
            input = getString(scanner, suggest);
            try {
                return Integer.parseInt(input);
            }catch (Exception e) {
                System.out.println("Can not parse number !");
            }
        }while (true);
    }

    public static double getDouble(Scanner scanner, String suggest) {
        String input = "" ;
        do {

            input = getString(scanner, suggest);
            try {
                return Double.parseDouble(input);
            }catch (Exception e) {
                System.out.println("Can not parse double !");
            }
        }while (true);
    }

    public static LocalDate getLocalDate(Scanner scanner, String suggest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do {

            String input = getString(scanner, suggest);
            try {
                return LocalDate.parse(input, formatter);
            } catch (Exception e) {
                System.out.println("Can not parse date !");
            }
        }while (true);
    }
}

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
                System.out.println("Không được để trống !");
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
                System.out.println("Mời nhập số !");
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
                System.out.println("Mời nhập số !");
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
                System.out.println("Mời nhập định dạng ngày !");
            }
        }while (true);
    }
}

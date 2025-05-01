package db;
import enums.Gender;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AcceptUserInfo {

    public static Long getUserChoiceID(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                Long num = scanner.nextLong();
                return num;
            } catch (InputMismatchException e) {
                System.err.println("Incorrect format! (write the number!)");
                scanner.nextLine();
            }
        }
    }
    public static Gender getUserGender(){
        while (true) {
            try {
                return Gender.valueOf(new Scanner(System.in).nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.err.println("Wrong value! Try again! Example: (male/female)");
            }
        }
    }

    public static int getUserChoiceNumber(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {
                int num = scanner.nextInt();
                return num;
            } catch (InputMismatchException e){
                System.err.println("Incorrect format! (write the number!)");
                scanner.nextLine();
            }
        }
    }
}

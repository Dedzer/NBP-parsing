package pl.parser.nbp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class GetScanner {
    // getting my scanner
    public static String codeScanner() {
        Scanner scanner = new Scanner(System.in);
        String code = "";
        boolean check = true;
        System.out.println("Available currency codes: USD, AUD, CAD, EUR, HUF, CHF, GBP, JPY, CZK, DKK, EEK, NOK, SEK, XDR");
        System.out.println("Enter currency code: ");
        while (check) {
            code = scanner.nextLine();
            String[] codes = {"USD", "AUD", "CAD", "EUR", "HUF", "CHF", "GBP", "JPY", "CZK", "DKK", "EEK", "NOK", "SEK", "XDR"};
            for (int i = 0; i < codes.length; i++) {
                if (code.toUpperCase().equals(codes[i])) {
                    check = false;
                    break;
                } else {
                    if (i == codes.length - 1) {
                        System.out.println("Please, enter right currency code");
                    }
                }
            }
        }
        return code;
    }
    // method that check format of start date
    public static String startDateScanner() {
        Scanner scanner = new Scanner(System.in);
        String startDate = "";
        boolean check = true;
        System.out.println("Date format is: 'yyyy-MM-dd'");
        System.out.println("Enter start date: ");
        while (check) {
            startDate = scanner.nextLine();
            if (startDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                dateValidation(startDate, scanner);
                check = false;
            } else {
                System.out.println("Please, enter a date with right format");
            }
        }
        return startDate;
    }

    // method that check format of end date
    public static String endDateScanner() {
        Scanner scanner = new Scanner(System.in);
        String endDate = "";
        boolean check = true;
        System.out.println("Enter endDate date: ");
        while (check) {
            endDate = scanner.nextLine();
            if (endDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                dateValidation(endDate, scanner);
                check = false;
            } else {
                System.out.println("Please, enter a date with right format");
            }
        }
        return endDate;
    }

    // method that check date validation
    public static boolean dateValidation(String date, Scanner scanner){
        boolean check = true;
        while (check){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.setLenient(false);
            try {
                simpleDateFormat.parse(date);
                check = false;
            } catch (ParseException e) {
                System.out.println("Entered date in not valid");
                date = scanner.nextLine();
            }
        }
        return true;
    }
}

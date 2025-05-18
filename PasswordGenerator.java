import java.util.*;

public class PasswordGenerator                                                                              {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean again = true;

        while (again) {
            System.out.print("Enter password length: ");
            int length = sc.nextInt();
            sc.nextLine();

            System.out.print("Include uppercase letters? (y/n): ");
            boolean upper = sc.nextLine().equalsIgnoreCase("y");

            System.out.print("Include lowercase letters? (y/n): ");
            boolean lower = sc.nextLine().equalsIgnoreCase("y");

            System.out.print("Include numbers? (y/n): ");
            boolean numbers = sc.nextLine().equalsIgnoreCase("y");

            System.out.print("Include special characters? (y/n): ");
            boolean special = sc.nextLine().equalsIgnoreCase("y");

            String password = generatePassword(length, upper, lower, numbers, special);
            System.out.println("\nGenerated Password: " + password);

            System.out.print("\nDo you want to generate a new password? (y/n): ");
            String choice = sc.nextLine();
            again = choice.equalsIgnoreCase("y");
        }

        System.out.println("\nThank you for using Password Generator!");
    }

    public static String generatePassword(int length, boolean upper, boolean lower, boolean numbers, boolean special) {
        String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerChars = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";
        String specialChars = "!@#$%^&*";

        String allowed = "";
        if (upper) allowed += upperChars;
        if (lower) allowed += lowerChars;
        if (numbers) allowed += numberChars;
        if (special) allowed += specialChars;

        if (allowed.isEmpty()) return "Please select at least one character type.";

        StringBuilder password = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(allowed.length());
            password.append(allowed.charAt(index));
        }

        return password.toString();
    }
}

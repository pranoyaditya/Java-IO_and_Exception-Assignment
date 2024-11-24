import java.io.*;
import java.util.regex.*;
import java.util.Scanner;

public class EmailValidator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path of the file containing email addresses: ");
        String inputFilePath = scanner.nextLine();

        System.out.print("Enter the path for the output file to save valid email addresses: ");
        String outputFilePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String email;
            int validCount = 0;

            while ((email = reader.readLine()) != null) {
                email = email.trim();
                if (isValidEmail(email)) {
                    writer.write(email);
                    writer.newLine();
                    validCount++;
                }
            }

            System.out.println("Validation completed. " + validCount + " valid email addresses saved to: " + outputFilePath);

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading or writing files: " + e.getMessage());
        }

        scanner.close();
    }
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}

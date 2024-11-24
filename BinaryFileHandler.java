import java.io.*;
import java.util.Scanner;

public class BinaryFileHandler {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path of the binary file to read: ");
        String inputFilePath = scanner.nextLine();

        System.out.print("Enter the path of the file to write to: ");
        String outputFilePath = scanner.nextLine();


        try (FileInputStream fis = new FileInputStream(inputFilePath);
             FileOutputStream fos = new FileOutputStream(outputFilePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("File contents copied successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Error while copying file: " + e.getMessage());
            return;
        }

        System.out.print("Do you want to append data to the output file? (yes/no): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter data to append (in hexadecimal, space-separated, e.g., 48 65 6C 6C 6F): ");
            String hexData = scanner.nextLine();

            try (FileOutputStream fos = new FileOutputStream(outputFilePath, true)) {

                String[] hexValues = hexData.split("\\s+");
                for (String hex : hexValues) {
                    fos.write(Integer.parseInt(hex, 16));
                }
                System.out.println("Data appended successfully.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid hexadecimal format: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error while appending data: " + e.getMessage());
            }
        }

        scanner.close();
    }
}

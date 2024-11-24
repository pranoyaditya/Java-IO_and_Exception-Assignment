import java.io.*;
import java.util.*;

public class ConfigurationManager {

    private static final String CONFIG_FILE = "config.properties";

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            properties.load(reader);
            System.out.println("Configuration loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found. A new one will be created.");
        } catch (IOException e) {
            System.out.println("Error reading configuration file: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. View all configurations");
            System.out.println("2. Get a specific value");
            System.out.println("3. Update a configuration");
            System.out.println("4. Save changes");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nCurrent configurations:");
                    properties.forEach((key, value) -> System.out.println(key + " = " + value));
                    break;

                case 2:
                    System.out.print("Enter the key: ");
                    String key = scanner.nextLine();
                    String value = properties.getProperty(key);
                    if (value != null) {
                        System.out.println("Value for '" + key + "': " + value);
                    } else {
                        System.out.println("Key '" + key + "' not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter the key to update: ");
                    key = scanner.nextLine();
                    System.out.print("Enter the new value: ");
                    value = scanner.nextLine();
                    properties.setProperty(key, value);
                    System.out.println("Configuration updated.");
                    break;

                case 4:
                    try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
                        properties.store(writer, "Updated Configuration");
                        System.out.println("Changes saved successfully.");
                    } catch (IOException e) {
                        System.out.println("Error saving configuration: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

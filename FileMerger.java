import java.io.*;
import java.util.Scanner;

public class FileMerger {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get file paths
        System.out.print("Enter the path of the first text file: ");
        String firstFilePath = scanner.nextLine();

        System.out.print("Enter the path of the second text file: ");
        String secondFilePath = scanner.nextLine();

        System.out.print("Enter the path of the output file: ");
        String outputFilePath = scanner.nextLine();


        try (
                BufferedReader reader1 = new BufferedReader(new FileReader(firstFilePath));
                BufferedReader reader2 = new BufferedReader(new FileReader(secondFilePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))
        ) {

            copyFileContents(reader1, writer);

            copyFileContents(reader2, writer);

            System.out.println("Files have been merged successfully into: " + outputFilePath);

        } catch (FileNotFoundException e) {
            System.out.println("One of the input files was not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred while processing the files: " + e.getMessage());
        }

        scanner.close();
    }

    private static void copyFileContents(BufferedReader reader, BufferedWriter writer) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }
    }
}

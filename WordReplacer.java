import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class WordReplacer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path of the text file: ");
        String filePath = scanner.nextLine();

        System.out.print("Enter the word to find: ");
        String wordToFind = scanner.nextLine();

        System.out.print("Enter the word to replace it with: ");
        String wordToReplace = scanner.nextLine();

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            String modifiedContent = content.replaceAll("\\b" + wordToFind + "\\b", wordToReplace);
            Files.write(Paths.get(filePath), modifiedContent.getBytes());

            System.out.println("The word '" + wordToFind + "' has been replaced with '" + wordToReplace + "'.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error processing the file: " + e.getMessage());
        }

        scanner.close();
    }
}

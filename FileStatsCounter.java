import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileStatsCounter {

    public static void main(String[] args) {
        String filePath = "path_to_your_file.txt";
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length();


                String[] words = line.split("\\s+");
                wordCount += words.length;
            }

            charCount += lineCount - 1;

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("Number of lines: " + lineCount);
        System.out.println("Number of words: " + wordCount);
        System.out.println("Number of characters: " + charCount);
    }
}
